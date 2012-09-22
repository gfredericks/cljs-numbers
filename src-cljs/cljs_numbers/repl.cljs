(ns cljs-numbers.repl
  (:require [clojure.browser.repl :as repl]))

(js/setTimeout #(repl/connect "http://localhost:9000/repl") 300)