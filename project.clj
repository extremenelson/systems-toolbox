(defproject matthiasn/systems-toolbox "0.5.10"
  :description "Toolbox for building Systems in Clojure"
  :url "https://github.com/matthiasn/systems-toolbox"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj" "src/cljs" "src/cljc"]

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.reader "1.0.0-alpha1"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/core.match "0.3.0-alpha4" :exclusions [org.clojure/tools.analyzer.jvm]]
                 [org.ow2.asm/asm-all "5.0.4"]
                 [org.clojure/core.async "0.2.374" :exclusions [org.clojure/tools.reader]]
                 [org.clojure/tools.logging "0.3.1"]
                 [io.aviso/pretty "0.1.23"]
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"]]

  :plugins [[lein-codox "0.9.4"]
            [test2junit "1.2.1"]
            [lein-cljsbuild "1.1.2"]]

  :test2junit-output-dir ~(or (System/getenv "CIRCLE_TEST_REPORTS") "target/test2junit")

  :profiles {:dev {:dependencies [[org.clojure/tools.logging "0.3.1"]
                                  [ch.qos.logback/logback-classic "1.1.5" :exclusions [org.slf4j/slf4j-api]]
                                  [org.slf4j/jul-to-slf4j "1.7.16"]
                                  [org.slf4j/jcl-over-slf4j "1.7.16"]
                                  [org.slf4j/log4j-over-slf4j "1.7.16"]]}})
