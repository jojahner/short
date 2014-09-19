(ns short.controllers.static
  (:require [compojure.core :refer [defroutes GET]]
            [selmer.parser :refer [render-file]]))

(defroutes routes
  (GET "/"      [_] (render-file "static/home.html" {}))
  (GET "/about" [_] (render-file "static/about.html" {})))
