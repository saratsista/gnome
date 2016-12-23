(ns gnome.transactions
  (:require [clojure.data.json :as json]
            [clojure.string :refer [lower-case includes?]]
            [gnome.web :as web]
            [gnome.util :refer :all]))


(defn get-transactions
  "Makes call to PLAID POST /connect/get and returns the 'transactions' field of the response"
  ([institution sub-option]
   (let [options (web/request-metadata institution (web/transactions-accounts-url))
         updated-options (merge options (web/plaid-options-json institution))
         response-body (web/plaid-get-transactions updated-options)
         transactions (:transactions response-body)]
     (case sub-option
       "--all" transactions
       "--print" (println transactions)))))

(defn get-all-transactions []
  (let [bofa-options (web/request-metadata "bofa" (web/transactions-accounts-url))
        bofa-updated-options (merge bofa-options (web/plaid-options-json "bofa"))
        bofa-transactions (:transactions (web/plaid-get-transactions bofa-updated-options))
        chase-options (web/request-metadata "chase" (web/transactions-accounts-url))
        chase-transactions (:transactions (web/plaid-get-transactions chase-options))]
    (concat bofa-transactions chase-transactions)))

(defn- category-transactions
  "Filters all the transactions in a 'category-list"
  [category, transactions]
  (letfn [(filter-by-name [namev]
             (reduce (fn [filtered, t]
                       (if (or (= (lower-case (t :name)) namev)
                                  (includes? (lower-case (t :name)) namev))
                          (conj filtered t)
                          filtered))
                []
                transactions))]
  (let [category-list (into #{}(map (fn [s] (lower-case s)) (get-all-categories category)))]
    (conj [] (map filter-by-name category-list)))))

(defn get-expenses
  [category]
  "Calculates and returns the total expenses for a given category"
  (let [all-transactions (get-all-transactions)
        filtered (flatten (category-transactions category all-transactions))
        total-expenses (reduce (fn [total, transaction] (+ total (transaction :amount))) 0 filtered)]
    (println total-expenses)))

