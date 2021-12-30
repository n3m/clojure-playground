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
           }))

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
      
      (dosync (swap! cars assoc (keyword (str id)) document))
    
      {:status 200

       :body document})
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