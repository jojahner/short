(require '[clojure.java.jdbc :as jdbc])

(def pgdb
  { :subprotocol "postgresql"
    :subname "//localhost:5432/short" })

(def create-users-table-sql
  "create table urls (
    id bigserial primary key,
    url varchar(255) not null
  );")