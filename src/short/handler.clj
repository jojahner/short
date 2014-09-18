(ns short.handler
  (:use compojure.core)
  (:require [short.views :as views]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]))

(defroutes app-routes
  (GET "/"
       []
       (views/home))
  (POST "/"
        {params :params}
        (views/add-url-result params))
  (GET "/r/:hashid"
        [hashid]
        (views/redirct-to-url hashid))
  (GET "/about"
       []
       (views/about))

  (GET "/login/"
       []
       {:status 302 :headers {"Location" "http://somewhere.com"}})
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

; Server
(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty app-routes {:port port})))
