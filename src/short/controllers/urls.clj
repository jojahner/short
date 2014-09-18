(ns short.controllers.urls
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [short.views.urls :as view]
            [short.models.url :as model]))

(defn show
  [id]
  {:status 302
   :headers {"Location" (model/find id)}})

(defn create
  [url]
  (view/create (model/create url)))

(defroutes routes
  (POST "/"      [url] (create url))
  (GET  "/r/:id" [id]  (show id)))
