(ns short.controllers.urls
  (:require [compojure.core :refer [defroutes GET POST]]
            [selmer.parser :refer [render-file]]
            [short.models.url :as model]))

(defn show
  [id]
  {:status 302
   :headers {"Location" (model/show id)}})

(defn create
  [url]
  (render-file "urls/create.html" {:hashid (model/create url)}))

(defroutes routes
  (POST "/"      [url] (create url))
  (GET  "/:id" [id]  (show id)))
