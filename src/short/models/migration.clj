(ns short.models.migration
  (:require [clojure.java.jdbc :as sql]
            [short.models.url :as url]))

(defn migrated? []
  (-> (sql/query url/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='urls'")])
      first :count pos?))

(defn migrate []
  (when-not (migrated?)
    (print "Creating database structure...")
    (flush)
    (sql/db-do-commands
     url/spec
      (sql/create-table-ddl
        :urls
        [:id :serial "PRIMARY KEY"]
        [:url :varchar "NOT NULL"]
        [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))
