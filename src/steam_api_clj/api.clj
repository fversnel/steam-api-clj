(ns steam-api-clj.api
  (:require [steam-api-clj.util :as util]))

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
                {name (cond
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
                   ~(if-not (empty? parameter-names#)
                      [{:keys (->> parameter-names# (map (comp symbol name)) vec) :as 'parameters}]
                      [])

                   ~(merge
                      {:method http-method
                       :url    url}
                      (case http-method
                        :get `{:query-params (make-request-params ~parameters-spec# ~'parameters)
                               :headers      (merge
                                               {"ContentType" "application/x-www-form-urlencoded; charset=utf-8"}
                                               (when (contains? ~'parameters :format)
                                                 {"Accept" (get data-format-headers ~'format)}))}
                        :post `{:form-params (make-request-params ~parameters-spec# ~'parameters)
                                :headers     {"ContentType" "application/x-www-form-urlencoded; charset=utf-8"}}
                        {})))
               {:url url
                :description description
                :http-method http-method
                :parameters parameters-spec})))
