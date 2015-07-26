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
                               description (str (get method "description"))
                               parameters (conj (vec (get method "parameters"))
                                                {"name" "format"
                                                 "type" "string"
                                                 "optional" true
                                                 "description" "The desired response format: json, xml, or vdf. Default: json"})
                               parameters (->> parameters
                                              (map (fn [parameter]
                                                     (let [parameter-name (get parameter "name")
                                                           indexed-array? (.contains parameter-name "[0]")
                                                           parameter-name (.replace parameter-name "[0]" "")
                                                           description (str "("
                                                                            (get parameter "type")
                                                                            (when (get parameter "optional") ", optional")
                                                                            ")"
                                                                            (when (get parameter "description")
                                                                              (str " " (get parameter "description"))))]
                                                       [(keyword parameter-name)
                                                         (if indexed-array? [:indexed-array description]
                                                                            description)]))))
                               http-method (case (get method "httpmethod")
                                             "POST" :post
                                             "GET" :get)
                               parameter-names (vec (map first parameters))
                               api-url (if (contains? parameter-names :key) secured-url unsecured-url)
                               url (util/url api-url interface-name method-name (format "v%04d" version))]]
                     {(str method-name "V" version)
                      (list 'steam-request url description http-method
                            (->> parameters (apply concat) vec))}))})))

(defn generate-api []
  (let [api-list (json-parser/parse-stream (io/reader steam-api-list-file))
        api-requests (generate-requests
                       {:secured-url "https://api.steampowered.com"
                        :unsecured-url "https://api.steampowered.com"}
                       api-list)
        api-code `(~'(ns steam-api-clj.core
                      (:require [steam-api-clj.api :refer [steam-request]]))

                     ~'(declare requests)

                     ~'(defn request [interface method parameters]
                         ((get-in requests [interface method]) parameters))

                     ~'(defn list-api-calls []
                         (vec (for [[interface methods] (vec requests)]
                                [interface (vec (keys methods))])))

                     ~'(defn interfaces []
                         (keys requests))

                     ~'(defn method-info [interface method]
                        (meta (get-in requests [interface method])))

                     ~'(defn interface-methods [interface]
                         (keys (get-in requests [interface])))

                     (~'def ~'requests ~api-requests))
        code-as-string (apply str (map #(str (with-out-str (pprint %)) "\n")
                                       api-code))]
    (spit "src/steam_api_clj/core.clj" code-as-string)))
