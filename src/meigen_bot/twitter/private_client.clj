(ns meigen-bot.twitter.private-client
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [environ.core :refer [env]]))

(def auth-token (env :auth-token))
(def ct0 (env :ct0))

(def guest-bearer-token
  "AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA")
(def user-agent "Mozilla/5.0 (X11; Linux x86_64; rv:94.0) Gecko/20100101 Firefox/94.0")

(def cookie (str "auth_token=" auth-token "; ct0=" ct0))

(def private-headers {:headers
                      {:authorization (str "Bearer " guest-bearer-token)
                       :user-agent    user-agent
                       :x-csrf-token  ct0
                       :cookie        cookie}})

(defn get-statuses-url [status-id]
  (str "https://api.twitter.com/2/timeline/conversation/"
       status-id
       ".json?include_reply_count=1&send_error_codes=true&count=20"))

(defn get-statuses [status-id]
  (let [url (get-statuses-url status-id)]
    (client/get url private-headers)))

(defn get-status [status-id]
  (let [response (get-statuses status-id)]
    (-> response
        :body
        (json/parse-string true)
        :globalObjects
        :tweets
        (get (keyword status-id)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; (def status (get-status "1477034578875277316"))
;; status
