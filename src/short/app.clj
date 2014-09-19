(ns short.app
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [short.controllers.static :as static]
            [short.controllers.urls :as urls]
            [short.views.layout :as layout]
            [short.models.migration :as schema])
  (:gen-class))

(defroutes routes
  static/routes
  urls/routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))
(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))
