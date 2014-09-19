(ns short.models.url
  (:require [clojure.java.jdbc :as sql]
            [short.lib.hashid :as hashid]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/short"))

(def insert-sql "INSERT INTO urls (url) VALUES (?) RETURNING id")
(def select-sql "SELECT url FROM urls WHERE id = ?")

(defn create
  [url]
  (let [entry (sql/query spec [insert-sql url])
        id (get (first entry) :id)]
    (hashid/encode id)))

(defn show
  [hashid]
  (let [id (hashid/decode hashid)
        entry (sql/query spec [select-sql id])]
    (get (first entry) :url)))
