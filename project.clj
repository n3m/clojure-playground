(defproject api-rest "0.1.0-SNAPSHOT"
  :description "API-REST CloJure Playground by AEMN"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-jetty-adapter "1.9.4"]
                 [metosin/reitit "0.5.15"]
                 [metosin/muuntaja "0.6.8"]
                 [ring/ring-json "0.5.1"]]

  :main ^:skip-aot api-rest.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
