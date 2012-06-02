(ns cljs-numbers.test-macros
  (:use [clojure.walk :only [postwalk]]))

(defmacro is
  [x]
  (list 'assert x))

(defmacro are
  [names expectation & examples]
  (list 'doseq [names (vec (map vec (partition (count names) examples)))]
        (list `is expectation)))