(ns cljs-numbers.test
  (:use [cljs-numbers.core :only [-add]]))

(defn is
  [x]
  (assert x))

(defn run-tests
  []
  (is (= 7 (-add 2 5)))
  (.log js/console "Tests pass"))