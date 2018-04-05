(ns grin-clj.core-test
  (:require [clojure.test :refer :all]
            [grin-clj.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (< 0 1))))

(deftest get-station-list-test
  (testing "getStationList"
    (println (class get-station-list)))
    (is (< 0 (count (keys (get-station-list))))))

