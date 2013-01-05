(defproject cljs-numbers "1.0.0"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.5.0-RC1"]
                 [compojure "1.0.3"]]
  :plugins [[lein-cljsbuild "0.2.10"]
            [lein-ring "0.7.0"]]
  :ring {:handler cljs-numbers.repl/handler}
  :cljsbuild {:builds [{:source-path "src-cljs"
                        :compiler {:output-to "main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       {:source-path "test-cljs"
                        :compiler {:output-to "test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})