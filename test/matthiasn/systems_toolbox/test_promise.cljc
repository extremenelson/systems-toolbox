(ns matthiasn.systems-toolbox.test-promise

  "Provide a promise-like experience for testing."

  #?(:cljs (:require-macros [cljs.core.async.macros :refer [go]]))
  (:require
   #?(:clj  [clojure.test :refer [is]]
      :cljs [cljs.test :refer-macros [async is]])
   #?(:clj  [clojure.core.async :refer [go alts! <!! timeout]]
      :cljs [cljs.core.async :refer [alts! take! timeout]])))

(defn test-async
  "Asynchronous test awaiting ch to produce a value or close. Makes use of cljs.test's facility
  for async testing.
  Borrowed from http://stackoverflow.com/questions/30766215/how-do-i-unit-test-clojure-core-async-go-macros"
  [ch]
  #?(:clj (<!! ch)
     :cljs (async done (take! ch (fn [_] (done))))))

(defn test-within
  "Asserts that ch does not close or produce a value within ms. Returns a channel from which the value
  can be taken. Also borrowed from stackoverflow comment above."
  [ms ch]
  (go (let [t (timeout ms)
            [v ch] (alts! [ch t])]
        (is (not= ch t)
            (str "Test should have finished within " ms "ms."))
        v)))

(defn w-timeout
  "Combines test-async and test-within to provide the deref functionality we expect from a promise.
  The first argument is the timeout in milliseconds, the second argument should be a go-block (which
  returns a channel with the return value of the block once completed). Then, in that go block, we can
  await the promise-chan to be delivered first before making any further assertions."
  [ms ch]
  (test-async
    (test-within ms ch)))
