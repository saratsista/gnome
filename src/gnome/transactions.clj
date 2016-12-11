(ns gnome.transactions
  (:require [clojure.data.json :as json]
            [gnome.web :as web]
            [gnome.util :as util]))

(defn get-transactions
  ([institution sub-option]
   (let [options (web/request-opts institution (web/transactions-accounts-url))
         response-body (web/plaid-get-transactions options)]
     (case sub-option
       "--all" (:transactions response-body)))))

