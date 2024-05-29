(defproject web-server "0.1.0-SNAPSHOT"
  :description "Web-server for biniko.me blog"
  :url "http://blog.biniko.me"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :min-lein-version "2.10.0"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.babashka/http-server "0.1.12"]
                 [org.clojure/tools.cli "1.1.230"]]

  :main ^:skip-aot web-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jar-name "web-server.jar"
                       :uberjar-name "web-server-standalone.jar"
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
