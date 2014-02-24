(defproject react-tutorial-om "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.reader "0.8.3"]
                 ;; CLJ
                 [ring/ring-core "1.2.0"]
                 [compojure "1.1.6"]
                 [cheshire "5.3.1"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 ;; CLJS
                 [org.clojure/clojurescript "0.0-2156"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [secretary "0.4.0"]
                 [markdown-clj "0.9.40"]
                 [cljs-http "0.1.2"]
                 [om "0.1.7"]
                 ;; TODO: move to dev
                 [weasel "0.1.0"] 
                 [com.cemerick/piggieback "0.1.3"]]

  ;; TODO: also move to dev
  :plugins [[lein-cljsbuild "1.0.0"]
            [lein-ring "0.8.7"]]

  ;; There does not appear to be way to specify versions for nrepl-middleware?
  ;; But piggieback/wrap-cljs-repl depends on a particular version (0.1.3)
  ;; of piggieback in dependencies. If piggieback gets bumped to 0.1.4,
  ;; the middleware will get bumped automatically, and this will likely break,
  ;; requiring a manual change to dependencies here, and cause
  ;; strange failures (and did, for me, when using 0.1.2, just now).
  
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]} ;; TODO: move to dev
  
  :ring {:handler react-tutorial-om.core/app
         :init    react-tutorial-om.core/init}

  :source-paths ["src/clj"]

  
  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :compiler {
                                   :output-to "resources/public/js/app.js"
                                   :output-dir "resources/public/js/out"
                                   :optimizations :none
                                   :source-map true
                                   :externs ["om/externs/react.js"]}}
                       {:id "release"
                        :source-paths ["src/cljs"]
                        :compiler {
                                   :output-to "resources/public/js/app.js"
                                   :source-map "resources/public/js/app.js.map"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :output-wrapper false
                                   :preamble ["om/react.min.js"]
                                   :externs ["om/externs/react.js"]
                                   :closure-warnings
                                   {:non-standard-jsdoc :off}}}]})

