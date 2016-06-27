(ns org.fversnel.steam-api.api)

(def data-format-headers
  {"json" "application/json"
   "xml"  "text/xml"
   "vdf"  "text/vdf"
   "csv"  "text/csv"})

(defn to-indexed-array
  "Creates an indexed array of parameter value pairs to be
   used in the parameter list of some of the Steam web requests.

   Example:
   (to-indexed-param-array \"test\" [42 43 44])
   => {\"test[0]\" 42 \"test[1]\" 43 \"test[2]\" 44}"
  [param-name values]
  (if-not (nil? values)
    (->> (or values [])
         (map-indexed (fn [index value] [(str param-name "[" index "]") value]))
         (into {}))))

(defn to-normal-value
  [param-name value]
  (cond
    (coll? value) [param-name (clojure.string/join "," value)]
    (nil? value)  nil
    :else         [param-name value]))

(defmacro steam-request
  "Parses a Steam request structure and returns a function that takes the required parameters
   of the request as an argument and returns a http request map"
  [url description http-method parameters-spec]
  (let [param-specs# (apply hash-map parameters-spec)
        conformer (fn [param-symbol]
                    (let [param-key (keyword param-symbol)
                          data-type (if (coll? description)
                                      (first description)
                                      :normal)
                          conform (case data-type
                                    :normal `to-normal-value
                                    :indexed-array `to-indexed-array)]
                      `(~conform ~(name param-key) ~param-symbol)))
        param-names# (keys param-specs#)
        param-symbols# (map (comp symbol name) param-names#)
        http-params-type (if (= http-method :get) :query-params :form-params)]
    (with-meta `(fn
                  ~(if (not-empty param-names#)
                     [{:keys (vec param-symbols#) :as 'params}]
                     [])

                  {:method  ~http-method
                   :url     ~url
                   ~http-params-type (into {} ~(->> param-symbols#
                                                    (map conformer)
                                                    vec))
                   :headers (merge
                              {"Content-Type" "application/x-www-form-urlencoded; charset=utf-8"}
                              (if (contains? ~'params :format)
                                {"Accept" (get data-format-headers ~'format)}
                                {"Accept" ~(get data-format-headers "json")}))})
               {:url url
                :description description
                :http-method http-method
                :parameters (apply array-map parameters-spec)})))
