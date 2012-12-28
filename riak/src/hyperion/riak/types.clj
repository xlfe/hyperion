(ns hyperion.riak.types
  (:require [chee.coerce :refer [->string ->int AsString AsInteger]]
            [hyperion.coerce :refer [->float ->double]]
            [hyperion.api :refer [pack unpack]])
  (:import  [java.math BigInteger]))

(defprotocol AsLong
  (->long [this]))

(extend-protocol AsLong
  java.lang.Integer
  (->long [this] (.longValue this))

  java.lang.Long
  (->long [this] this)

  java.lang.String
  (->long [this] (long (BigInteger. this)))

  nil
  (->long [this] nil)

  )

(extend-protocol AsInteger

  nil
  (->int [this] nil)

  )

(extend-protocol AsString

  Object
  (->string [this] (str this))

  nil
  (->string [this] nil)

  )

(defmethod pack java.lang.Integer [_ value]
  (->string value))

(defmethod unpack java.lang.Integer [_ value]
  (->int value))

(defmethod pack java.lang.Long [_ value]
  (->string value))

(defmethod unpack java.lang.Long [_ value]
  (->long value))

(defmethod pack java.lang.Float [_ value]
  (->string value))

(defmethod unpack java.lang.Float [_ value]
  (->float value))

(defmethod pack java.lang.Double [_ value]
  (->string value))

(defmethod unpack java.lang.Double [_ value]
  (->double value))