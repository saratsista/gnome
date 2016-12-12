(ns gnome.core
  (:require [gnome.transactions :as transactions]
           [gnome.accounts :as accounts])
  (:gen-class))

(defn usage []
  "Constructs and prints the Usage string"
  (let [usage-string "Usage: gnome <institution> <option>

    options:
    transaction -- gets the transactions from last 30 days
    accounts    -- displays the accounts info for the specific 'institution' mentioned
    help        -- Show this menu

    Institutions:
      chase
      bofa"]

    (println usage-string)))

(defn -main
  "Usage: gnome <institution> <option> <sub-option>"
  [& args]
  (try
    (let [parsed-args (if (= (count args) 2) (conj (into [] args) "--all") (into [] args))
          institution (nth parsed-args 0)
          option (nth parsed-args 1)
          sub-option (nth parsed-args 2)]
          ; conj appends to the front of the list. (args is of type ArraySeq)
          (println parsed-args)
      (case option
        "transactions"
            (transactions/get-transactions institution sub-option)
        "accounts" (accounts/get-accounts-info institution sub-option)
        (usage)))
    (catch Exception e
      (println e)
      (usage))))
