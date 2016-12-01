(ns gnome.core
  (require [gnome.web :as web])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (web/print-url))

  
