(ns short.models.url
  (:require [clojure.java.jdbc :as sql]
            [short.lib.hashid :as hashid]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/short"))

(defn create
  [url]
  (let [entry (sql/query spec ["INSERT INTO urls (url) VALUES (?) RETURNING id" url])
        id (get (first entry) :id)]
    (hashid/encode id)))

(defn show
  [hashid]
  (let [id (hashid/decode hashid)
        entry (sql/query spec ["select url from urls where id = ?" id])]
    (get (first entry) :url)))
