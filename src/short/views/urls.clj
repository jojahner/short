(ns short.views.urls
  (:require [short.views.layout :as layout]
            [hiccup.page :as page]
            [hiccup.element :as elem]))

(defn show-url-path
  [id]
  (str "http://shrrt.herokuapp.com/r/" id))

(defn create
  [hashid]
  (layout/common "short"
                 [:div.jumbotron
                   [:a.text-center {:href (show-url-path hashid)}
                     [:h1.result.text-center (show-url-path hashid)]]
                   "&nbsp;"]))
