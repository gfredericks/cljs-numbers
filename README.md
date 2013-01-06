# cljs-numbers

Currently a library for doing exact arithmetic (bigints and ratios) in ClojureScript.

Maybe in the future (if needed and wanted) a full numeric tower.

## Getting it

In the `project.clj`:

``` clojure
[com.gfredericks/cljs-numbers "0.1.1"]
```

## Usage

The `cljs-numbers.core` namespace gives its own versions of most of the
core math functions. So if you want to use them nakedly, youll have to
have a rather elaborate `ns` declaration to exclude the `clojure.core`
versions.

``` clojure
(ns foo.bar
  (:refer-clojure :exclude [+ - * / = < > <= >= zero? pos? neg?])
  (:require-macros [cljs-numbers.syntax-macros :refer [with-numeric-literals]])
  (:require [cljs-numbers.core :refer [+ - * / = < > <= >= bigint bigint?
                                       ratio? double? zero? pos? neg?]]
            ;; in case we need the normal stuff
            [cljs.core :as cljs]))

(with-numeric-literals
  (.log js/console (str (+ 84/7 4828948388888888888888838488441110N))))
```

## TODO

- Look into extending compiler instead of `with-numeric-literals`
- Make string versions of Ratio and Integer look nice

## Running Tests

Run `lein cljsbuild once`, then load `tests.html` in a browser.
