(ns short.hashid)

; defining some constants
(def -base-alphabet "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz")
(def -separators "jbD4w")
(def -separator-pattern (re-pattern (str "[" -separators "]")))
(def -alphabet (clojure.string/replace -base-alphabet -separator-pattern ""))

; private helper methods
(defn -shuffle-with-rnd
  "Return a random permutation of coll (uses java.util.Random)"
  [^java.util.Collection coll
   ^java.util.Random rand]
  (let [al (java.util.ArrayList. coll)]
    (java.util.Collections/shuffle al rand)
    (clojure.lang.RT/vector (.toArray al))))

(defn -shuffle-string
  [string
   seed]
  (let [coll (vec string)
        rnd  (java.util.Random. seed)]
    (clojure.string/join (-shuffle-with-rnd coll rnd))))

(defn -random-alphabet
  [seed]
  (-shuffle-string -alphabet seed))

(defn -generate-result-string
  [list]
  (clojure.string/join (reverse list)))

(defn -cons-with-nth-alphabet
  "Takes the numberTH element from the alphabet and concatenates it with result"
  [result
   number
   alphabet]
  (conj result (nth alphabet number)))

(defn -generate-hashid
  [id
   alphabet]
  (loop [result []
         number id]
    (if (< number 1)
      (-generate-result-string result)
      (recur (-cons-with-nth-alphabet result (rem number (count alphabet)) alphabet)
             (quot number (count alphabet))))))

(defn -decode-hashid
  [hashid
   alphabet]
  (reduce
    (fn [result number]
      (+ (* (count -alphabet) result) (.indexOf alphabet (str number))))
    0
    hashid))

; public methods
(defn encode
  "Encodes an id to a Hashid"
  [id]
  (let [seed (rand-int 1000)]
    (str (-generate-hashid seed -alphabet) (nth -separators (rand-int (count -separators))) (-generate-hashid id (-random-alphabet seed)))))

(defn decode
  "Decodes a Hashid to an id (Horner's method)"
  [hashid]
  (let [seed-hashid (first (clojure.string/split hashid -separator-pattern))
        id-hashid (last (clojure.string/split hashid -separator-pattern))
        seed (-decode-hashid seed-hashid -alphabet)]
    (-decode-hashid id-hashid (-random-alphabet seed))))

; TODO
; Im augenblick wird das alphabet jedes mal mit einem random seed reshuffled
; besser waere es ein default seed zu nehmen und und das alpahabet bei jedem zugriff neu zu -shuffle-string
; dadurch gibt es nurnoch eine id <-> hashid und nicht wie jetzt ne ganze menge




