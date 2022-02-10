(ns meigen-bot.core
  (:import (java.time Instant Duration))
  (:require
   ;; [meigen-bot.twitter.guest-client :as guest]
   [meigen-bot.twitter.private-client :as private]
   [meigen-bot.meigen :refer [meigens]]
   [chime.core :as chime]))

(defn pick-random [] (rand-nth meigens))

(defn make-status [data]
  (let [{content :content, author :author} data]
    (str content "\n\n" author)))

(defn tweet-random []
  (let [data   (pick-random)
        status (make-status data)]
    (try
      (private/update-status status)
      (catch Exception e (println "Tweet Failed." (.getMessage e))))))

(defn -main [& args]
  (println "Started up Twitter Bot.")
  (chime/chime-at (chime/periodic-seq
                   (Instant/now)
                   (Duration/ofHours 1))
                  (fn [time]
                    (println "tweet at " time)
                    (tweet-random))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; (def status (str (:content data) "\n\n" (:author data)))
;; (def status (make-status))

;; (def status (make-status (pick-random)))
;; (def response (private/update-status status))

;; (tweet-random)

;; (def status (guest/get-status "1477034578875277316"))
;; (def status (private/get-status "1477034578875277316"))

;; status
