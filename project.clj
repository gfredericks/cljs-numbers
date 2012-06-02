(defproject cljs-numbers "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [compojure "1.0.3"]]
  :plugins [[lein-cljsbuild "0.1.10"]
            [lein-ring "0.7.0"]]
  :ring {:handler cljs-numbers.repl/handler}
  :cljsbuild {:builds [{:source-path "src-cljs"
                        :compiler {:output-to "main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})