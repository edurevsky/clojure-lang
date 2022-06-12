(ns playground.dateable
  (:import (java.util Date)))

;Example from Sean Devlin's talk on protocols at Clojure Conj

(defprotocol Dateable
  (to-ms [this]))

(extend-type Date
  Dateable
  (to-ms
    [this]
    (.getTime this)))

(to-ms (Date.))
