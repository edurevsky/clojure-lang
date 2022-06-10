(ns playground.cljretry
  (:use clojure.pprint))

(def my-atom (atom []))

(defn insert
  [vec val]
  (println "trying insert" val)
  (Thread/sleep (* (rand) 2000))
  (println "inserting" val)
  (conj vec val))

(defn insert!
  [vec val]
  (swap! vec insert val)
  (println "done insert" val))

(dotimes [n 5]
  (.start (Thread. (fn [] (insert! my-atom n)))))

(do
  (Thread/sleep 5000)
  (println @my-atom))