(ns org.fversnel.steam-api.api
  (:require [org.fversnel.steam-api.util :as util]))

(def data-format-headers
  {"json" "application/json"
   "xml"  "text/xml"
   "vdf"  "text/vdf"
   "csv"  "text/csv"})

(defn to-indexed-param-array [array-name values]
  (->> values
       (map-indexed
         (fn [index value] [(str array-name "[" index "]") value]))
       (flatten)
       (apply hash-map)))

(defn to-parameter-definition [parameter-spec]
  (let [parameter-name (first parameter-spec)
        definition (second parameter-spec)]
    {:name parameter-name
     :type (if (coll? definition) (first definition) :normal)}))

(defn make-request-params [parameters-spec parameters]
  (letfn [(to-query-param
            [{:keys [type name] :as parameter-definition}]
            (let [value (get parameters name)]
              (case type
                :normal
                {(clojure.core/name name)
                 (cond
                  (coll? value) (util/comma-separated-string value)
                  :else value)}

                :indexed-array
                (to-indexed-param-array name value))))]
    (->> parameters-spec
         (vec)
         (map to-parameter-definition)
         (filter #(contains? parameters (:name %)))
         (map to-query-param)
         (apply (partial merge {})))))

(defmacro steam-request [url description http-method parameters-spec]
  (let [parameters-spec# (apply hash-map parameters-spec)
        parameter-names# (keys parameters-spec#)]
    (with-meta `(fn
                  ~(if (not-empty parameter-names#)
                     [{:keys (->> parameter-names# (map (comp symbol name)) vec) :as 'parameters}]
                     [])

                  {:method ~http-method
                   :url    ~url
                   :params (make-request-params ~parameters-spec# ~'parameters)
                   :headers (merge
                              {"Content-Type" "application/x-www-form-urlencoded; charset=utf-8"}
                              (if (contains? ~'parameters :format)
                                {"Accept" (get data-format-headers ~'format)}
                                {"Accept" ~(get data-format-headers "json")}))})
               {:url url
                :description description
                :http-method http-method
                :parameters parameters-spec})))
