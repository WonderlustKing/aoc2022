(ns aoc2022.day3.core
  (:require [clojure.java.io :as io]
            [clojure.set :as set]))

(def input
  (line-seq (io/reader (io/resource "day3_input.txt"))))

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(def a-z-points
  (zipmap (char-range \a \z) (range 1 27)))

(def A-Z-points
  (zipmap (char-range \A \Z) (range 27 53)))

(defn find-same-item-type
  [rucksack]
  (let [size (/ (count rucksack) 2)
        first-half (take size rucksack)
        second-half (take-last size rucksack)]
    (first (set/intersection (set first-half) (set second-half)))))

(defn get-item-type-point
  [item-type]
  (or (get a-z-points item-type) (get A-Z-points item-type)))

(defn part1
  [input]
  (->> (map find-same-item-type input)
       (map get-item-type-point)
       (apply +)))


;;---------
;;---------
;; PART 2
;;---------
;;---------

(defn find-same-item-type-in-group
  [[a b c]]
  (first (set/intersection (set a) (set b) (set c))))

(defn part2
  [input]
  (let [partitioned-input (partition 3 input)]
    (->> (map find-same-item-type-in-group partitioned-input)
         (map get-item-type-point)
         (apply +))))


