(ns grin-clj.core
  (:require [clj-http.client :as http])
  (:require [clojure.data.json :as json])
  (:gen-class))

(def server "http://kiwis.grandriver.ca")
(def url "/KiWIS/KiWIS")

(defn parameters
  "builds the parameter string for the web request"
  ([function]
  (str "service=kisters&type=queryServices&request=" function "&datasource=0&format=json"))
  ([function parameter-map]
   (let [k (first (keys parameter-map))
         v (k parameter-map)]
  (str "service=kisters&type=queryServices&request=" function
       "&datasource=0&format=json&"
       (name k) "=" v))))

(defn req-str
  ([function]
    (str server url "?" (parameters function)))
  ([function parameter-map]
    (str server url "?" (parameters function parameter-map))))

(defn string-array->keywords
  "convert an array of strings to an array of keywords"
  [array]
  (map #(keyword %) array))

(defn create-map
  "convert JSON result in map per station"
  [body]
  (let [header (string-array->keywords (first body))
        stations (rest body)]
    (map #(zipmap header %) stations)))

(defn web-request
  "request list from webserver for
  given function.
  return an sequence of maps"
  ([request]
  (let [request-str (req-str request)
        body (json/read-str (:body (http/get request-str)) :key-fn keyword)]
    (create-map body)))
  ([request parameter-map]
  (let [request-str (req-str request parameter-map)
        body (json/read-str (:body (http/get request-str)) :key-fn keyword)]
    (create-map body))))

(defn get-station-list
  "get list of available stations"
  []
  (web-request "getStationList"))

(defn get-parameter-list
  "get list of all stations with all possible parameters"
  ([]
   (web-request "getParameterList"))
  ([type-name]
   (web-request "getParameterList" {:parametertype_name type-name})))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
