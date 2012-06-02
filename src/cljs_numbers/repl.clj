(ns cljs-numbers.repl
  "Basic ring app to make the repl-listen functionality work."
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler]))

(defroutes main-routes
  (GET "/" [] (slurp "test.html"))
  (GET "/main.js" [] (slurp "main.js"))
  (route/not-found "<h1>Page not found</h1>"))

(def handler (compojure.handler/api main-routes))