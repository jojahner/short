(defproject short "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [com.h2database/h2 "1.3.170"]
                 [clojurewerkz/route-one "1.1.0"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler short.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})