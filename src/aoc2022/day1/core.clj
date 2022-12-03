(ns aoc2022.day1.core
  (:require [clojure.java.io :as io]))

(def input
  (line-seq (io/reader (io/resource "day1_input.txt"))))

(defn to-int
  [row]
  (map #(Integer/parseInt %) row))

(defn split-calories
  [input]
  (take-nth 2 (partition-by #(= "" %) input)))

(defn input-calories->int
  [input]
  (map to-int (split-calories input)))

(defn calories-per-elf
  [input]
  (map #(reduce + %) (input-calories->int input)))

(defn top-3-calories
  [input]
  (take-last 3 (sort (calories-per-elf input))))

(defn part1
  [input]
  (apply max (calories-per-elf input)))

(defn part2
  [input]
  (apply + (top-3-calories input)))