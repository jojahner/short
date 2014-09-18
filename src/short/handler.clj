(ns short.handler
  (:use compojure.core)
  (:require [short.db :as db]
            [short.views :as views]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]))

(defroutes app-routes
  (GET "/"
       []
       (views/home))
  (POST "/"
        {params :params}
        (let [{url :url} params
              hashid (db/add-url url)
              url (str "http://shrrt.herokuapp.com/r/" hashid)]
          (views/add-url-result url)))
  (GET "/r/:hashid"
        [hashid]
        {:status 302 :headers {"Location" (str (db/get-url-from-hashid hashid))}})
  (GET "/about"
       []
       (views/about))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

; Server
(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty app-routes {:port port})))
