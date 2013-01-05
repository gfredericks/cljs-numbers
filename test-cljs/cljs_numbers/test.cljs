(ns cljs-numbers.test
  (:refer-clojure :exclude [+ * =])
  (:require-macros [cljs-numbers.test-macros :refer [is are]]
                   [cljs-numbers.syntax-macros :refer [with-numeric-literals]])
  (:use [cljs-numbers.core :only [+ * = bigint bigint? ratio? double?]]))

(defn run-tests
  []
  (with-numeric-literals
    ;;
    ;; numeric literals
    ;;
    (are [pred x] (pred x)
            bigint? 2
            bigint? 9N
            bigint? -32888888888888888888888888888888888888888444N
            ratio? 3/4
            ratio? -9/8888888888888888888888888888888888888888888
            double? 8.7
            double? 1.0)
    (are [pred x] (not (pred x))
            double? 2
            double? 9N
            double? 3/4
            double? -8888888888888888888888888888888888888888888/3
            ratio? 8.9
            ratio? 3
            ratio? 3N
            bigint? 8.2
            bigint? 8/3
            bigint? 77777777777.7)

    ;;
    ;; addition
    ;;
    (are [x y z] (= z (+ x y))

         ;; integer + integer
         2 5 7

         111111111311111111111111111111111111111411111111N
         222222222222222222222222922222222222222522222222N
         333333333533333333333334033333333333333933333333N

         -20N -44N -64N

         ;; integer + ratio
         7 5/2 19/2
         13/3 12 49/3
         -1 1/2 -1/2

         ;; ratio + ratio
         7/2 9/2 8
         3/4 4/5 31/20
         1/4 -1/3 -1/12)

    ;;
    ;; Multiplication
    ;;
    (are [x y z] (= z (* x y))
            ;; bigint * bigint
            3 3 9
            100000000000000000000N
            700000000000000000000N
            70000000000000000000000000000000000000000N))


  (.log js/console "Tests pass")
  :pass)