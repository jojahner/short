(ns short.views.static
  (:require [short.views.layout :as layout]
            [hiccup.page :as page]
            [hiccup.element :as elem]))

(defn home []
  (layout/common "short"
                 [:div.jumbotron
                   [:h1 "Shorten your URL"]
                   [:p "just enter the URL you want to shorten in the field below and press 'submit'"]
                   [:form {:role "form" :action "/" :method "post"}
                     [:div.col-xs-12.col-md-11
                       [:input.form-control.url {:type "text" :placeholder "https://example.com" :name "url"}]]
                     [:div.col-xs-6.col-md-1
                       [:button.btn.btn-info.btn-lg {:type "submit"} "Submit"]]]
                   "&nbsp;"]))

(defn about []
  (layout/common "short"
                 [:img.about.center-block {:src "http://replygif.net/i/872.gif"}]))
