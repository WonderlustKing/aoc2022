(ns aoc2022.day2.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input
  (line-seq (io/reader (io/resource "day2_input.txt"))))

(defn input->keywords
  [row]
  (->>
    (str/split row #" ")
    (map keyword)))

(def points-per-choice
  {:X 1
   :Y 2
   :Z 3})

(def points-per-result
  {:WIN 6
   :DRAW 3
   :LOST 0})

(defn win?
  [opponent-choice player-choice]
  (or
    (and (= :X player-choice) (= :C opponent-choice))
    (and (= :Z player-choice) (= :B opponent-choice))
    (and (= :Y player-choice) (= :A opponent-choice))))

(defn draw?
  [opponent-choice player-choice]
  (or
    (and (= :C opponent-choice) (= :Z player-choice))
    (and (= :B opponent-choice) (= :Y player-choice))
    (and (= :A opponent-choice) (= :X player-choice))))

(defn points-per-round
  [opponent-choice player-choice]
  (cond
    (draw? opponent-choice player-choice) (+ (:DRAW points-per-result) (get points-per-choice player-choice))
    (win? opponent-choice player-choice) (+ (:WIN points-per-result) (get points-per-choice player-choice))
    :else (+ (:LOST points-per-result) (get points-per-choice player-choice))))

(defn part1
  [input]
  (reduce + (map #(let [[o p] (input->keywords %)]
                    (points-per-round o p))
                 input)))


;;
;;
;; PART 2
;;
;;

(defn do-lose
  [opponent-move]
  (cond
    (= opponent-move :A) :Z
    (= opponent-move :B) :X
    :else :Y))

(defn do-draw
  [opponent-move]
  (cond
    (= opponent-move :A) :X
    (= opponent-move :B) :Y
    :else :Z))

(defn do-win
  [opponent-move]
  (cond
    (= opponent-move :A) :Y
    (= opponent-move :B) :Z
    :else :X))

(defn next-move
  [opponent-move player-move]
  (cond
    (= :X player-move) (do-lose opponent-move)
    (= :Y player-move) (do-draw opponent-move)
    :else (do-win opponent-move)))

(defn part2
  [input]
  (reduce + (map #(let [[o p] (input->keywords %)]
                    (points-per-round o (next-move o p)))
                 input)))
