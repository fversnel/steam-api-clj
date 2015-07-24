(defproject steam-api-clj "0.1.0-SNAPSHOT"
  :description "Steam Web API interface for Clojure"
  :url "https://github.com/fversnel/steam-api-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :profiles {:dev {:dependencies [[cheshire "5.5.0"]]
                   :source-paths ["dev/src"]
                   :resource-paths ["dev/resources"]}})
