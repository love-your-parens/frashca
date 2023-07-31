(ns examples.routing
  (:require [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend.controllers :as rfc]))

(defn greeter
  ([] (greeter nil))
  ([name]
   (when-let [el (js/document.getElementById "greeting")]
     (set! (.-innerHTML el)
           (when name (str "Hello " name "!"))))))

(defonce match (atom nil))

(def routes
  (rf/router
   [["/" {:name ::index 
          :controllers [{:start #'greeter}]}]
    ["/:name" {:name ::greeting
               :controllers [{:identity #'identity
                              :start (fn [match]
                                       (greeter (get-in match [:path-params :name])))}]}]]))

(defn init-routes! []
  (rfe/start!
   routes
   (fn [new-match]
     (swap! match
            (fn [old-match]
              (when new-match
                (assoc new-match :controllers
                       (rfc/apply-controllers (:controllers old-match) new-match))))))
   {:use-fragment true}))

(defn greet-me!
  [name]
  (rfe/navigate ::greeting {:path-params {:name name}}))

(defn vec->dom
  [[tag params content]]
  (let [el (js/document.createElement (name tag))]
    (doseq [[k v] (or params {})]
      (.setAttribute el (name k) v))
    (set! (.-innerHTML el) content)
    el))

(defn init-layout! []
  (let [container (js/document.getElementById "app")
        navigation (vec->dom [:ul])
        links [[:a {:href (rfe/href ::index)} "Index"]
                  ;; URL coded by hand:
               [:a {:href "/#/Koziołek"} "Koziołek"]
                  ;; URL generated from a route:
               [:a {:href (rfe/href ::greeting {:name "Matołek"})} "Matołek"]]]
    (doseq [link links]
      (let [li (vec->dom ["li"])
            a (vec->dom link)]
        (.appendChild li a)
        (.appendChild navigation li)))
    (.appendChild container navigation)
    (let [el (vec->dom [:button {:type "button"} "Greet me!"])]
      (.addEventListener el "click" #(greet-me! (js/prompt "What's your name?")))
      (.appendChild container el))
    (.appendChild container (vec->dom [:p {:id "greeting"}]))))

(comment
  (init-routes!)
  (init-layout!) 
  (rfe/navigate ::greeting {:path-params {:name "Konrad"}})
  )