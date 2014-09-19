(defproject short "0.1.0"
  :description "Short URLS"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [postgresql "9.1-901-1.jdbc4"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [compojure "1.1.9"]
                 [selmer "0.7.1"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:init short.app/init
         :handler short.app/application}
  :main ^:skip-aot short.app
  :uberjar-name "short-standalone.jar"
  :min-lein-version "2.0.0"
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}
   :uberjar {:aot :all}})
