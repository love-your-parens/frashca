# Frashca

Batteries included, minimal project template for simple ad-hoc web apps using ClojureScript. Intended for forking.

## Required Software

- [node.js (v6.0.0+)](https://nodejs.org/en/download/)
- [Java JDK (8+)](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [Open JDK (8+)](http://jdk.java.net/10/)

## User Guide

This template is based on the [Shadow CLJS quickstart template](https://github.com/shadow-cljs/quickstart-browser.git). Check it out to learn more about how to set-up and use Shadow CLJS. You may also refer to the [full documentation](https://github.com/thheller/shadow-cljs).

To start a local development server with all the bells and whistles (hot-reload etc.), simply run:

```bash
npx shadow-cljs watch app
```

This will start a local HTTP server on port `8020`.

To compile a production bundle, run:

```bash
npx shadow-cljs release app
```

## What's included

The template is opinionated in that it pre-selects a bunch of libraries to handle most of the common tasks. Bear in mind that this is only a scaffolding and you can mix & match as you like. Here's what's on tap:

### DOM 

[Hoplon](https://github.com/hoplon/hoplon) enables easy DOM manipulation and composition. What's more, it gives you spreadsheet-like reactive GUI via *Javelin*. See: `dev/examples/dom.cljs`.

### Routing

[Reitit](https://github.com/metosin/reitit/tree/master/doc/frontend) offers extensive routing capabilities. See: `dev/examples/routing.cljs` for interactive examples.

### Styling

Pending. Preferably something in the ballpark of TailwindCSS. No such solutions seem to integrate with dynamically generated DOM, but Tailwind itself might just be able to scan .cljs files. This wouldn't cover style-classes created dynamically, but that seems to be a rare enough use-case...

### State

For persistent application state, you can leverage the browser's Local Storage.
[storage-atom](https://github.com/alandipert/storage-atom) is included to facilitate this.

### Ajax.

Pending. [cljs-ajax](https://github.com/JulianBirch/cljs-ajax) is currently included.

### Development

[cljs-devtools](https://github.com/binaryage/cljs-devtools) allows for Clojure's native data structures to be represented in popular browsers' Web Inspectors, along with some other niceties.

[Rich Comment Tests](https://github.com/matthewdowney/rich-comment-tests) enables ad-hoc testing via Rich Comment Fields.

[Truss](https://github.com/taoensso/truss) offers simple assertions similar in spirit to `spec` etc., but less complex.

## Examples

You'll find a bunch of examples, divided by topic, under `dev/examples`. These are intended to be explored interactively using the REPL. Simply launch your Shadow CLJS server in *watch* mode and jack-in - preferably using your editor of choice. Then navigate to `http://localhost:8020` in your browser to view the page, and evaluate the examples at the REPL.