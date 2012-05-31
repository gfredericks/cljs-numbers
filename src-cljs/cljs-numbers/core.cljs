(ns cljs-numbers.core)

(defprotocol Add
  (-add [x y]))

(defprotocol AddWithDouble
  (-add-with-double [x y]))

(extend-type number
  Add
  (-add [x y] (-add-with-double y x))
  AddWithDouble
  (-add-with-double [x y]
    (+ x y)))
