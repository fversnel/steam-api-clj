(defproject org.fversnel/steam-api "0.8.0"
  :description "Steam Web API interface for Clojure"
  :url "https://github.com/fversnel/steam-api-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [cheshire "5.6.2"]
                                  [clj-http "2.2.0"]]
                   :source-paths ["dev/src"]
                   :resource-paths ["dev/resources"]}})
