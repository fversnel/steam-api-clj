(ns steam-api-clj.util)

(defn comma-separated-string
  "Useful for steam API calls that require arrays as comma-separated values"

  [values]
  (clojure.string/join "," values))

(defn url [& strings]
 (->> strings
     (interpose "/")
     (apply str)))
