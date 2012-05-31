(defproject cljs-numbers "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]]
  :plugins [[lein-cljsbuild "0.1.10"]]
  :cljsbuild {:builds [{:source-path "src-cljs"
                        :compiler {:output-to "main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})