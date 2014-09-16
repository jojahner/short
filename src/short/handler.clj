(ns short.handler
  (:require [short.views :as views]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

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
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
