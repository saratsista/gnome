(ns gnome.web
  (:require [compojure.core :refer :all]
            [liberator.core :refer [resource defresource]]
            [liberator.dev :refer [wrap-trace]]))


(defresource handle-balance
  [institution]
  :available-media-types ["text/html" "text/plain"]
  :handle-ok (format "Institution is %s" institution))

(defroutes app
  (GET "/" [] "Hello World!")
  (GET "/balance/:institution" [institution] (handle-balance institution))
  (GET "/balance" [] (handle-balance nil)))

(def handler
  (-> app
      (wrap-trace :header :ui)))

