(ns gnome.core
  (:gen-class)
  (:require [gnome.web :as web]
            [ring.adapter.jetty :as ring]
            ))

(defn -main
  [& args]
  (ring/run-jetty web/handler {:port 3000}))
