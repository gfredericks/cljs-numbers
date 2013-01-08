(defproject com.gfredericks/cljs-numbers "0.1.2"
  :description "Exact arithmetic in ClojureScript"
  :dependencies [[org.clojure/clojure "1.5.0-RC1"]]
  :plugins [[lein-cljsbuild "0.2.10"]]
  :source-paths ["src" "src-cljs"]
  :cljsbuild {:builds [{:source-path "src-cljs"
                        :compiler {:output-to "main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       {:source-path "test-cljs"
                        :compiler {:output-to "test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})