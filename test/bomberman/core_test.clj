(ns bomberman.core-test
  (:require [clojure.test :refer :all]
            [bomberman.core :refer :all]))

(deftest test-parse-req
  (testing "parse-req"
    (is (=
         (parse-req {:text "bulldog 5"
                     :user_name "otaviopace"})
         {:race "bulldog"
          :quantity "5"
          :user-name "otaviopace"}))))

(deftest test-build-welcome-message
  (testing "build-welcome-message"
    (is (=
         (build-welcome-message "bulldog" "otaviopace" "5")
         "bulldog bomb for @otaviopace x5"))))

(deftest test-create-dogs-url
  (testing "create-dogs-url"
    (is (=
         (create-dogs-url "bulldog")
         "https://dog.ceo/api/breed/bulldog/images/random"))))
