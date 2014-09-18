(ns short.views
  (:require [short.db :as db]
            [hiccup.page :as page]
            [hiccup.element :as elem]
            [ring.util.response :as response]))

(def js-includes
  (list (page/include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js")
        (page/include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")))

(def css-includes
  (list (page/include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")
        (page/include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css")
        (page/include-css "/css/styles.css")))

(defn header [title]
  [:head
    [:title title]
    css-includes])

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

(defn page-header [title]
  (list (header title)
        nav-bar-top))

(defn home
  []
  (page/html5
    (page-header "Short")
    [:div.container
      [:div.jumbotron
        [:h1 "Shorten your URL"]
        [:p "just enter the URL you want to shorten in the field below and press 'submit'"]
        [:form {:role "form" :action "/" :method "post"}
          [:div.col-xs-12.col-md-11
            [:input.form-control.url {:type "text" :placeholder "https://example.com" :name "url"}]]
          [:div.col-xs-6.col-md-1
           [:button.btn.btn-info.btn-lg {:type "submit"} "Submit"]]]
        "&nbsp;"]]
    js-includes))

(defn add-url-result
  [{:keys [url]}]
  (let [hashid (db/add-url url)
        url (str "http://shrrt.herokuapp.com/r/" hashid)]
    (page/html5
      (page-header "Short")
      [:div.container
        [:div.jumbotron
          [:a.text-center {:href url}
            [:h1.result.text-center url] ]
          "&nbsp;"]]
     js-includes)))

(defn redirct-to-url
  [hashid]
  (let [{url :url} (db/get-url-from-hashid hashid)]
    (response/redirect url)))

(defn about []
  (page/html5
    (page-header "Short")
    [:div.container
      [:img.about.center-block {:src "http://replygif.net/i/872.gif"}]]
    js-includes))
