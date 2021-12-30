(ns api-rest.routes

  (:use compojure.core)

  (:require [compojure.handler :as handler]
            
            [clojure.pprint :as pprint]

            [compojure.route]


            [ring.middleware.json :as middleware]))

(def cars (atom {:0  {:id 0

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

               :engine "V8"}}))

(defn handle-query-one-car [id]

  (let [car ((keyword id) @cars nil)]

    (if (not (nil? car))

      {:status 200

       :body car}

      {:status 404

       :body {:error "Car not found"}})))

(defn handle-query-all-cars []

  {:status 200

   :body @cars})

(defn handle-add-car [jsonBody]

  (let [id (count @cars)]

    (let [document (assoc jsonBody "id" id)]
      
      (pprint/pprint document)
      
      (dosync (swap! cars assoc (keyword (str id)) document))
    
      {:status 200

       :body @cars})
    )

  )

(defroutes router

  (GET "/" [] "API Testing")

  (GET "/cars" [] (handle-query-all-cars))

  (GET "/cars/:id" [id] (handle-query-one-car id))

  (POST "/cars" {body :body} (handle-add-car body))

  (compojure.route/not-found "Not Found"))

(def app

  (-> (handler/api router)

      (middleware/wrap-json-body)

      (middleware/wrap-json-response)))