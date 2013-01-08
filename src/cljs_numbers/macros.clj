(ns cljs-numbers.macros
  (:use [clojure.walk :only [postwalk]]))

;; TODO: if the things becoming bigints are within double range
;; we could emit a long instead of a string -- I assume that is
;; more efficient.
(let [bigint #(list 'cljs-numbers.core/bigint (str %))]
  (def type-conversions
    {Long bigint
     Double identity
     clojure.lang.BigInt bigint
     java.math.BigInteger bigint
     clojure.lang.Ratio #(list 'cljs-numbers.core/ratio
                               (-> % numerator bigint)
                               (-> % denominator bigint))}))

(defn type-convert-form
  [form]
  (postwalk (fn [x] (if-let [emitter (-> x type type-conversions)]
                      (emitter x)
                      x))
            form))

(defmacro with-numeric-literals
  ([form]
     (type-convert-form form))
  ([form1 form2 & more]
     (cons 'do (map type-convert-form (list* form1 form2 more)))))

;; alias
(defmacro wnl [& forms] (cons `with-numeric-literals forms))