(ns cljs-numbers.test
  (:refer-clojure :exclude [+ * = < > <= >= / -])
  (:require-macros [cljs-numbers.test-macros :refer [is are]]
                   [cljs-numbers.macros :refer [num-literals]])
  (:require [cljs-numbers.core :refer [+ - * / = < > <= >= bigint bigint? ratio? double?]]))

(defn run-tests
  []
  (num-literals
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
         -33N 0 -33N
         67N 0 67N

         ;; integer + ratio
         7 5/2 19/2
         13/3 12 49/3
         -1 1/2 -1/2

         ;; ratio + ratio
         7/2 9/2 8
         3/4 4/5 31/20
         1/4 -1/3 -1/12
         7/8 0 7/8
         0 1/5 1/5)

    ;;
    ;; Multiplication
    ;;
    (are [x y z] (= z (* x y))
         ;; bigint * bigint
         3 3 9
         1 2 2
         7N 1N 7N

         100000000000000000000N
         700000000000000000000N
         70000000000000000000000000000000000000000N

         ;; bigint * ratio
         7 3/4 21/4
         -7/9 14 -98/9
         0 2/3 0
         -7/4 0N 0

         ;; ratio * ratio
         3/4 4/5 3/5
         4/5 6/7 24/35
         1/4 -2/3 -1/6
         -5/4 -4/5 1)

    ;; division
    (are [a b c] (= c (/ a b))
         7 4 7/4
         8 2 4
         -9 3 -3
         -9/4 15 -3/20
         1/2 1/2 1
         1/2 2 1/4
         1/3 3/4 4/9
         -1/3 3/4 -4/9
         0 17/18 0)

    (is (= 3/7 (- 1 4/7) (- 1 1/7 2/7 1/14 1/14)))

    (is (= (/ 19 2 3 4/5 6)
           95/144))

    ;; comparison
    (are [a b] (and (< a b)
                    (> b a)
                    (not (>= a b))
                    (not (<= b a))
                    (not (> a b))
                    (not (< b a))
                    (<= a b)
                    (>= b a)
                    (<= a a b b)
                    (>= b a a a))
         7 15
         -3 4
         -4 2
         -4/3 2
         -5 3/4
         -1 0
         -1/7 1/8
         1/8 1/7
         1/8 17
         900/3 901/2
         800 900
         800/3 900
         800/4 900/3
         -900 800
         -900/11 800/7
         -13/4 -7/4
         -4/7 -4/13)

    (is (not (< 1 3 2 4))) ;; failing test for a bug

    (is (< -7 -3/4 0 1 2 7/2 19284/7 3885792473484N))
    (is (> 19 18 17 16/7 1/843 0 -4928/75283922481 -3/4 -1 -2 -5 -88/7)))

  (.log js/console "Tests pass")
  :pass)