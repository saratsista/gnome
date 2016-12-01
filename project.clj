(defproject gnome "0.1.0-SNAPSHOT"
  :description "Teller for personal finances"
  :url "https://github.com/saratsista/gnome"
  :license {:name "MIT License"
            :url "https://github.com/saratsista/gnome/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.3.0"]
                 [environ "1.1.0"]]
  :main ^:skip-aot gnome.core
  :plugins [[lein-environ "1.1.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:env {:plaid-base-url "https://tartan.plaid.com"}}}) 

