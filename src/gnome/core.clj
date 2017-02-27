(ns gnome.core
  (:require [gnome.transactions :as transactions]
           [gnome.accounts :as accounts])
  (:gen-class))

(defn usage []
  "Constructs and prints the Usage string"
  (let [usage-string "Usage: gnome <institution> <option> <sub-option>

    options:
    transaction -- gets the transactions from last 30 days
    accounts    -- displays the accounts info for the specific 'institution' mentioned
    expenses    -- get expenses for a particular category
    help        -- Show this menu

    sub-options:
      For expenses:
        The name of the cateogory
      For accounts:
        `--all` -- gets information for all the accounts
        `--balance` -- gets balance for all accounts or particular institution
      For transactions:


    Institutions:
      bofa
      all (to consider all the institutions. Works only for 'expenses' option.)

      Examples:
      `lein run all expenses restaurants`   -- Gets the total expenses for restaurants
      `lein run bofa expenses pets`         -- Gets the total expenses on bofa for pets
      `lein run bofa accounts --balance`    -- Gets balance from bofa
      `lein run bofa transactions --print`  -- prints all transactions from bofa"]


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
        "transactions" (transactions/get-transactions institution sub-option)
        "accounts" (accounts/get-accounts-info institution sub-option)
        "expenses" (transactions/get-expenses sub-option)
        (usage)))
    (catch Exception e
      (println e)
      (usage))))
