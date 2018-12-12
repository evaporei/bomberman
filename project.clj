(defproject bomberman "0.1.0-SNAPSHOT"
  :author "Otávio Pace"
  :description "slack bot that gets images on the web and post them"
  :url "https://github.com/otaviopace/bomberman"
  :license {:name "WTFPL"
            :url "http://www.wtfpl.net/txt/copying/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.3.0-alpha5"];; this version is because of protocolexception, which is related to SNI: https://medium.com/@kumarshantanu/using-server-name-indication-sni-with-http-kit-client-f7d92954e165
                 [cheshire "5.8.1"]
                 [compojure "1.6.0"]
                 [clj-fuzzy "0.4.1"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-json "0.4.0"]
                 [lynxeyes/dotenv "1.0.2"]]
  :main bomberman.core)
