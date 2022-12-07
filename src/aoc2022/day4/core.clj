(ns aoc2022.day4.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(def input
  (line-seq (io/reader (io/resource "day4_input.txt"))))

(defn split-input
  [input]
  (->> (map #(str/split % #",") input)
       (map (fn [[a b]]
              (->> (mapcat #(str/split % #"-") [a b])
                   (map #(Integer/parseInt %)))))))

(defn fully-contains?
  [[a b c d]]
  (let [pair1 (set (range a (inc b)))
        pair2 (set (range c (inc d)))]
    (or (set/subset? pair1 pair2)
        (set/subset? pair2 pair1))))

(defn part1
  [input]
  (->> (split-input input)
       (filter fully-contains?)
       count))

;;-------
;;-------
;; PART 2
;;-------
;;-------

(defn overlap-at-all?
  [[a b c d]]
  (let [pair1 (set (range a (inc b)))
        pair2 (set (range c (inc d)))]
    (some pair1 pair2)))

(defn part2
  [input]
  (->> (split-input input)
       (filter overlap-at-all?)
       count))