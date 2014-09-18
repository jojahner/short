(ns short.views.urls
  (:require [short.views.layout :as layout]
            [hiccup.page :as page]
            [hiccup.element :as elem]))

(defn create
  [url]
  (layout/common "short"
                 [:div.jumbotron
                   [:a.text-center {:href url}
                     [:h1.result.text-center (str "http://shrrt.herokuapp.com/r/" url)]]
                   "&nbsp;"]))

