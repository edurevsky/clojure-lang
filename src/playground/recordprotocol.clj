(ns playground.recordprotocol)

(defprotocol Mailable
  (send-mail
    [this content]))

(defrecord Contact
  [id name number email]
  Mailable
  (send-mail
    [this content]
    (let [name (:name this)]
      (future
        (Thread/sleep 1000)
        (println "Mail sent to" name "with content:" content)))))

(defrecord Employee
  [id name email]
  Mailable
  (send-mail
    [this content]
    (let [name (:name this)]
      (println "Mail sent to" name "with content:" content))))

(let [contact   (->Contact 1 "John Doe" "9999-999" "john@doe.com")
      employee  (Employee. 2 "Rich Hickey" "rich@hickey.com")
      mailables [contact employee]]
  (doseq [m mailables]
    (send-mail m "A normal email")))
