(ns short.controllers.static
  (:require [compojure.core :refer [defroutes GET]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [short.views.static :as view]))

(defn home []
  (view/home))

(defn about []
  (view/about))

(defroutes routes
  (GET "/"     [] (view/home))
  (GET "/about" [] (view/about)))
