(ns meigen-bot.core
  (:import (java.time Instant Duration))
  (:require
   ;; [meigen-bot.twitter.guest-client :as guest]
   [taoensso.timbre :as log]
   [meigen-bot.twitter.private-client :as private]
   [meigen-bot.meigen :refer [meigens]]
   [chime.core :as chime]))

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

(def timbre-config {:timestamp-opts {:pattern  "yyyy-MM-dd HH:mm:ss,SSS"
                                     :locale   (java.util.Locale. "ja_JP")
                                     :tiemzone (java.util.TimeZone/getTimeZone "Asia/Tokyo")}})

(defn -main [& args]
  (log/merge-config! timbre-config)
  (log/info "Started up Twitter Bot.")
  (chime/chime-at (chime/periodic-seq
                   ;; (Instant/now)
                   (Duration/ofHours 1)
                   ;;(Duration/ofMinutes 3)
                   )
                  (fn [_]
                    (tweet-random))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (def status (make-status (pick-random)))
;; (def response (private/update-status status))

;; (tweet-random)

;; (def status (guest/get-status "1477034578875277316"))
;; (def status (private/get-status "1477034578875277316"))

;; status
