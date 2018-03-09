hyperion.gae
============

A Hyperion implementation for the Google App Engine datastore

Two additions - ancestor relationships and indexing options

## Parents

You can specify ancestory relationships using the `:parent` key

```clojure
(defn test-db [req]
  (let [parent (:key (ds/save {:kind "test"}))
        child (ds/save {:kind "test"} {:parent parent})
        updated (assoc child :another "property")]
   {:body (str (ds/save updated))}))
```

## Indexing

All properties are indexed by default.

If you want only some of your properties indexed, add a `__indexed` 
field. You can specify a :default in your entity definition too.

For example, the user entity below all properties are unindexed except for `email` and `id.`

```clojure
(defentity user
            [__indexed :default [:email :id]]
            [given_name]
            [email]
            [locale]
            [name]
            [family_name]
            [id])
```


## Types

### Natively Supported

* `java.lang.Boolean`
* `java.lang.Long`
* `java.lang.Double`
* `java.lang.String`

### Supported by Packer/Unpacker

* `java.lang.Byte` (if type is not specified, GAE unpacks `Byte`s as `Integer`s)
* `java.lang.Short` (if type is not specified, GAE unpacks `Short`s as `Long`s)
* `java.lang.Integer` (if type is not specified, GAE unpacks `Integer`s as `Long`s)
* `java.lang.Float` (if type is not specified, GAE unpacks `Float`s as `Double`s)
* `java.lang.Character`
* `clojure.lang.Keyword`

## License

Copyright © 2012 8th Light, Inc.

Distributed under the Eclipse Public License, the same as Clojure.
