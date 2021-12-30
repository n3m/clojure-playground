(ns api-rest.core

  (:require [compojure.core :refer :all]

            [compojure.route :as route])

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


(defroutes app
   (GET "/cars" [] (clojure/data/json cars) )
   (GET "/car/:id" [{{id :id} :params}] (clojure/data/json cars) )
  (route/not-found {:message "Not found" :status 404} )
)

(ring-jetty/run-jetty app {:port 3000

                           :join? false})

(defn -main

  [& args])