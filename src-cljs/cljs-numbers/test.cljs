(ns cljs-numbers.test
  (:refer-clojure :exclude [+])
  (:require-macros [cljs-numbers.test-macros :as m])
  (:use [cljs-numbers.core :only [+ = bigint bigint? ratio? double?]]))

(defn run-tests
  []
  (m/with-numeric-literals
    ;;
    ;; numeric literals
    ;;
    (m/are [pred x] (pred x)
           bigint? 2
           bigint? 9N
           bigint? -32888888888888888888888888888888888888888444N
           ratio? 3/4
           ratio? -9/8888888888888888888888888888888888888888888
           double? 8.7
           double? 1.0)
    (m/are [pred x] (not (pred x))
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
    (m/are [x y z] (= z (+ x y))

           ;; double + double
           2.0 5.0 7.0
           -3.0 8.0 5.0
           11.5 12.5 24.0
           700.0 800.0 1500.0

           ;; integer + integer
           2 5 7
           111111111311111111111111111111111111111411111111N
           222222222222222222222222922222222222222522222222N
           333333333533333333333334033333333333333933333333N
           -20N -44N -64N

           ))
  
  (.log js/console "Tests pass")
  :pass)