(ns web-server.core
  (:gen-class)
  (:require
   [clojure.tools.cli :refer [parse-opts]]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :refer [files not-found]]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.util.response :refer [redirect]]))

(def ^:private DEFAULT-PORT 8080)
(def ^:private STATIC-DIR "public")
(def ^:private INDEX-FILE "index.html")

(def ^:private CLI-OPTIONS
  [["-p" "--port PORT" "Port number"
    :default DEFAULT-PORT
    :parse-fn #(Integer/parseInt %)]])

(defroutes routes
  (GET "/" [] (redirect INDEX-FILE))
  (files "/" {:root STATIC-DIR})
  (not-found "<h1>Not Found</h1>"))

(defn start-server
  ([] (start-server DEFAULT-PORT))
  ([port] (run-jetty routes {:port port :join? false})))

(defn -main
  [& args]
  (let [{:keys [options]} (parse-opts args CLI-OPTIONS)
        {:keys [port]} options]
    (start-server port)))
