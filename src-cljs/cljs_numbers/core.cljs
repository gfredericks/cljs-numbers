(ns cljs-numbers.core
  (:refer-clojure :exclude [+ - * / < > <= >= =])
  (:require [goog.math.Integer :as int]
            [cljs.core :as cljs]))

(defn bigint?
  [x]
  (instance? goog.math.Integer x))

(defn bigint
  [x]
  (if (bigint? x)
    x
    (int/fromString (str x))))

(defprotocol Add
  (-add [x y]))
(defprotocol AddWithDouble
  (-add-with-double [x y]))
(defprotocol AddWithInteger
  (-add-with-integer [x y]))
(defprotocol AddWithRatio
  (-add-with-ratio [x y]))

(defprotocol Multiply
  (-multiply [x y]))
(defprotocol MultiplyWithDouble
  (-multiply-with-double [x y]))
(defprotocol MultiplyWithInteger
  (-multiply-with-integer [x y]))
(defprotocol MultiplyWithRatio
  (-multiply-with-ratio [x y]))

(defprotocol Invert
  (-invert [x]))

(defprotocol Negate
  (-negate [x]))

(defprotocol Ordered
  (-compare [x y]))
(defprotocol CompareToDouble
  (-compare-to-double [x y]))
(defprotocol CompareToInteger
  (-compare-to-integer [x y]))
(defprotocol CompareToRatio
  (-compare-to-ratio [x y]))

(extend-type number
  Add
  (-add [x y] (-add-with-double y x))
  AddWithDouble
  (-add-with-double [x y]
    (cljs/+ x y))
  ;; I have a hard time reasoning about whether or not this is necessary
  AddWithInteger
  (-add-with-integer [x y]
    (-add-with-double y x))
  Multiply
  (-multiply [x y] (-multiply-with-double y x))
  MultiplyWithDouble
  (-multiply-with-double [x y]
    (cljs/* x y))
  MultiplyWithInteger
  (-multiply-with-integer [x y]
    (-multiply (bigint x) y))
  Negate
  (-negate [x] (cljs/- x))
  Ordered
  (-compare [x y] (-compare-to-double y x))
  CompareToDouble
  (-compare-to-double
    [x y]
    (cljs/compare x y))
  CompareToInteger
  (-compare-to-integer
    [x y]
    (-compare-to-integer (bigint x) y)))

;; Is this a good idea?
(comment
  (extend-type default
    AddWithInteger
    (-add-with-integer [x y] (-add y x))))

(declare ratio)

(extend-type goog.math.Integer
  Add
  (-add [x y] (-add-with-integer y x))
  AddWithDouble
  (-add-with-double [x y]
    (-add (.toNumber x) y)) ;; should we do (+ ...) directly here?
  AddWithInteger
  (-add-with-integer [x y]
    (.add x y))
  Multiply
  (-multiply [x y]
    (-multiply-with-integer y x))
  MultiplyWithDouble
  (-multiply-with-double [x y]
    (-multiply x (bigint y)))
  MultiplyWithInteger
  (-multiply-with-integer [x y]
    (.multiply x y))
  Negate
  (-negate [x] (.negate x))
  Invert
  (-invert [x] (ratio 1 x))
  Ordered
  (-compare [x y] (-compare-to-integer y x))
  CompareToDouble
  (-compare-to-double
    [x y]
    (-compare-to-integer x (bigint y)))
  CompareToInteger
  (-compare-to-integer
    [x y]
    (.compare x y)))


(defn gcd
  [x y]
  (if (zero? y)
    x
    (recur y (mod x y))))


(deftype Ratio
  ;; "Ratios should not be constructed directly by user code; we assume n and d are
  ;;  canonical; i.e., they are coprime and at most n is negative."
  [n d]
  Add
  (-add [x y] (-add-with-ratio y x))
  Negate
  (-negate [x]
    (Ratio. (-negate n) d))
  Invert
  (-invert [x]
    (cond
     (= n 0)
     (throw "Cannot divide by zero")

     (< n 0)
     (Ratio. (- d) (- n))

     :else
     (Ratio. d n)))
  AddWithRatio
  (-add-with-ratio [x y]
    (let [d' (* d (.-d y))
          n' (+ (* n (.-d y)) (* d (.-n y)))
          x (gcd d' n')]
      (Ratio. (/ n' x) (/ d' x))))
  MultiplyWithRatio
  (-multiply-with-ratio [x y]
    (let [n' (* n (.-n y))
          d' (* d (.-d y))
          x (gcd n' d')]
      (Ratio. (/ n' x) (/ d' x)))))

(defn /
  [x y]
  :STUB)

(defn ratio
  ([x] (ratio x 1))
  ([x y]
     (let [x (bigint x),
           y (bigint y),
           d (gcd x y)]
       ;; TODO: only x can be negative
       (Ratio. (/ x d) (/ y d)))))

(defn ratio?
  [x]
  (instance? Ratio x))

(defn double?
  [x]
  (number? x))

(defn =
  ([x] true)
  ([x y] (cljs/= 0 (-compare x y)))
  ([x y z & more]
     (every? (partial apply =) (partition 2 (list* x y z more)))))

(defn +
  ([] 0)
  ([x] x)
  ([x y] (-add x y))
  ([x y z & more] (reduce -add (list* x y z more))))

(defn *
  ([] 0)
  ([x] x)
  ([x y] (-multiply x y))
  ([x y z & more] (reduce -multiply (list* x y z more))))