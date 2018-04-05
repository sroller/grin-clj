(ns grin-clj.core
  (:require [clj-http.client :as http])
  (:require [clojure.data.json :as json])
  (:gen-class))

(def server "http://kiwis.grandriver.ca")
(def url "/KiWIS/KiWIS")
(def parameters "service=kisters&type=queryServices&request=getStationList&datasource=0&format=json&station_name=A*")

(defn request-str
  []
  (str server url "?" parameters))

(defn get-station-list
  "get list of available stations"
  []
  (json/read-str (:body (http/get (request-str))) :key-fn keyword))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
