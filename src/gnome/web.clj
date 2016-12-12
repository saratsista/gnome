(ns gnome.web
  (:require [environ.core :as environ]
            [clj-http.client :as http]
            [gnome.util :refer :all]))

(defn plaid-base-url [] (environ/env :plaid-base-url))

(defn transactions-accounts-url [] (str (plaid-base-url) "/connect/get"))

(defn plaid-options-json
  "Constructs the 'options' parameter in PLAID POST /connect/get"
  [institution]
  {:options (str "{\"account:\" \"" (get-account-id institution) "\"}")})

(defn request-metadata
  "Construct the options for the PLAID API call"
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
  "Makes call to PLAID POST /connect/get API and returns the whole reponse"
  [options]
  ; TODO: Need to handle the 4xx and 5xx cases from upstream
  (let [response (http/request options :as :clojure)
        body (safe-json-read-str (slurp (:body response)))]
    body))
