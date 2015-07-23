(ns steam-api-clj.generator
  (:require [steam-api-clj.util :as util]
            [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]
            [cheshire.core :as json-parser]))

(def steam-api-list-file (-> "steam-api-list.json" io/resource io/file))

(defn generate-requests [{:keys [secured-url unsecured-url]} api-list]
  (apply merge
         (for [interface (get-in api-list ["apilist" "interfaces"])
               :let [interface-name (get interface "name")]]
           {interface-name
            (apply merge
                   (for [method (get interface "methods")
                         :let [method-name (get method "name")
                               version (get method "version")
                               doc-string (let [general-description (get method "description")
                                                parameter-descriptions
                                                  (map (fn [parameter]
                                                         (str (get parameter "name")
                                                              " ["
                                                              (get parameter "type")
                                                              (when (get parameter "optional") ", optional")
                                                              "]"
                                                              (when (get parameter "description")
                                                                (str " - " (get parameter "description")))))
                                                       (get method "parameters"))]
                                            (str (when general-description (str general-description "\n\n"))
                                                 (clojure.string/join "\n" parameter-descriptions)))
                               http-method (case (get method "httpmethod")
                                             "POST" :post
                                             "GET" :get)
                               parameters (map (fn [parameter]
                                                 (let [parameter-name (get parameter "name")]
                                                   (if (.contains parameter-name "[0]")
                                                     [:indexed-array (keyword (.replace parameter-name "[0]" ""))]
                                                     (keyword parameter-name))))
                                               (get method "parameters"))
                               parameters (conj (vec parameters) :format)
                               api-url (if (contains? (set parameters) :key)
                                         secured-url
                                         unsecured-url)
                               url (util/url api-url interface-name method-name (format "v%04d" version))]]
                     {(str method-name "V" version)
                      (list 'steam-request url doc-string http-method (vec parameters))}))})))

(defn generate-api []
  (let [api-list (json-parser/parse-stream (io/reader steam-api-list-file))
        api-requests (generate-requests 
                       {:secured-url "https://api.steampowered.com"
                        :unsecured-url "https://api.steampowered.com"}
                       api-list)
        api-code `((~'ns ~'steam-api-clj.core
                      (:require [~'steam-api-clj.api :refer [~'steam-request]]))

                     (~'declare ~'requests)

                     (~'defn ~'request [~'interface ~'method ~'parameters]
                       ((~'get-in ~'requests [~'interface ~'method]) ~'parameters))

                     (def ~'requests ~api-requests))
        code-as-string (apply str (map #(with-out-str (pprint %)) api-code))]
    (spit "src/steam_api_clj/core.clj" code-as-string)))
