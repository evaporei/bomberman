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

(defn post-slack-message [message]
  @(http/request {
                  :url slack-url
                  :method :post
                  :body (json/write-str {:text message})
                  }))

(defn get-index-of-text [text quantity]
  (get (str/split text #" ") quantity))

(defn get-text-command [req-map prop]
  (let [text (get-in req-map [:text])]
    (case prop
      "race" (get-index-of-text text 0)
      "quantity" (get-index-of-text text 1))))

(defn build-welcome-message [req-map]
  (str (get-text-command req-map "race") " bomb for @" (get-in req-map [:user_name]) " x" (get-text-command req-map "quantity")))

(defn fetch-image [race]
  (get-in (json/read-str (get-in @(http/get (str/replace dogs-url #"race" race) {:client sni-client}) [:body])) ["message"]))

(defn request-handler [req]
  (let
    [req-map (keywordize-keys (form-decode (slurp (.bytes (:body req)) :encoding "UTF-8")))]
    (post-slack-message (build-welcome-message req-map))
    (dotimes [_ (Integer/parseInt (get-text-command req-map "quantity"))]
      (post-slack-message (fetch-image (get-text-command req-map "race")))))
  req)

(defroutes app
  (POST "/" req (request-handler req)))

(defn -main [& args]
  (run-server (-> app wrap-json-body wrap-json-response) {:port 8080})
  (println "Server started on port 8080"))
