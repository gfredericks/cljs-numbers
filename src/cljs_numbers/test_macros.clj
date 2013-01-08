(ns cljs-numbers.test-macros
  "Mimicking clojure.test by hand until I find a good cljs lib for this."
  (:use [clojure.walk :only [postwalk]]))

(defmacro is
  [x]
  (list 'assert x))

(defmacro are
  [names expectation & examples]
  (cons 'do
        (for [vals (partition (count names) examples)]
          (list 'is
                (list 'let
                      (vec (mapcat list names vals))
                      expectation)))))