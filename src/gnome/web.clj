(ns gnome.web
  (:require [compojure.core :refer :all]
            [gnome.crypto :as crypto]
            [liberator.core :refer [resource defresource]]
            [liberator.dev :refer [wrap-trace]]))

(defn- handle-balance
  [institution]
  (crypto/decrypt) 
  )

(defresource balance
  [institution]
  :available-media-types ["text/html" "text/plain"]
  :handle-ok (handle-balance institution))

(defroutes app
  (GET "/" [] "Hello World!")
  (GET "/balance/:institution" [institution] (balance institution))
  (GET "/balance" [] (balance nil)))

(def handler
  (-> app
      (wrap-trace :header :ui)))

