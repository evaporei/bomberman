(ns bomberman.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server]]
            [org.httpkit.client :as http]
            [cheshire.core :refer :all]
            [compojure.core :refer :all]
            [clojure.data.json :as json]
            [clojure.string :as str]
            [dotenv :refer [env app-env]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response]])
  (:import
    [java.net URI]
    [javax.net.ssl
     SNIHostName SNIServerName SSLEngine SSLParameters])
  (:use clj-fuzzy.metrics)
  (:use ring.util.codec)
  (:use clojure.walk))

(defn sni-configure
  [^SSLEngine ssl-engine ^URI uri]
  (let [^SSLParameters ssl-params (.getSSLParameters ssl-engine)]
    (.setServerNames ssl-params [(SNIHostName. (.getHost uri))])
    (.setSSLParameters ssl-engine ssl-params)))

(def sni-client (org.httpkit.client/make-client
                  {:ssl-configurer bomberman.core/sni-configure}))

(def slack-url (env "SLACK_URL"))
(def dogs-url "https://dog.ceo/api/breed/race/images/random")

(defn parse-req [req]
  {:race (first (str/split (:text req) #" "))
   :quantity (second (str/split (:text req) #" "))
   :user-name (:user_name req)})

(defn post-slack-message [message]
  @(http/request {:url slack-url
                  :method :post
                  :body (json/write-str {:text message :parse "full"})}))

(defn build-welcome-message [race user-name quantity]
  (str race " bomb for @" user-name " x" quantity))

(defn create-dogs-url [race]
  (str/replace dogs-url #"race" race))

(defn fetch-dog-image-url [race]
  (->
    race
    create-dogs-url
    (http/get {:client sni-client})
    deref
    :body
    json/read-str
    (get-in ["message"])))

(defn request-handler [req]
  (let
    [req-map (keywordize-keys (form-decode (slurp (.bytes (:body req)) :encoding "UTF-8")))]
    (let [{race :race
           quantity :quantity
           user-name :user-name} (parse-req req-map)]
      (post-slack-message (build-welcome-message race user-name quantity))
      (dotimes [_ (Integer/parseInt quantity)]
        (post-slack-message (fetch-dog-image-url race)))))
  req)

(defroutes app
  (POST "/" req (request-handler req)))

(defn -main [& args]
  (run-server (-> app wrap-json-body wrap-json-response) {:port 8080})
  (println "Server started on port 8080"))
