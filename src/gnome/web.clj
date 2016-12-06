(ns gnome.web
  (:require [clj-http.client :as http]
            [environ.core :as environ]
            [clojure.data.json :as json]))

(defn plaid-base-url [] (environ/env :plaid-base-url))

(defn transactions-url [] (str (plaid-base-url) "/connect/get"))

(defn credentials []
  (json/read-str (slurp "resources/configs/credentials.json") :key-fn keyword))

(def accounts (:accounts (credentials)))

(defn get-access-token [institution]
  (let [access-token-key (keyword (str (name institution) "-access-token"))]
    (access-token-key (credentials))))

(defn transaction-request-opts
  [institution]
  {
   :method       :post,
   :content-type "application/x-www-form-urlencoded",
   :query-params {:client_id    (:plaid-client-id (credentials))
                  :secret       (:plaid-client-secret (credentials))
                  :access_token (get-access-token institution)}
   })

(defn get-transactions
  [institution]
  (http/request (merge {:url (transactions-url)}
                       (transaction-request-opts institution)))
)


