(defproject gnome "0.1.0-SNAPSHOT"
  :description "Teller for Personal Finances"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [compojure "1.5.2"]
                 [liberator "0.14.1"]]
  :plugins [[cider/cider-nrepl "0.14.0"]]
  :main ^:skip-aot gnome.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
