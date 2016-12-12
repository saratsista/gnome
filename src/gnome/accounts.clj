(ns gnome.accounts
  (:require [gnome.util :as util]
            [gnome.web :as web]))

(defn get-balance
  "Finds the balance details of the account"
  [institution accounts-info]
  (let [account (into {} (filter (fn [account] (= (:_id account) (util/get-account-id institution))) accounts-info))]
    (println (:balance account))))

(defn get-accounts-info
  "Makes call to PLAID POST /connect/get API & returns the 'accounts' field of the response JSON"
  [institution sub-option]
  (let [options (web/request-metadata institution (web/transactions-accounts-url))
        updated-options (merge options (web/plaid-options-json institution))
        response-body (web/plaid-get-transactions options)
        accounts-info (:accounts response-body)]
    (case sub-option
      "--all" (println accounts-info)
      "--balance" (get-balance institution accounts-info))))
