(ns gnome.accounts
  (:require [gnome.util :as util]
            [gnome.web :as web]))

(def accounts-map (:accounts (util/credentials)))

(defn get-accounts-info
  [institution sub-option]
  (let [options (web/request-opts institution (web/transactions-accounts-url))
        response-body (web/plaid-get-transactions options)]
    (case sub-option
      "--all" (println (:accounts response-body)))))