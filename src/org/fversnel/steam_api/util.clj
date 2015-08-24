(ns org.fversnel.steam-api.util)

(defn comma-separated-string
  "Useful for Steam API calls that require arrays as comma-separated values"
  [values]
  (clojure.string/join "," values))

(defn url
  "Creates a url from a collection of strings, interposes a '/' character between each string.
   For example: (url \"something\" \"anything\")
                => \"something/anything\""
  [& strings]
  (->> strings
      (interpose "/")
      (apply str)))
