(ns meigen-bot.core
  (:require [clj-http.client :as client]))


;; url = 'https://api.twitter.com/2/timeline/conversation/{}.json?include_reply_count=1&send_error_codes=true&count={}{}'.format(status_id, str(count), cursor)

(def get-statuses
  [let [url "https://api.twitter.com/2/timeline/conversation/xxx.json"]
   (client/get url)])

(defn get-status [status-id]
  (println "Get Status: id="status-id ))
