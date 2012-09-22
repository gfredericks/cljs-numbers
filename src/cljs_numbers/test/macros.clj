(ns cljs-numbers.test.macros
  (:require [clojure.walk :refer [postwalk]]))

(defmacro is
  [x]
  (list 'assert x))

(defmacro are
  [names expectation & examples]
  (list 'doseq [names (vec (map vec (partition (count names) examples)))]
        (list `is expectation)))