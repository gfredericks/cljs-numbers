(ns cljs-numbers.test
  (:use [cljs-numbers.core :only [-add bigint]]))

(defn is
  [x]
  (assert x))

(defn run-tests
  []
  (let [d2 2
        d5 5
        d7 7
        i2 (bigint 2)
        i5 (bigint 5)
        i7 (bigint 7)]
    
    (is (= 7 (-add 2 5)))
    (is (= "7" (str (-add i2 i5))))
    (is (= d7 (-add d2 i5))))
  
  (.log js/console "Tests pass"))