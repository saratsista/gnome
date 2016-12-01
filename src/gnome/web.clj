(ns gnome.web
  (:require [clj-http.client :as http]
            [environ.core :as environ]))

(defn plaid-base-url [] (environ/env :plaid-base-url))

(defn make-request []
)


