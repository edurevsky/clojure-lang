(ns playground.cljatom)

(defn mutable-vector
  []
  (atom []))

(defn my-conj
  [vector value]
  (let [size (count vector)]
    (if (< size 10) (conj vector value) vector)))

(defn with-even-numbers
  [vector]
  (let [predicates [number? even?]]
    (->> vector
         (filter (apply every-pred predicates))
         vec)))

(def my-vec (mutable-vector))

(println @my-vec)
(swap! my-vec conj "Hello")

(println @my-vec)
(swap! my-vec my-conj "world")
(println @my-vec)

(doseq [t (range 10)]
  (swap! my-vec my-conj t)
  (println @my-vec))

(println (deref my-vec))

(swap! my-vec with-even-numbers)
(println @my-vec)

(reset! my-vec {:something 42})
(println @my-vec)
