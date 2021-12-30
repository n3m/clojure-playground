(ns api-rest.core

  (:require [ring.adapter.jetty :as ring-jetty]

            [muuntaja.core :as m]

            [reitit.ring :as ring]

            [reitit.ring.middleware.muuntaja :as muuntaja])

  (:gen-class))

(def cars {:0  {:id 0

                :name "Ford"

                :price "100000"

                :year "2000"

                :color "red"

                :type "car"

                :brand "Ford"

                :model "Mustang"

                :engine "V8"}

           :1 {:id 1

               :name "Audi"

               :price "200000"

               :year "2010"

               :color "blue"

               :type "car"

               :brand "Audi"

               :model "A4"

               :engine "V8"}

           :2 {:id 2

               :name "BMW"

               :price "300000"

               :year "2010"

               :color "black"

               :type "car"

               :brand "BMW"

               :model "X5"

               :engine "V8"}

           :3 {:id 3

               :name "Mercedes"

               :price "400000"

               :year "2010"

               :color "white"

               :type "car"

               :brand "Mercedes"

               :model "S-Class"

               :engine "V8"}

           :4 {:id 4

               :name "Toyota"

               :price "500000"

               :year "2010"

               :color "green"

               :type "car"

               :brand "Toyota"

               :model "Corolla"

               :engine "V8"}

           :5 {:id 5

               :name "Honda"

               :price "600000"

               :year "2010"

               :color "yellow"

               :type "car"

               :brand "Honda"

               :model "Civic"

               :engine "V8"}

           :6 {:id 6

               :name "Nissan"

               :price "700000"

               :year "2010"

               :color "red"

               :type "car"

               :brand "Nissan"

               :model "Sentra"

               :engine "V8"}

           :7 {:id 7

               :name "Opel"

               :price "800000"

               :year "2010"

               :color "blue"

               :type "car"

               :brand "Opel"

               :model "Astra"

               :engine "V8"}})

(defn handle-query-one-car [{requestParams :path-params}]

  (def paramId (get requestParams :id))

  (let [car ((keyword paramId) cars nil)]

    (if (not (nil? car))

      {:status 200

       :body car}

      {:status 404

       :body {:error "Car not found"}})))

(defn handle-query-all-cars [_]

  {:status 200

   :body cars})

(defn handle-add-car [{car :body}]

  (clojure.pprint/pprint car)

  {:status 200

   :body "(assoc id car cars)"})

(def app

  (ring/ring-handler

   (ring/router

    ["/"

     ["car/:id" {:get handle-query-one-car}]

     ["cars" {:get handle-query-all-cars}]

     ["car-add" {:post handle-add-car}]]

    {:data {:muuntaja m/instance

            :middleware [muuntaja/format-middleware]}})))

(ring-jetty/run-jetty app {:port 3000

                           :join? false})

(defn -main

  [& args])