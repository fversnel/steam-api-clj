# steam-api-clj

Library that allows you to easily create Steam Web API calls from Clojure.

## Features

An idiomatic and complete Clojure API for the Steam Web Interface.

- The API is exposed as one big map:
  - Each API call has metadata associated with it that tells you what it is.
  - You can use the power of Clojure to explore and use the API.
- It is **not** bound to any specific http library, I leave that up to you.
- The API can be generated from Steam's own [API specification](http://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001/?format=json)

## Installation

steam-api-clj is available as a Maven artifact from Clojars.

With Leiningen/Boot:

```clojure
[org.fversnel/steam-api "0.8.0"]
```

## Usage

```clojure
;; In your ns statement:
(ns my.ns
  (:require [org.fversnel.steam-api.core :as steam-api]))
```

And simply create the desired request by specifying the Steam interface, method name and parameters

```clojure
(steam-api/request "ISteamNews" "GetNewsForAppV2"
                   {:appid 329190 :maxlength 50 :count 5 :format "json"})
=> {:method :get,
    :url "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002",
    :headers {"ContentType" "application/x-www-form-urlencoded; charset=utf-8",
              "Accept" "application/json"},
    :query-params {"format" "json", "maxlength" 50, "appid" 329190, "count" 5}}
```

You can lookup a specific method very easily in the requests map in the [core](./src/steam_api_clj/core.clj) file.
Each API call also has metadata associated with it that tells you what it is:

```clojure
(meta (get-in steam-api/requests ["ISteamNews" "GetNewsForAppV2"]))
=> {:url "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002",
    :description "",
    :http-method :get,
    :parameters {:appid "(uint32) AppID to retrieve news for"
                 :maxlength "(uint32, optional) Maximum length for the content to return, if this is 0 the full 
                             content is returned, if it's less then a blurb is generated to fit."
                 :enddate "(uint32, optional) Retrieve posts earlier than this date (unix epoch timestamp)"
                 :count "(uint32, optional) # of posts to retrieve (default 20)"
                 :feeds "(string, optional) Comma-seperated list of feed names to return news for"
                 :format "(string, optional) The desired response format: json, xml, or vdf. Default: json"}}
```

Steam has two distinct methods for putting an array of values in a request parameter.

The first one is as comma-separated value:

```clojure
{"something" "42,43,44"}
```

But sometimes it requires the following structure:

```clojure
{"something[0]" 42 "something[1]" 43 "something[2]" 44}
```

Either way you can just hand over your collection `[42 43 44]` to the library and the library
will make sure that your collection will be converted according to the specification
of that parameter.

### Using your Steam API key

Using your steam API key is as simple as passing it to the request:

```clojure
(steam-api/request "ISteamUserAuth" "AuthenticateUserTicketV1"
                   {:appid 329190 :key "mykey" :ticket "some_ticket" :format "json"})
```

### Using default parameters

If you find it annoying to keep passing your `key`, `appid`, and `format` parameters
you can use `request-with-defaults`:
```clojure
(def request
  (steam-api/request-with-defaults {:appid 329190
                                    :key "mykey"
                                    :format "json"}))
```
A request call then becomes:
```clojure
(request "ISteamUserAuth" "AuthenticateUserTicketV1"
         {:ticket "some_ticket"})
```
**Don't worry about providing parameters that are not part of the request
specification as `steam-api-clj` will filter those out for you.**

## Re-generating the API

Place the updated steam-api-list.json in the resource folder and do:

```clojure
(org.fversnel.steam-api.api-generator/generate-api)
```

This will generate a new core.clj file with the updated API.

## License

Copyright © 2015-2016 Frank Versnel

Distributed under the Eclipse Public License version 1.0
