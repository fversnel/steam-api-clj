# steam-api-clj

Library that allows you to easily create Steam Web API calls from clojure.

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
(steam-api/request "ISteamNews" "GetNewsForAppV2"
                   {:appid 1234 :maxlength 50 :count 5 :format "json"})
=> {:method :get,
    :url "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002",
    :headers {"ContentType" "application/x-www-form-urlencoded",
              "Accept" "application/json"},
    :query-params {:appid 1234, :maxlength 50, :count 5, :format "json"}}

```

You can lookup a specific method very easily in the requests map in the [core](./src/steam_api_clj/core.clj) file.
Each API call also has metadata associated with it that tells you what it is:

```clojure
(meta (get-in steam-api/requests ["ISteamNews" "GetNewsForAppV2"]))
=> {:url "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002",
    :http-method :get,
    :parameters [:appid :maxlength :enddate :count :feeds :format],
    :description "appid [uint32] - AppID to retrieve news for
                  maxlength [uint32, optional] - Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit.
                  enddate [uint32, optional] - Retrieve posts earlier than this date (unix epoch timestamp)
                  count [uint32, optional] - # of posts to retrieve (default 20)
                  feeds [string, optional] - Comma-seperated list of feed names to return news for
                  format [string, optional] - The desired response format: json, xml, vdf, or csv. If not specified then json is assumed"}
```

## Re-generating the API

Place the updated steam-api-list.json in the resource folder and do:

```clojure
(steam-api-clj.generator/generate-api)
```

This will generate a new core.clj file with the updated API.

## License

Copyright © 2015 Frank Versnel

Distributed under the Eclipse Public License version 1.0