(ns meigen-bot.core
  (:require
   ;; [meigen-bot.twitter.guest-client :as guest]
   [meigen-bot.twitter.private-client :as private]
   [meigen-bot.meigen :refer [meigens]]
   ))

(defn pick-random [] (rand-nth meigens))

(defn make-status [data]
  (let [{content :content, author :author} data]
    (str content "\n\n" author)))

(defn tweet-random []
  (let [status (make-status (pick-random))]
    (private/update-status status)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (def status (str (:content data) "\n\n" (:author data)))
;; (def status (make-status))

;; (def status (make-status (pick-random)))
;; (def response (private/update-status status))

;; (tweet-random)

;; (def status (guest/get-status "1477034578875277316"))
;; (def status (private/get-status "1477034578875277316"))

;; status
