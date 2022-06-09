(ns playground.core
  (:use clojure.pprint)
  (:import (java.time LocalDateTime)))

(println
  (if nil "true" "false"))

(defn cool-function
  [x y]
  (let [result (+ x y)]
    (if (even? result)
      result
      (recur x (inc y)))))

(println (cool-function 1 0))

(println (System/currentTimeMillis))

(let [current-date (LocalDateTime/now)]
  (.getMonthValue current-date))

(let [message "Hello World"]
  (->> message
       (.split #"\[a-zA-Z]+ ")
       vec
       println))

(do
  (println "begin")
  (Thread/sleep 1000)
  (println "end"))


