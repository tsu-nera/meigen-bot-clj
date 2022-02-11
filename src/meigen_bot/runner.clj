(ns meigen-bot.runner
  (:require
   [taoensso.timbre :as log]
   [meigen-bot.twitter.private-client :as private]
   [meigen-bot.meigen :refer [meigens]]))


(defn pick-random [] (rand-nth meigens))

(defn make-status [data]
  (let [{content :content, author :author} data]
    (str content "\n\n" author)))

(defn tweet-random []
  (let [data                               (pick-random)
        {content :content, author :author} data
        status                             (make-status data)]
    (try
      (private/update-status status)
      (log/info "post tweet completed.")
      (log/debug (str content " - " author))
      (catch Exception e (log/error "post tweet Failed." (.getMessage e))))))

(defn -main []
  (tweet-random))
