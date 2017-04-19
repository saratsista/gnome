(ns gnome.crypto
 (:require [clojure.string :refer [split]]
           [environ.core :refer [env]])
 (:import [javax.crypto.spec SecretKeySpec]
          [javax.crypto Cipher]
          [org.apache.commons.codec.binary Base64]
          [org.apache.commons.codec.digest DigestUtils]))

(def algorithm (first (split (env :transformation) #"/")))

; store both creds-key and algorithm in environment variables
(defn- secret-key
  []
  (let [creds-key (bytes (byte-array (map (comp byte int) (env :gnome-key))))
        sha1-digest (. DigestUtils sha1 creds-key)]
    (new SecretKeySpec (. Arrays copyOf creds-key 16) algorithm)))

(defn decrypt
  []
  (let [cipher (. Cipher (getInstance algorithm))
        cipher-text (slurp "resources/creds.txt")
        _ (. cipher (init (. Cipher DECRYPT_MODE) (secret-key)))]
    (println  (. cipher (doFinal  (. Base64 (decodeBase64 cipher-text)))))))

(defn encrypt
  []
  (let [cipher (. Cipher getInstance algorithm)
        cipher-text (bytes (byte-array (map (comp byte int) (slurp "resources/creds.json"))))
        _ (. cipher (init (. Cipher ENCRYPT_MODE) (secret-key)))]
    (println (. cipher (doFinal (. Base64 (encodeBase64String cipher-text)))))))
