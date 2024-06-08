(ns web-server.core
  (:gen-class)
  (:require
   [clojure.tools.cli :refer [parse-opts]]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :refer [files not-found]]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.response :refer [redirect]]))

(def ^:private DEFAULT-PORT 8080)
(def ^:private STATIC-DIR "public")
(def ^:private INDEX-FILE "index.html")

(def ^:private CLI-OPTIONS
  [["-p" "--port PORT" "Port number"
    :default DEFAULT-PORT
    :parse-fn #(Integer/parseInt %)]
   [nil "--profile PROFILE" "Profile"
    :default :prod
    :parse-fn keyword]])

(defroutes routes
  (GET "/" [] (redirect INDEX-FILE))
  (files "/" {:root STATIC-DIR})
  (not-found "<h1>Not Found</h1>"))

(defn- start-server
  [port profile]
  (println "Starting server on port" port "with profile" profile)
  (let [dev-profile? (= profile :dev)
        app (cond-> #'routes
              dev-profile? wrap-reload)]
    (run-jetty app {:port port :join? (not dev-profile?)})))

(defn -main
  "Entry point of the application"
  [& args]
  (let [{:keys [options]} (parse-opts args CLI-OPTIONS)
        {:keys [port profile]} options]
    (start-server port profile)))

(defn -dev-main
  "Entry point of the application in dev profile"
  [& _]
  (start-server DEFAULT-PORT :dev))






