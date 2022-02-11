(defproject meigen-bot "0.1.0-SNAPSHOT"
  :description "Twitter Bot written in Clojure."
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.taoensso/timbre "5.1.2"]
                 [environ "1.2.0"]
                 [cheshire "5.10.2"]
                 [clj-http "3.12.3"]
                 [jarohen/chime "0.3.3"]]
  :main meigen-bot.core
  :repl-options {:init-ns meigen-bot.core}
  :min-lein-version "2.0.0"​
  :profiles {:uberjar {:aot :all}}
  )
