(ns examples.dom
  (:require [hoplon.core :as h]
            [javelin.core :as j]
            [clojure.string :as s]))

(def container (js/document.getElementById "app"))
(def reactive-cell (j/cell ""))
(def linked-cell (j/cell= (s/upper-case reactive-cell)))

(.replaceChildren container
 (h/div 
  (h/h1 :class "title" :id "Greeting" "Hello!")
  (h/h2 "The markup is nice and intuitive.")
  (h/p "It's not Hiccup, but still very malleable. Observe:"
       (apply h/ul (map h/ol (range 5))))
  (h/div
   (h/p "Javelin integration gives us reactive GUI capabilities:")
   (h/textarea :disabled true :placeholder "This is a reactive element!"
               reactive-cell)
   (h/br)
   (h/button :click #(reset! reactive-cell
                             (js/prompt "Replace with what?"))
             "Replace text!")
   (h/p "Below value is linked to the reactive box:")
   (h/code linked-cell))))

(comment
  (reset! reactive-cell "Swapping this atom will trigger a reaction in the GUI!")
  (reset! reactive-cell nil)
  )