(ns web-server.core
  (:gen-class)
  (:require
   [babashka.http-server :as http]
   [clojure.tools.cli :refer [parse-opts]]))

(def ^:private CLI-OPTIONS
  [["-p" "--port PORT" "Port number"
    :default 80
    :parse-fn #(Integer/parseInt %)]
   ["-d" "--dir DIR" "Directory to serve"
    :default "public"]])

(defn start-server
  [port dir]
  (http/serve {:port port :dir dir :header {}}))

(defn -main
  [& args]
  (let [{:keys [options]} (parse-opts args CLI-OPTIONS)
        {:keys [port dir]} options]
    (start-server port dir)))
