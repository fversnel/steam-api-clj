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
  (if (keyword? parameter-spec)
    {:type :normal :name parameter-spec}
    {:type (first parameter-spec) :name (second parameter-spec)}))

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
         (map to-parameter-definition)
         (filter #(contains? parameters (:name %)))
         (map to-query-param)
         (apply (partial merge {})))))

(defn extract-parameter-names [parameters-spec]
  (map (comp :name to-parameter-definition) parameters-spec))

(defmacro steam-request [url doc-string http-method parameters-spec]
  (let [parameter-names# (set (extract-parameter-names parameters-spec))]
    (with-meta `(fn
                   ~(if-not (empty? parameter-names#)
                      [{:keys (->> parameter-names# (map (comp symbol name)) vec) :as 'parameters}]
                      [])

                   ~(merge
                      {:method http-method
                       :url    url}
                      (case http-method
                        :get `{:query-params (make-request-params ~(vec parameters-spec) ~'parameters)
                               :headers      (merge
                                               {"ContentType" "application/x-www-form-urlencoded; charset=utf-8"}
                                               (when (contains? ~'parameters :format)
                                                 {"Accept" (get data-format-headers ~'format)}))}
                        :post `{:form-params (make-request-params ~(vec parameters-spec) ~'parameters)
                                :headers     {"ContentType" "application/x-www-form-urlencoded; charset=utf-8"}}
                        {})))
               {:url url
                :http-method http-method
                :parameters parameters-spec
                :description doc-string})))
