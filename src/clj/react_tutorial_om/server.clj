(ns react-tutorial-om.server
  (:require [ring.adapter.jetty :as jetty]
            [react-tutorial-om.core :as core]
            [compojure.handler :as handler]))


(defonce srv (agent nil))


(defn start [port]
  (core/init)
  (send srv
        (fn [s]
          (when (and s (.isRunning s))
            (.stop s))
          ;; TODO: port in env/env
          (jetty/run-jetty #'core/app {:port port, :join? false}))))


(comment

  (start 8000)


  )
