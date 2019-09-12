(ns bomberman.core-test
  (:require [clojure.test :refer :all]
            [bomberman.core :refer :all]))

(deftest test-parse-req
  (testing "parse-req"
    (is (=
         (parse-req {
                     :text "bulldog 5"
                     :user_name "otaviopace"
                     })
         {
          :race "bulldog"
          :quantity "5"
          :user-name "otaviopace"
          }))))
