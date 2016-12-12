(ns gnome.util
  "Namespace for utility functions"
  (:require
    [clojure.data.json :as json]))

(defn safe-json-read-str
  [body]
  (try
    (json/read-str body :key-fn keyword)
    (catch Exception e
      (throw e))))

(defn credentials []
  (safe-json-read-str (slurp "resources/configs/credentials.json")))

(defn load-access-token [institution]
  (let [access-token-key (keyword (str (name institution) "-access-token"))]
    (access-token-key (credentials))))

(def accounts-map (:accounts (credentials)))

(defn get-account-id
  [institution]
  ((keyword institution) accounts-map))
