(ns playground.defmulti
  (:use clojure.pprint))

(defn percentage
  [p]
  (/ p 100))

(def my-request {:items
                 {:t-shirt   {:price 40 :quantity 1}
                  :coffee-cup {:price 2 :quantity 2}
                  :sock      {:price 4 :quantity 6}}})



(defn item-total
  "Retuns the price * value of an item"
  [item]
  (* (:price item) (:quantity item)))

(defn items-total
  [items]
  (map item-total items))

(defn request-total
  [request]
  (let [items (vals (:items request))]
    (reduce + (items-total items))))

(defn item-quantity
  [item]
  (:quantity item))

(defn items-quantity
  [items]
  (map item-quantity items))

(defn total-items
  [request]
  (let [items (vals (:items request))]
    (reduce + (items-quantity items))))

(defn complete-request
  [request]
  {:total          (request-total request)
   :items-quantity (total-items request)
   :request request})


(defn discount-fn
  [request]
  (let [total          (:total request)
        items-quantity (:items-quantity request)]
    (if
      (and (> total 200) (> items-quantity 5))
      :twenty-percent
      :ten-percent)))


(defmulti discount-percentage discount-fn)

(defmethod discount-percentage :twenty-percent
  [request]
  0.2)

(defmethod discount-percentage :ten-percent
  [request]
  0.1)

(let [complete-request  (complete-request my-request)
      discount-p        (discount-percentage complete-request)
      total             (:total complete-request)
      discount-amount   (* total discount-p)
      new-request-total (- total discount-amount)]
  (println "Value of the request with discount applied: " new-request-total))



(comment
  (->> my-request
       complete-request
       discount-fn
       discount-percentage))

