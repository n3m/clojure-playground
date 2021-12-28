(ns api-rest.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [muuntaja.core :as m]
            [reitit.ring :as ring]
            [reitit.ring.middleware.muuntaja :as muuntaja])
  (:gen-class))

(defn handle-query-one-car [_]
  {:status 200
   :body "query-one"})

(defn handle-query-all-cars [_]
  {:status 200
   :body "query-all"})

(defn handle-add-car [_]
  {:status 200
   :body "add-car"})

(defn handle-edit-car [_]
  {:status 200
   :body "edit-car"})

(defn handle-delete-car [_]
  {:status 200
   :body "delete-car"})

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     ["car" {:get handle-query-one-car}]
     ["cars" {:get handle-query-all-cars}]
     ["car" {:post handle-add-car}]
     ["car" {:put handle-edit-car}]
     ["car" {:delete handle-delete-car}]])))

(ring-jetty/run-jetty app {:port 3000
                           :join? false})

(defn -main
  [& args])