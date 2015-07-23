# steam-api-clj

Library that allows you to easily call the Steam Web API from clojure.

## Design goals

- Not bound to any specific http library, I leave that up to the user
- API can be generated from Steam's own [API specification](http://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001/?format=json)

## Usage

```clojure
;; In your ns statement:
(ns my.ns
  (:require [steam-api-clj.core :as steam-api]))
```

And simply create the desired request by specifying the Steam interface, method name and parameters

```clojure
(steam-api/request "ISteamNews" "GetNewsForAppV2" {:appid 1234 :maxlength 50 :count 5 :format "json"})
=> {:method :get, :url "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002", :headers {"ContentType" "application/x-www-form-urlencoded", "Accept" "application/json"}, :query-params {:appid 1234, :maxlength 50, :count 5, :format "json"}}

```

## Re-generating the API

Place the updated steam-api-list.json in the resource folder and do:

```clojure
(steam-api-clj.generator/generate-api)
```

This will generate a new core.clj file with the updated API.

## License

Copyright © 2015 Frank Versnel

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
