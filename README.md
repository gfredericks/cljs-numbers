# cljs-numbers

## This library is deprecated

Because I made a better one that does the same thing: [exact](https://github.com/gfredericks/exact).

# The old README

cljs-numbers is currently a library for doing exact arithmetic
(big-integers and ratios) in ClojureScript. No assumptions should be
made about its performance or correctness.

Maybe in the future (if needed and wanted) it could include a full
numeric tower.

## Getting it

In the `project.clj`:

``` clojure
[com.gfredericks/cljs-numbers "0.1.2"]
```

## Usage

The `cljs-numbers.core` namespace gives its own versions of most of the
core math functions. So if you want to use them nakedly, you'll have to
have a rather elaborate `ns` declaration to exclude the `clojure.core`
versions.

``` clojure
(ns foo.bar
  (:refer-clojure :exclude [+ - * / = < > <= >= zero? pos? neg?])
  (:require-macros [cljs-numbers.macros :refer [num-literals]])
  (:require [cljs-numbers.core :refer [+ - * / = < > <= >= bigint bigint?
                                       ratio? double? zero? pos? neg?]]
            ;; in case we need the normal stuff
            [cljs.core :as cljs]))

(num-literals
  (.log js/console (str (+ 84/7 4828948388888888888888838488441110N))))
```

## TODO

- Look into extending compiler instead of `num-literals`
- Make string versions of Ratio and Integer look nice

## Running Tests

Run `lein cljsbuild once`, then load `tests.html` in a browser.
