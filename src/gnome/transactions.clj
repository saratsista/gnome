(ns gnome.transactions
  (:require [clojure.data.json :as json]
            [gnome.web :as web]
            [gnome.util :refer :all]))


(defn parse-transactions
  [transactions]
  (println (type transactions)))

(defn get-transactions
  "Makes call to PLAID POST /connect/get and returns the 'transactions' field of the response"
  ([institution sub-option]
   (let [options (web/request-metadata institution (web/transactions-accounts-url))
         updated-options (merge options (web/plaid-options-json institution))
         response-body (web/plaid-get-transactions updated-options)
         transactions (:transactions response-body)]
     (case sub-option
       "--all" transactions
       "--print" (println transactions)
       "--parse" (parse-transactions transactions)))))
