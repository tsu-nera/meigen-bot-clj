(ns meigen-bot.core
  (:require [clj-http.client :as client]
            [cheshire.core :as cheshire]))

(def twitter-guest-bearer-token
  "AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA")
(def user-agent "Mozilla/5.0 (X11; Linux x86_64; rv:94.0) Gecko/20100101 Firefox/94.0")

(def twitter-guest-headers {:headers {:authorization (str "Bearer " twitter-guest-bearer-token)
                                      :user-agent user-agent
                                      :x-guest-token twitter-guest-token}})

(defn get-statuses-url [status-id]
  (str "https://api.twitter.com/2/timeline/conversation/" status-id ".json?include_reply_count=1&send_error_codes=true&count=20"))


(defn get-statuses [status-id]
  (let [url (get-statuses-url status-id)]
    (client/get url twitter-guest-headers)))

(defn get-status [status-id]
  (println "Get Status: id="status-id))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; REPL Experiments
