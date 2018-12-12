(defproject bomberman "0.1.0-SNAPSHOT"
  :author "Otávio Pace"
  :description "slack bot that gets images on the web and post them"
  :url "https://github.com/otaviopace/bomberman"
  :license {:name "WTFPL"
            :url "http://www.wtfpl.net/txt/copying/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [cheshire "5.8.1"]
                 [compojure "1.6.0"]]
  ;; :profiles {:uberjar {:aot [bomberman.core]}}
  :main bomberman.core)
