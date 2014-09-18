(ns short.views.layout
  (:require [hiccup.page :as page]
            [hiccup.element :as elem]))

(def js-includes
  (list (page/include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js")
        (page/include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")))

(def css-includes
  (list (page/include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")
        (page/include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css")
        (page/include-css "/stylesheets/base.css")))

(def nav-bar-top
  [:nav.navbar.navbar-default.navbar-fixed-top {:role "navigation"}
    [:div.container
      [:div.navigation
        [:div.navbar-header
          [:button.navbar-toggle.collapsed {:type "button" :data-toggle "collapse" :data-target ".navbar-collapse"}
            [:span.sr-only "Toggle navigation"]
            [:span.icon-bar]
            [:span.icon-bar]
            [:span.icon-bar]]
          [:a.navbar-brand {:href "/" } "Short"]]]
      [:div.collapse.navbar-collapse
        [:ul.nav.navbar-nav.navbar-left
          [:li (elem/link-to "/" "home")]
          [:li (elem/link-to "/about" "about")]]]]])

(defn common [title & body]
  (page/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content
            "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    css-includes]
   [:body
    nav-bar-top
    [:div {:id "content" :class "container"} body]
    js-includes]))
