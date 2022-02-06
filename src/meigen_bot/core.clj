(ns meigen-bot.core
  (:require [clj-http.client :as client]))

(client/get "https:futurismo.biz")

(defn get-status
  [status-id]
  (println "Get Status: id="status-id ))
