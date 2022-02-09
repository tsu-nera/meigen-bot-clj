(ns meigen-bot.twitter.private-client
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [environ.core :refer [env]]))

(def auth-token (env :auth-token))
(def ct0 (env :ct0))

(def user-agent "Mozilla/5.0 (X11; Linux x86_64; rv:94.0) Gecko/20100101 Firefox/94.0")

;; (def guest-token
;;   (delay
;;     (let [response (client/post
;;                     "https://api.twitter.com/1.1/guest/activate.json"
;;                     {:cookie-policy :none
;;                      :headers       {:authorization (str "Bearer " guest-bearer-token)
;;                                      :user-agent    user-agent}})]
;;       (-> response
;;           :body
;;           (json/parse-string true)
;;           :guest_token
;;           (or (throw (ex-info "Can't get guest token" {:response response})))))))

;; (def guest-headers {:headers
;;                     {:authorization (str "Bearer " guest-bearer-token)
;;                      :user-agent    user-agent
;;                      :x-guest-token @guest-token}})

;; (defn get-statuses-url [status-id]
;;   (str "https://api.twitter.com/2/timeline/conversation/"
;;        status-id
;;        ".json?include_reply_count=1&send_error_codes=true&count=20"))

;; (defn get-statuses [status-id]
;;   (let [url (get-statuses-url status-id)]
;;     (client/get url guest-headers)))

;; (defn get-status [status-id]
;;   (let [response (get-statuses status-id)]
;;     (-> response
;;         :body
;;         (json/parse-string true)
;;         :globalObjects
;;         :tweetsq
;;         (get (keyword status-id)))))
