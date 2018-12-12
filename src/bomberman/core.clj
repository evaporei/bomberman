(ns bomberman.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server]]
            [cheshire.core :refer :all]
            [compojure.core :refer :all]))

(defn hello []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (generate-string {:oi "pessoa"})})

(defroutes app
  (GET "/hello" [] (hello)))

(defn -main [& args]
  (run-server app {:port 8080})
  (println "Server started on port 8080"))
