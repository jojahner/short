(ns short.db
  (:require [clojure.java.jdbc :as sql]
            [short.hashid :as hashid]))

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2:file"
              :subname "db/short"})

(defn add-url
  [url]
  (let [results (sql/with-connection db-spec
                  (sql/insert-record :urls
                                     {:url url}))]
    (assert (= (count results) 1))
    (hashid/encode (first (vals results)))))

(defn get-url-from-hashid
  [hashid]
  (let [url-id (hashid/decode hashid)
        results (sql/with-connection db-spec
                  (sql/with-query-results res
                    ["select url from urls where id = ?" url-id]
                    (doall res)))]
    (assert (= (count results) 1))
    (first results)))
