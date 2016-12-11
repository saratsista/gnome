(ns gnome.web
  (:require [environ.core :as environ]
            [clj-http.client :as http]
            [gnome.util :refer :all]))

(defn plaid-base-url [] (environ/env :plaid-base-url))

(defn transactions-accounts-url [] (str (plaid-base-url) "/connect/get"))

(defn request-opts
  [institution url]
  {
   :method       :post,
   :url          url,
   :content-type "application/x-www-form-urlencoded",
   :query-params {:client_id    (:plaid-client-id (credentials))
                  :secret       (:plaid-client-secret (credentials))
                  :access_token (load-access-token institution)}
   })

(defn plaid-get-transactions
  [options]
  (let [response (http/request options :as :clojure)
        body (safe-json-read-str (slurp (:body response)))]
    body))

