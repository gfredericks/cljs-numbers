(ns cljs-numbers.test-macros
  (:use [clojure.walk :only [postwalk]]))

(defmacro with-numerics
  [& forms]
  (cons 'do
        (postwalk (fn [x] (if (instance? clojure.lang.BigInt x)
                            `(cljs-numbers.core/bigint ~(str x))
                            x))
                  forms)))