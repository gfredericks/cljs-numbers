(defproject cljs-numbers "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.0.3"]]
  :plugins [[lein-cljsbuild "0.2.7"]]
  :min-lein-version "2.0.0"
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds [{:source-path "src-cljs"
                        :compiler {:output-to "main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       {:source-path "test-cljs"
                        :compiler {:output-to "test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})