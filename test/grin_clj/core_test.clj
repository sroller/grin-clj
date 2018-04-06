(ns grin-clj.core-test
  (:require [clojure.test :refer :all]
            [grin-clj.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (< 0 1))))

(deftest string-array->keywords-test
  (testing "convert string array to array of keywords"
    (is (= (string-array->keywords '("a" "b" "c" "help")) [:a :b :c :help]))))

(deftest req-str-test
  (testing "build the correct request string for http/get"
    (let [function "getStationList"]
    (is (= (req-str function)
           (str "http://kiwis.grandriver.ca/KiWIS/KiWIS?service=kisters&type=queryServices&request=" function "&datasource=0&format=json"))))))

(deftest req-str-with-type-test
  (testing "build the correct request string for http/get"
    (let [function "getParameterList"]
    (is (= (req-str function {:parametertype_name "TA"})
           (str "http://kiwis.grandriver.ca/KiWIS/KiWIS?service=kisters&type=queryServices&request=" function "&datasource=0&format=json&parametertype_name=TA"))))))

(deftest get-station-list-test
  (testing "getStationList"
    (is (< 0 (count (keys (get-station-list)))))))

(deftest get-parameter-list-test
  (testing "getParameterList - all stations with all parameters"
    (is (< 0 (count (get-parameter-list))))))

(deftest get-parameter-list-with-type-name-test
  (testing "getParameterList - all stations with specific parameters"
    (is (< 0 (count (get-parameter-list "WT"))))))
