(ns
 steam-api-clj.core
 (:require [steam-api-clj.api :refer [steam-request]]))

(declare requests)

(defn
 request
 [interface method parameters]
 ((get-in requests [interface method]) parameters))

(defn
 list-api-calls
 []
 (vec
  (for
   [[interface methods] (vec requests)]
   [interface (vec (keys methods))])))

(defn interfaces [] (keys requests))

(defn
 method-info
 [interface method]
 (meta (get-in requests [interface method])))

(defn
 interface-methods
 [interface]
 (keys (get-in requests [interface])))

(def
 requests
 {"ITFPromos_620"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_620/GetItemID/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :PromoID
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_620/GrantItem/v0001"
    ""
    :post
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :PromoID
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IPortal2Leaderboards_620"
  {"GetBucketizedDataV1"
   (steam-request
    "https://api.steampowered.com/IPortal2Leaderboards_620/GetBucketizedData/v0001"
    ""
    :get
    [:leaderboardName
     "(string) The leaderboard name to fetch data for."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamRemoteStorage"
  {"EnumerateUserPublishedFilesV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/EnumerateUserPublishedFiles/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "EnumerateUserSubscribedFilesV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/EnumerateUserSubscribedFiles/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :listtype
     "(uint32, optional) EUCMListType"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetCollectionDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/GetCollectionDetails/v0001"
    ""
    :post
    [:collectioncount
     "(uint32) Number of collections being requested"
     :publishedfileids
     [:indexed-array "(uint64) collection ids to get the details for"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPublishedFileDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/GetPublishedFileDetails/v0001"
    ""
    :post
    [:itemcount
     "(uint32) Number of items being requested"
     :publishedfileids
     [:indexed-array "(uint64) published file id to look up"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUGCFileDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/GetUGCFileDetails/v0001"
    ""
    :get
    [:steamid
     "(uint64, optional) If specified, only returns details if the file is owned by the SteamID specified"
     :ugcid
     "(uint64) ID of UGC file to get info for"
     :appid
     "(uint32) appID of product"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SetUGCUsedByGCV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/SetUGCUsedByGC/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :ugcid
     "(uint64) ID of UGC file whose bits are being fiddled with"
     :appid
     "(uint32) appID of product to change updating state for"
     :used
     "(bool) New state of flag"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SubscribePublishedFileV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/SubscribePublishedFile/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :publishedfileid
     "(uint64) published file id to subscribe to"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "UnsubscribePublishedFileV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/UnsubscribePublishedFile/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :publishedfileid
     "(uint64) published file id to unsubscribe from"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_730"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaV2"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetSchema/v0002"
    ""
    :get
    [:language
     "(string, optional) The language to return the names in. Defaults to returning string keys."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaURLV2"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetSchemaURL/v0002"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetStoreMetaData/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to results in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamVideo"
  {"AddVideoV1"
   (steam-request
    "https://api.steampowered.com/ISteamVideo/AddVideo/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of the video"
     :videoid
     "(string) ID of the video on the provider's site"
     :accountname
     "(string) Account name of the video's owner on the provider's site"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconService"
  {"FlushInventoryCacheV1"
   (steam-request
    "https://api.steampowered.com/IEconService/FlushInventoryCache/v0001"
    "Flushes the cache for a user's inventory in a specific app context"
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) User to clear cache for."
     :appid
     "(uint32) App to clear cache for."
     :contextid
     "(uint64) Context to clear cache for."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FlushAssetAppearanceCacheV1"
   (steam-request
    "https://api.steampowered.com/IEconService/FlushAssetAppearanceCache/v0001"
    "Flushes the display cache for assets.  This will result in calls to GetAssetClassInfo for each asset class the next time it is displayed."
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTradeOffersV1"
   (steam-request
    "https://api.steampowered.com/IEconService/GetTradeOffers/v0001"
    "Get a list of sent or received trade offers"
    :get
    [:key
     "(string) Access key"
     :get_sent_offers
     "(bool) Request the list of sent offers."
     :get_received_offers
     "(bool) Request the list of received offers."
     :get_descriptions
     "(bool) If set, the item display data for the items included in the returned trade offers will also be returned."
     :language
     "(string) The language to use when loading item display data."
     :active_only
     "(bool) Indicates we should only return offers which are still active, or offers that have changed in state since the time_historical_cutoff"
     :historical_only
     "(bool) Indicates we should only return offers which are not active."
     :time_historical_cutoff
     "(uint32) When active_only is set, offers updated since this time will also be returned"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTradeOfferV1"
   (steam-request
    "https://api.steampowered.com/IEconService/GetTradeOffer/v0001"
    "Gets a specific trade offer"
    :get
    [:key
     "(string) Access key"
     :tradeofferid
     "(uint64)"
     :language
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTradeOffersSummaryV1"
   (steam-request
    "https://api.steampowered.com/IEconService/GetTradeOffersSummary/v0001"
    "Get counts of pending and new trade offers"
    :get
    [:key
     "(string) Access key"
     :time_last_visit
     "(uint32) The time the user last visited.  If not passed, will use the time the user last visited the trade offer page."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "DeclineTradeOfferV1"
   (steam-request
    "https://api.steampowered.com/IEconService/DeclineTradeOffer/v0001"
    "Decline a trade offer someone sent to us"
    :post
    [:key
     "(string) Access key"
     :tradeofferid
     "(uint64)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "CancelTradeOfferV1"
   (steam-request
    "https://api.steampowered.com/IEconService/CancelTradeOffer/v0001"
    "Cancel a trade offer we sent"
    :post
    [:key
     "(string) Access key"
     :tradeofferid
     "(uint64)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_221540"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_221540/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITFPromos_841"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_841/GetItemID/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :PromoID
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_841/GrantItem/v0001"
    ""
    :post
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :PromoID
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamLeaderboards"
  {"DeleteLeaderboardV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/DeleteLeaderboard/v0001"
    ""
    :post
    [:appid
     "(uint32) appid of game"
     :name
     "(string) name of the leaderboard to delete"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FindOrCreateLeaderboardV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/FindOrCreateLeaderboard/v0001"
    ""
    :post
    [:appid
     "(uint32) appid of game"
     :name
     "(string) name of the leaderboard to create"
     :sortmethod
     "(string, optional) sort method to use for this leaderboard (defaults to Ascending)"
     :displaytype
     "(string, optional) display type for this leaderboard (defaults to Numeric)"
     :createifnotfound
     "(bool, optional) if this is true the leaderboard will be created if it doesn't exist. Defaults to true."
     :onlytrustedwrites
     "(bool, optional) if this is true the leaderboard scores cannot be set by clients, and can only be set by publisher via SetLeaderboardScore WebAPI. Defaults to false."
     :onlyfriendsreads
     "(bool, optional) if this is true the leaderboard scores can only be read for friends by clients, scores can always be read by publisher. Defaults to false."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FindOrCreateLeaderboardV2"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/FindOrCreateLeaderboard/v0002"
    ""
    :post
    [:appid
     "(uint32) appid of game"
     :name
     "(string) name of the leaderboard to create"
     :sortmethod
     "(string, optional) sort method to use for this leaderboard (defaults to Ascending)"
     :displaytype
     "(string, optional) display type for this leaderboard (defaults to Numeric)"
     :createifnotfound
     "(bool, optional) if this is true the leaderboard will be created if it doesn't exist. Defaults to true."
     :onlytrustedwrites
     "(bool, optional) if this is true the leaderboard scores cannot be set by clients, and can only be set by publisher via SetLeaderboardScore WebAPI. Defaults to false."
     :onlyfriendsreads
     "(bool, optional) if this is true the leaderboard scores can only be read for friends by clients, scores can always be read by publisher. Defaults to false."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetLeaderboardEntriesV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/GetLeaderboardEntries/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :rangestart
     "(int32) range start or 0"
     :rangeend
     "(int32) range end or max LB entries"
     :steamid
     "(uint64, optional) SteamID used for friend & around user requests"
     :leaderboardid
     "(int32) ID of the leaderboard to view"
     :datarequest
     "(uint32) type of request: RequestGlobal, RequestAroundUser, RequestFriends"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetLeaderboardsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/GetLeaderboardsForGame/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetLeaderboardsForGameV2"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/GetLeaderboardsForGame/v0002"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "ResetLeaderboardV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/ResetLeaderboard/v0001"
    ""
    :post
    [:appid
     "(uint32) appid of game"
     :leaderboardid
     "(uint32) numeric ID of the target leaderboard. Can be retrieved from GetLeaderboardsForGame"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SetLeaderboardScoreV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/SetLeaderboardScore/v0001"
    ""
    :post
    [:appid
     "(uint32) appid of game"
     :leaderboardid
     "(uint32) numeric ID of the target leaderboard. Can be retrieved from GetLeaderboardsForGame"
     :steamid
     "(uint64) steamID to set the score for"
     :score
     "(int32) the score to set for this user"
     :scoremethod
     "(string) update method to use. Can be \"KeepBest\" or \"ForceUpdate\""
     :details
     "(rawbinary, optional) game-specific details for how the score was earned. Up to 256 bytes."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IPlayerService"
  {"RecordOfflinePlaytimeV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/RecordOfflinePlaytime/v0001"
    "Tracks playtime for a user when they are offline"
    :post
    [:steamid
     "(uint64)"
     :ticket
     "(string)"
     :play_sessions
     "({message})"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetRecentlyPlayedGamesV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001"
    "Gets information about a player's recently played games"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The player we're asking about"
     :count
     "(uint32) The number of games to return (0/unset: all)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetOwnedGamesV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001"
    "Return a list of games owned by the player"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The player we're asking about"
     :include_appinfo
     "(bool) true if we want additional details (name, icon) about each game"
     :include_played_free_games
     "(bool) Free games are excluded by default.  If this is set, free games the user has played will be returned."
     :appids_filter
     "(uint32) if set, restricts result set to the passed in apps"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSteamLevelV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetSteamLevel/v0001"
    "Returns the Steam Level of a user"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The player we're asking about"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetBadgesV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetBadges/v0001"
    "Gets badges that are owned by a specific user"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The player we're asking about"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetCommunityBadgeProgressV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetCommunityBadgeProgress/v0001"
    "Gets all the quests needed to get the specified badge, and which are completed"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The player we're asking about"
     :badgeid
     "(int32) The badge we're asking about"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "IsPlayingSharedGameV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/IsPlayingSharedGame/v0001"
    "Returns valid lender SteamID if game currently played is borrowed"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The player we're asking about"
     :appid_playing
     "(uint32) The game player is currently playing"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamCommunity"
  {"ReportAbuseV1"
   (steam-request
    "https://api.steampowered.com/ISteamCommunity/ReportAbuse/v0001"
    ""
    :post
    [:key
     "(string) access key"
     :steamidActor
     "(uint64) SteamID of user doing the reporting"
     :steamidTarget
     "(uint64) SteamID of the entity being accused of abuse"
     :appid
     "(uint32) AppID to check for ownership"
     :abuseType
     "(uint32) Abuse type code (see EAbuseReportType enum)"
     :contentType
     "(uint32) Content type code (see ECommunityContentType enum)"
     :description
     "(string) Narrative from user"
     :gid
     "(uint64, optional) GID of related record (depends on content type)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IBroadcastService"
  {"PostGameDataFrameV1"
   (steam-request
    "https://api.steampowered.com/IBroadcastService/PostGameDataFrame/v0001"
    "Add a game meta data frame to broadcast"
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :steamid
     "(uint64)"
     :broadcast_id
     "(uint64)"
     :frame_data
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IWorkshopService"
  {"SetItemPaymentRulesV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/SetItemPaymentRules/v0001"
    "Set item payment rules."
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :gameitemid
     "(uint32)"
     :associated_workshop_files
     "({message})"
     :partner_accounts
     "({message})"
     :validate_only
     "(bool, optional) Only validates the rules and does not persist them."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetFinalizedContributorsV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/GetFinalizedContributors/v0001"
    "Get a list of contributors for a specific gameitemid/app combination."
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :gameitemid
     "(uint32)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetItemDailyRevenueV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/GetItemDailyRevenue/v0001"
    "Get item revenue for a specific app/item definition for a date range."
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :item_id
     "(uint32)"
     :date_start
     "(uint32)"
     :date_end
     "(uint32)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "PopulateItemDescriptionsV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/PopulateItemDescriptions/v0001"
    "Populate block of item descriptions."
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :languages
     "({message})"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamMicroTxnSandbox"
  {"AdjustAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/AdjustAgreement/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user with the agreement"
     :agreementid
     "(uint64) ID of agreement"
     :appid
     "(uint32) AppID of game"
     :nextprocessdate
     "(string) Date for next process"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "QueryTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/QueryTxn/v0002"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :orderid
     "(uint64, optional) 3rd party ID for transaction"
     :transid
     "(uint64, optional) Steam transaction ID"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "CancelAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/CancelAgreement/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user with the agreement"
     :agreementid
     "(uint64) ID of agreement"
     :appid
     "(uint32) AppID of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "InitTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/InitTxn/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items in cart"
     :language
     "(string) ISO 639-1 language code of description"
     :currency
     "(string) ISO 4217 currency code"
     :itemid
     [:indexed-array "(uint32) 3rd party ID for item"]
     :qty
     [:indexed-array "(uint32) Quantity of this item"]
     :amount
     [:indexed-array "(int32) Total cost (in cents) of item(s)"]
     :description
     [:indexed-array "(string) Description of item"]
     :category
     [:indexed-array
      "(string, optional) Optional category grouping for item"]
     :billingtype
     [:indexed-array
      "(string, optional) Optional recurring billing type"]
     :startdate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :enddate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :period
     [:indexed-array
      "(string, optional) Optional period for recurring billing"]
     :frequency
     [:indexed-array
      "(uint32, optional) Optional frequency for recurring billing"]
     :recurringamt
     [:indexed-array
      "(string, optional) Optional recurring billing amount"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetReportV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetReport/v0003"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :type
     "(string) Report type (GAMESALES, STEAMSTORE, SETTLEMENT)"
     :time
     "(string) Beginning time to start report from (RFC 3339 UTC format)"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetReportV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetReport/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :type
     "(string) Report type (GAMESALES, STEAMSTORE, SETTLEMENT)"
     :time
     "(string) Beginning time to start report from (RFC 3339 UTC format)"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetReportV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetReport/v0002"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :type
     "(string) Report type (GAMESALES, STEAMSTORE, SETTLEMENT)"
     :time
     "(string) Beginning time to start report from (RFC 3339 UTC format)"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FinalizeTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/FinalizeTxn/v0002"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "InitTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/InitTxn/v0002"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items in cart"
     :language
     "(string) ISO 639-1 language code of description"
     :currency
     "(string) ISO 4217 currency code"
     :itemid
     [:indexed-array "(uint32) 3rd party ID for item"]
     :qty
     [:indexed-array "(uint32) Quantity of this item"]
     :amount
     [:indexed-array "(int32) Total cost (in cents) of item(s)"]
     :description
     [:indexed-array "(string) Description of item"]
     :category
     [:indexed-array
      "(string, optional) Optional category grouping for item"]
     :billingtype
     [:indexed-array
      "(string, optional) Optional recurring billing type"]
     :startdate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :enddate
     [:indexed-array
      "(string, optional) Optional end date for recurring billing"]
     :period
     [:indexed-array
      "(string, optional) Optional period for recurring billing"]
     :frequency
     [:indexed-array
      "(uint32, optional) Optional frequency for recurring billing"]
     :recurringamt
     [:indexed-array
      "(string, optional) Optional recurring billing amount"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetUserInfo/v0001"
    ""
    :get
    [:steamid
     "(uint64, optional) SteamID of user making purchase"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "InitTxnV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/InitTxn/v0003"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items in cart"
     :language
     "(string) ISO 639-1 language code of description"
     :currency
     "(string) ISO 4217 currency code"
     :itemid
     [:indexed-array "(uint32) 3rd party ID for item"]
     :qty
     [:indexed-array "(uint32) Quantity of this item"]
     :amount
     [:indexed-array "(int32) Total cost (in cents) of item(s)"]
     :description
     [:indexed-array "(string) Description of item"]
     :category
     [:indexed-array
      "(string, optional) Optional category grouping for item"]
     :billingtype
     [:indexed-array
      "(string, optional) Optional recurring billing type"]
     :startdate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :enddate
     [:indexed-array
      "(string, optional) Optional end date for recurring billing"]
     :period
     [:indexed-array
      "(string, optional) Optional period for recurring billing"]
     :frequency
     [:indexed-array
      "(uint32, optional) Optional frequency for recurring billing"]
     :recurringamt
     [:indexed-array
      "(string, optional) Optional recurring billing amount"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserInfoV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetUserInfo/v0002"
    ""
    :get
    [:steamid
     "(uint64, optional) SteamID of user making purchase"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FinalizeTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/FinalizeTxn/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RefundTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/RefundTxn/v0002"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "ProcessAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/ProcessAgreement/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user with the agreement"
     :agreementid
     "(uint64) ID of agreement"
     :appid
     "(uint32) AppID of game"
     :amount
     "(int32) Total cost (in cents) to charge"
     :currency
     "(string) ISO 4217 currency code"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserAgreementInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetUserAgreementInfo/v0001"
    ""
    :get
    [:steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "QueryTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/QueryTxn/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :orderid
     "(uint64, optional) 3rd party ID for transaction"
     :transid
     "(uint64, optional) Steam transaction ID"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RefundTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/RefundTxn/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_570"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetSchema/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to return the names in. Defaults to returning string keys."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaURLV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetSchemaURL/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetStoreMetaData/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to results in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamPublishedItemSearch"
  {"RankedByPublicationOrderV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/RankedByPublicationOrder/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :startidx
     "(uint32) Starting index in the result set (0 based)"
     :count
     "(uint32) Number Requested"
     :tagcount
     "(uint32) Number of Tags Specified"
     :usertagcount
     "(uint32) Number of User specific tags requested"
     :hasappadminaccess
     "(bool, optional) Whether the user making the request is an admin for the app and can see private files"
     :fileType
     "(uint32, optional) EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items"
     :tag
     [:indexed-array "(string, optional) Tag to filter result set"]
     :usertag
     [:indexed-array "(string, optional) A user specific tag"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RankedByTrendV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/RankedByTrend/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :startidx
     "(uint32) Starting index in the result set (0 based)"
     :count
     "(uint32) Number Requested"
     :tagcount
     "(uint32) Number of Tags Specified"
     :usertagcount
     "(uint32) Number of User specific tags requested"
     :hasappadminaccess
     "(bool, optional) Whether the user making the request is an admin for the app and can see private files"
     :fileType
     "(uint32, optional) EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items"
     :days
     "(uint32, optional) [1,7] number of days for the trend period, including today"
     :tag
     [:indexed-array "(string, optional) Tag to filter result set"]
     :usertag
     [:indexed-array "(string, optional) A user specific tag"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RankedByVoteV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/RankedByVote/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appID of product"
     :startidx
     "(uint32) Starting index in the result set (0 based)"
     :count
     "(uint32) Number Requested"
     :tagcount
     "(uint32) Number of Tags Specified"
     :usertagcount
     "(uint32) Number of User specific tags requested"
     :hasappadminaccess
     "(bool, optional) Whether the user making the request is an admin for the app and can see private files"
     :fileType
     "(uint32, optional) EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items"
     :tag
     [:indexed-array "(string, optional) Tag to filter result set"]
     :usertag
     [:indexed-array "(string, optional) A user specific tag"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "ResultSetSummaryV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/ResultSetSummary/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint64) appID relevant to all subsequent tags"
     :tagcount
     "(uint32) Number of Tags Specified"
     :usertagcount
     "(uint32) Number of User specific tags requested"
     :hasappadminaccess
     "(bool, optional) Whether the user making the request is an admin for the app and can see private files"
     :fileType
     "(uint32, optional) EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items"
     :tag
     [:indexed-array "(string, optional) Tag to filter result set"]
     :usertag
     [:indexed-array "(string, optional) A user specific tag"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamEnvoy"
  {"PaymentOutNotificationV1"
   (steam-request
    "https://api.steampowered.com/ISteamEnvoy/PaymentOutNotification/v0001"
    ""
    :post
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "PaymentOutReversalNotificationV1"
   (steam-request
    "https://api.steampowered.com/ISteamEnvoy/PaymentOutReversalNotification/v0001"
    ""
    :post
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITFPromos_730"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_730/GetItemID/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :PromoID
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_730/GrantItem/v0001"
    ""
    :post
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :PromoID
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IGCVersion_205790"
  {"GetClientVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_205790/GetClientVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_205790/GetServerVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IPublishedFileService"
  {"QueryFilesV1"
   (steam-request
    "https://api.steampowered.com/IPublishedFileService/QueryFiles/v0001"
    "Performs a search query for published files"
    :get
    [:key
     "(string) Access key"
     :query_type
     "(uint32) enumeration EPublishedFileQueryType in clientenums.h"
     :page
     "(uint32) Current page"
     :numperpage
     "(uint32, optional) (Optional) The number of results, per page to return."
     :creator_appid
     "(uint32) App that created the files"
     :appid
     "(uint32) App that consumes the files"
     :requiredtags
     "(string) Tags to match on. See match_all_tags parameter below"
     :excludedtags
     "(string) (Optional) Tags that must NOT be present on a published file to satisfy the query."
     :match_all_tags
     "(bool, optional) If true, then items must have all the tags specified, otherwise they must have at least one of the tags."
     :required_flags
     "(string) Required flags that must be set on any returned items"
     :omitted_flags
     "(string) Flags that must not be set on any returned items"
     :search_text
     "(string) Text to match in the item's title or description"
     :filetype
     "(uint32) EPublishedFileInfoMatchingFileType"
     :child_publishedfileid
     "(uint64) Find all items that reference the given item."
     :days
     "(uint32) If query_type is k_PublishedFileQueryType_RankedByTrend, then this is the number of days to get votes for [1,7]."
     :include_recent_votes_only
     "(bool) If query_type is k_PublishedFileQueryType_RankedByTrend, then limit result set just to items that have votes within the day range given"
     :cache_max_age_seconds
     "(uint32, optional) Allow stale data to be returned for the specified number of seconds."
     :language
     "(int32, optional) Language to search in and also what gets returned. Defaults to English."
     :required_kv_tags
     "({message}) Required key-value tags to match on."
     :totalonly
     "(bool) (Optional) If true, only return the total number of files that satisfy this query."
     :return_vote_data
     "(bool) Return vote data"
     :return_tags
     "(bool) Return tags in the file details"
     :return_kv_tags
     "(bool) Return key-value tags in the file details"
     :return_previews
     "(bool) Return preview image and video details in the file details"
     :return_children
     "(bool) Return child item ids in the file details"
     :return_short_description
     "(bool) Populate the short_description field instead of file_description"
     :return_for_sale_data
     "(bool) Return pricing information, if applicable"
     :return_metadata
     "(bool, optional) Populate the metadata"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SetDeveloperMetadataV1"
   (steam-request
    "https://api.steampowered.com/IPublishedFileService/SetDeveloperMetadata/v0001"
    "Sets the metadata for a developer on the published file"
    :post
    [:key
     "(string) Access key"
     :publishedfileid
     "(uint64)"
     :appid
     "(uint32)"
     :metadata
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "UpdateTagsV1"
   (steam-request
    "https://api.steampowered.com/IPublishedFileService/UpdateTags/v0001"
    "Updates tags on the published file"
    :post
    [:key
     "(string) Access key"
     :publishedfileid
     "(uint64)"
     :appid
     "(uint32)"
     :add_tags
     "(string)"
     :remove_tags
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITFItems_440"
  {"GetGoldenWrenchesV2"
   (steam-request
    "https://api.steampowered.com/ITFItems_440/GetGoldenWrenches/v0002"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamWebUserPresenceOAuth"
  {"PollStatusV1"
   (steam-request
    "https://api.steampowered.com/ISteamWebUserPresenceOAuth/PollStatus/v0001"
    ""
    :post
    [:steamid
     "(string) Steam ID of the user"
     :umqid
     "(uint64) UMQ Session ID"
     :message
     "(uint32) Message that was last known to the user"
     :pollid
     "(uint32, optional) Caller-specific poll id"
     :sectimeout
     "(uint32, optional) Long-poll timeout in seconds"
     :secidletime
     "(uint32, optional) How many seconds is client considering itself idle, e.g. screen is off"
     :use_accountids
     "(uint32, optional) Boolean, 0 (default): return steamid_from in output, 1: return accountid_from"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_620"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_620/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_620/GetSchema/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to return the names in. Defaults to returning string keys."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_218620"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_218620/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IAccountRecoveryService"
  {"ReportAccountRecoveryDataV1"
   (steam-request
    "https://api.steampowered.com/IAccountRecoveryService/ReportAccountRecoveryData/v0001"
    "Send account recovery data"
    :post
    [:loginuser_list
     "(string)"
     :install_config
     "(string)"
     :shasentryfile
     "(string)"
     :machineid
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RetrieveAccountRecoveryDataV1"
   (steam-request
    "https://api.steampowered.com/IAccountRecoveryService/RetrieveAccountRecoveryData/v0001"
    "Send account recovery data"
    :post
    [:requesthandle
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamUserAuth"
  {"AuthenticateUserV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserAuth/AuthenticateUser/v0001"
    ""
    :post
    [:steamid
     "(uint64) Should be the users steamid, unencrypted."
     :sessionkey
     "(rawbinary) Should be a 32 byte random blob of data, which is then encrypted with RSA using the Steam system's public key.  Randomness is important here for security."
     :encrypted_loginkey
     "(rawbinary) Should be the users hashed loginkey, AES encrypted with the sessionkey."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "AuthenticateUserTicketV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserAuth/AuthenticateUserTicket/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :ticket
     "(string) Ticket from GetAuthSessionTicket."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamUserOAuth"
  {"GetTokenDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserOAuth/GetTokenDetails/v0001"
    ""
    :get
    [:access_token
     "(string) OAuth2 token for which to return details"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamWorkshop"
  {"AssociateWorkshopItemsV1"
   (steam-request
    "https://api.steampowered.com/ISteamWorkshop/AssociateWorkshopItems/v0001"
    ""
    :post
    [:appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items to associate"
     :publishedfileid
     [:indexed-array
      "(uint64, optional) the workshop published file id"]
     :gameitemid
     [:indexed-array "(uint32, optional) 3rd party ID for item"]
     :revenuepercentage
     [:indexed-array
      "(float, optional) Percentage of revenue the owners of the workshop item will get from the sale of the item [0.0, 100.0].  For bundle-like items, send over an entry for each item in the bundle (gameitemid = bundle id) with different publishedfileids and with the revenue percentage pre-split amongst the items in the bundle (i.e. 30% / 10 items in the bundle)"]
     :gameitemdescription
     [:indexed-array
      "(string, optional) Game's description of the game item"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetContributorsV1"
   (steam-request
    "https://api.steampowered.com/ISteamWorkshop/GetContributors/v0001"
    ""
    :post
    [:appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_841"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_841/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_841/GetSchema/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to return the names in. Defaults to returning string keys."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IGameServersService"
  {"GetAccountListV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetAccountList/v0001"
    "Gets a list of game server accounts with their logon tokens"
    :get
    [:key
     "(string) Access key"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "CreateAccountV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/CreateAccount/v0001"
    "Creates a persistent game server account"
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32) The app to use the account for"
     :memo
     "(string) The memo to set on the new account"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SetMemoV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/SetMemo/v0001"
    "This method changes the memo associated with the game server account. Memos do not affect the account in any way. The memo shows up in the GetAccountList response and serves only as a reminder of what the account is used for."
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The SteamID of the game server to set the memo on"
     :memo
     "(string) The memo to set on the new account"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "ResetLoginTokenV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/ResetLoginToken/v0001"
    "Generates a new login token for the specified game server"
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The SteamID of the game server to reset the login token of"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAccountPublicInfoV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetAccountPublicInfo/v0001"
    "Gets public information about a given game server account"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The SteamID of the game server to get info on"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServerSteamIDsByIPV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetServerSteamIDsByIP/v0001"
    "Gets a list of server SteamIDs given a list of IPs"
    :get
    [:key
     "(string) Access key"
     :server_ips
     "(string)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServerIPsBySteamIDV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetServerIPsBySteamID/v0001"
    "Gets a list of server IP addresses given a list of SteamIDs"
    :get
    [:key
     "(string) Access key"
     :server_steamids
     "(uint64)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ICSGOServers_730"
  {"GetGameServersStatusV1"
   (steam-request
    "https://api.steampowered.com/ICSGOServers_730/GetGameServersStatus/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IPortal2Leaderboards_841"
  {"GetBucketizedDataV1"
   (steam-request
    "https://api.steampowered.com/IPortal2Leaderboards_841/GetBucketizedData/v0001"
    ""
    :get
    [:leaderboardName
     "(string) The leaderboard name to fetch data for."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamUserStats"
  {"GetPlayerAchievementsV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) AppID to get achievements for"
     :l
     "(string, optional) Language to return strings for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetGlobalAchievementPercentagesForAppV2"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0002"
    ""
    :get
    [:gameid
     "(uint64) GameID to retrieve the achievement percentages for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :l
     "(string, optional) localized langauge to return (english, french, etc.)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserStatsForGameV2"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appid of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetGlobalStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetGlobalStatsForGame/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID that we're getting global stats for"
     :count
     "(uint32) Number of stats get data for"
     :name
     [:indexed-array "(string) Names of stat to get data for"]
     :startdate
     "(uint32, optional) Start date for daily totals (unix epoch timestamp)"
     :enddate
     "(uint32, optional) End date for daily totals (unix epoch timestamp)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaForGameV2"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v0002"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :l
     "(string, optional) localized language to return (english, french, etc.)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetNumberOfCurrentPlayersV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID that we're getting user count for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SetUserStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/SetUserStatsForGame/v0001"
    ""
    :post
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appid of game"
     :count
     "(uint32) Number of stats and achievements to set a value for (name/value param pairs)"
     :name
     [:indexed-array "(string) Name of stat or achievement to set"]
     :value
     [:indexed-array "(uint32) Value to set"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetGlobalAchievementPercentagesForAppV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0001"
    ""
    :get
    [:gameid
     "(uint64) GameID to retrieve the achievement percentages for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) appid of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamSpecialSurvey"
  {"CheckUserStatusV1"
   (steam-request
    "https://api.steampowered.com/ISteamSpecialSurvey/CheckUserStatus/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :surveyid
     "(uint32) ID of the survey being taken"
     :steamid
     "(uint64) SteamID of the user taking the survey"
     :token
     "(string) Survey identity verification token for the user"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SetUserFinishedV1"
   (steam-request
    "https://api.steampowered.com/ISteamSpecialSurvey/SetUserFinished/v0001"
    ""
    :post
    [:key
     "(string) access key"
     :appid
     "(uint32) appid of game"
     :surveyid
     "(uint32) ID of the survey being taken"
     :steamid
     "(uint64) SteamID of the user taking the survey"
     :token
     "(string) Survey identity verification token for the user"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IGameNotificationsService"
  {"CreateSessionV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/CreateSession/v0001"
    "Creates an async game session"
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32) The appid to create the session for."
     :context
     "(uint64) Game-specified context value the game can used to associate the session with some object on their backend."
     :title
     "({message}) The title of the session to be displayed within each user's list of sessions."
     :users
     "({message}) The initial state of all users in the session."
     :steamid
     "(uint64) (Optional) steamid to make the request on behalf of -- if specified, the user must be in the session and all users being added to the session must be friends with the user."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "UpdateSessionV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/UpdateSession/v0001"
    "Updates a game session"
    :post
    [:key
     "(string) Access key"
     :sessionid
     "(uint64) The sessionid to update."
     :appid
     "(uint32) The appid of the session to update."
     :title
     "({message}) (Optional) The new title of the session.  If not specified, the title will not be changed."
     :users
     "({message}) (Optional) A list of users whose state will be updated to reflect the given state. If the users are not already in the session, they will be added to it."
     :steamid
     "(uint64) (Optional) steamid to make the request on behalf of -- if specified, the user must be in the session and all users being added to the session must be friends with the user."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "EnumerateSessionsForAppV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/EnumerateSessionsForApp/v0001"
    "Enumerates a user's sessions"
    :get
    [:key
     "(string) Access key"
     :appid
     "(uint32) The sessionid to request details for. Optional. If not specified, all the user's sessions will be returned."
     :steamid
     "(uint64) The user whose sessions are to be enumerated."
     :include_all_user_messages
     "(bool) (Optional) Boolean determining whether the message for all users should be included. Defaults to false."
     :include_auth_user_message
     "(bool) (Optional) Boolean determining whether the message for the authenticated user should be included. Defaults to false."
     :language
     "(string) (Optional) Language to localize the text in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSessionDetailsForAppV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/GetSessionDetailsForApp/v0001"
    "Get the details for a specific session"
    :get
    [:key
     "(string) Access key"
     :sessions
     "({message})"
     :appid
     "(uint32) The appid for the sessions."
     :language
     "(string) Language to localize the text in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RequestNotificationsV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/RequestNotifications/v0001"
    "Requests that a user receive game notifications for an app"
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The steamid to request notifications for."
     :appid
     "(uint32) The appid to request notifications for."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "DeleteSessionV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/DeleteSession/v0001"
    "Deletes an async game session"
    :post
    [:key
     "(string) Access key"
     :sessionid
     "(uint64) The sessionid to delete."
     :appid
     "(uint32) The appid of the session to delete."
     :steamid
     "(uint64) (Optional) steamid to make the request on behalf of -- if specified, the user must be in the session."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "DeleteSessionBatchV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/DeleteSessionBatch/v0001"
    "Deletes a batch of async game sessions"
    :post
    [:key
     "(string) Access key"
     :sessionid
     "(uint64) The sessionid to delete."
     :appid
     "(uint32) The appid of the session to delete."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconDOTA2_570"
  {"GetEventStatsForAccountV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetEventStatsForAccount/v0001"
    ""
    :get
    [:eventid
     "(uint32) The League ID of the compendium you're looking for."
     :accountid
     "(uint32) The account ID to look up."
     :language
     "(string, optional) The language to provide hero names in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetGameItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetGameItems/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to provide item names in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetHeroesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to provide hero names in."
     :itemizedonly
     "(bool, optional) Return a list of itemized heroes only."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetItemIconPathV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetItemIconPath/v0001"
    ""
    :get
    [:iconname
     "(string) The item icon name to get the CDN path of"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetRaritiesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetRarities/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to provide rarity names in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTournamentPrizePoolV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetTournamentPrizePool/v0001"
    ""
    :get
    [:leagueid
     "(uint32, optional) The ID of the league to get the prize pool of"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITFPromos_440"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_440/GetItemID/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :promoid
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_440/GrantItem/v0001"
    ""
    :post
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :promoid
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamUser"
  {"ResolveVanityURLV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :vanityurl
     "(string) The vanity URL to get a SteamID for"
     :url_type
     "(int32, optional) The type of vanity URL. 1 (default): Individual profile, 2: Group, 3: Official game group"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantPackageV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GrantPackage/v0001"
    ""
    :post
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :packageid
     "(uint32) PackageID to grant"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx)."
     :thirdpartykey
     "(string, optional) Optionally associate third party key during grant. 'thirdpartyappid' will have to be set."
     :thirdpartyappid
     "(uint32, optional) Has to be set if 'thirdpartykey' is set. The appid associated with the 'thirdpartykey'."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAppPriceInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetAppPriceInfo/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :appids
     "(string) Comma-delimited list of appids (max: 100)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPublisherAppOwnershipV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPublisherAppOwnership/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPublisherAppOwnershipV2"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPublisherAppOwnership/v0002"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserGroupListV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetUserGroupList/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPlayerSummariesV2"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002"
    ""
    :get
    [:key
     "(string) access key"
     :steamids
     "(string) Comma-delimited list of SteamIDs (max: 100)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPlayerBansV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPlayerBans/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamids
     "(string) Comma-delimited list of SteamIDs"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetFriendListV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetFriendList/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :relationship
     "(string, optional) relationship type (ex: friend)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPlayerSummariesV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamids
     "(string) Comma-delimited list of SteamIDs"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "CheckAppOwnershipV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/CheckAppOwnership/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) AppID to check for ownership"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IDOTA2Match_570"
  {"GetLeagueListingV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetLeagueListing/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetLiveLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v0001"
    ""
    :get
    [:league_id
     "(uint32, optional) Only show matches of the specified league id"
     :match_id
     "(uint64, optional) Only show matches of the specified match id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMatchDetailsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v0001"
    ""
    :get
    [:match_id
     "(uint64) Match id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMatchHistoryV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v0001"
    ""
    :get
    [:hero_id
     "(uint32, optional) The ID of the hero that must be in the matches being queried"
     :game_mode
     "(uint32, optional) Which game mode to return matches for"
     :skill
     "(uint32, optional) The average skill range of the match, these can be [1-3] with lower numbers being lower skill. Ignored if an account ID is specified"
     :min_players
     "(string, optional) Minimum number of human players that must be in a match for it to be returned"
     :account_id
     "(string, optional) An account ID to get matches from. This will fail if the user has their match history hidden"
     :league_id
     "(string, optional) The league ID to return games from"
     :start_at_match_id
     "(uint64, optional) The minimum match ID to start from"
     :matches_requested
     "(string, optional) The number of requested matches to return"
     :tournament_games_only
     "(string, optional) Whether or not tournament games should only be returned"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMatchHistoryBySequenceNumV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001"
    ""
    :get
    [:start_at_match_seq_num
     "(uint64, optional) "
     :matches_requested
     "(uint32, optional) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetScheduledLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetScheduledLeagueGames/v0001"
    ""
    :get
    [:date_min
     "(uint32, optional) The starting time stamp to collect scheduled games from. If ignored, it will use the current time"
     :date_max
     "(uint32, optional) The ending time stamp. If this is more than 7 days past the starting time stamp, it will be clamped to 7 days."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTeamInfoByTeamIDV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetTeamInfoByTeamID/v0001"
    ""
    :get
    [:start_at_team_id
     "(uint64, optional) "
     :teams_requested
     "(uint32, optional) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTournamentPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetTournamentPlayerStats/v0001"
    ""
    :get
    [:account_id
     "(string) "
     :league_id
     "(string, optional) "
     :hero_id
     "(string, optional) "
     :time_frame
     "(string, optional) "
     :match_id
     "(uint64, optional) "
     :phase_id
     "(uint32, optional) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IDOTA2MatchStats_570"
  {"GetRealtimeStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2MatchStats_570/GetRealtimeStats/v0001"
    ""
    :get
    [:server_steam_id
     "(uint64) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IGCVersion_570"
  {"GetClientVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_570/GetClientVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_570/GetServerVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IDOTA2Fantasy_570"
  {"GetFantasyPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_570/GetFantasyPlayerStats/v0001"
    ""
    :get
    [:FantasyLeagueID
     "(uint32) The fantasy league ID"
     :StartTime
     "(uint32, optional) An optional filter for minimum timestamp"
     :EndTime
     "(uint32, optional) An optional filter for maximum timestamp"
     :matchid
     "(uint64, optional) An optional filter for a specific match"
     :SeriesID
     "(uint32, optional) An optional filter for a specific series"
     :PlayerAccountID
     "(uint32, optional) An optional filter for a specific player"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPlayerOfficialInfoV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_570/GetPlayerOfficialInfo/v0001"
    ""
    :get
    [:accountid
     "(uint32) The account ID to look up"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_238460"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_238460/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamNews"
  {"GetNewsForAppV1"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID to retrieve news for"
     :maxlength
     "(uint32, optional) Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit."
     :enddate
     "(uint32, optional) Retrieve posts earlier than this date (unix epoch timestamp)"
     :count
     "(uint32, optional) # of posts to retrieve (default 20)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetNewsForAppV2"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002"
    ""
    :get
    [:appid
     "(uint32) AppID to retrieve news for"
     :maxlength
     "(uint32, optional) Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit."
     :enddate
     "(uint32, optional) Retrieve posts earlier than this date (unix epoch timestamp)"
     :count
     "(uint32, optional) # of posts to retrieve (default 20)"
     :feeds
     "(string, optional) Comma-seperated list of feed names to return news for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetNewsForAppAuthedV1"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForAppAuthed/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) AppID to retrieve news for"
     :maxlength
     "(uint32, optional) Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit."
     :enddate
     "(uint32, optional) Retrieve posts earlier than this date (unix epoch timestamp)"
     :count
     "(uint32, optional) # of posts to retrieve (default 20)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetNewsForAppAuthedV2"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForAppAuthed/v0002"
    ""
    :get
    [:key
     "(string) access key"
     :appid
     "(uint32) AppID to retrieve news for"
     :maxlength
     "(uint32, optional) Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit."
     :enddate
     "(uint32, optional) Retrieve posts earlier than this date (unix epoch timestamp)"
     :count
     "(uint32, optional) # of posts to retrieve (default 20)"
     :feeds
     "(string, optional) Comma-seperated list of feed names to return news for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ICheatReportingService"
  {"ReportPlayerCheatingV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/ReportPlayerCheating/v0001"
    "Reports a player cheating"
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) steamid of the user who is reported as cheating."
     :appid
     "(uint32) The appid."
     :steamidreporter
     "(uint64) (Optional) steamid of the user or game server who is reporting the cheating."
     :appdata
     "(uint64) (Optional) App specific data about the cheating."
     :heuristic
     "(bool) (Optional) extra information about the source of the cheating - was it a heuristic."
     :detection
     "(bool) (Optional) extra information about the source of the cheating - was it a detection."
     :playerreport
     "(bool) (Optional) extra information about the source of the cheating - was it a player report."
     :noreportid
     "(bool) (Optional) dont return report id"
     :gamemode
     "(uint32) (Optional) extra information about state of game - was it a specific type of game play (0 = generic)"
     :suspicionstarttime
     "(uint32) (Optional) extra information indicating how far back the game thinks is interesting for this user"
     :severity
     "(uint32) (Optional) level of severity of bad action being reported"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RequestPlayerGameBanV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/RequestPlayerGameBan/v0001"
    "Requests a ban on a player"
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) steamid of the user who is reported as cheating."
     :appid
     "(uint32) The appid."
     :reportid
     "(uint64) The reportid originally used to report cheating."
     :cheatdescription
     "(string) Text describing cheating infraction."
     :duration
     "(uint32) Ban duration requested in seconds."
     :delayban
     "(bool) Delay the ban according to default ban delay rules."
     :flags
     "(uint32) Additional information about the ban request."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetCheatingReportsV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/GetCheatingReports/v0001"
    "Get a list of cheating reports submitted for this app"
    :get
    [:key
     "(string) Access key"
     :appid
     "(uint32) The appid."
     :timeend
     "(uint32) The beginning of the time range ."
     :timebegin
     "(uint32) The end of the time range."
     :reportidmin
     "(uint64) Minimum reportID to include"
     :includereports
     "(bool) (Optional) Include reports."
     :includebans
     "(bool) (Optional) Include ban requests."
     :steamid
     "(uint64) (Optional) Query just for this steamid."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RequestVacStatusForUserV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/RequestVacStatusForUser/v0001"
    "Checks a user's VAC session status. If verification fails, then do not let the user matchmake into a secure game."
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) steamid of the user."
     :appid
     "(uint32) The appid the user is playing."
     :session_id
     "(uint64) session id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "StartSecureMultiplayerSessionV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/StartSecureMultiplayerSession/v0001"
    "Tell the VAC servers that a secure multiplayer session has started"
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) steamid of the user."
     :appid
     "(uint32) The appid the user is playing."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "EndSecureMultiplayerSessionV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/EndSecureMultiplayerSession/v0001"
    "Tell the VAC servers that a secure multiplayer session has ended."
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) steamid of the user."
     :appid
     "(uint32) The appid the user is playing."
     :session_id
     "(uint64) session id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "ReportCheatDataV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/ReportCheatData/v0001"
    "Reports cheat data. Only use on test account that is running the game but not in a multiplayer session."
    :post
    [:key
     "(string) Access key"
     :steamid
     "(uint64) steamid of the user running and reporting the cheat."
     :appid
     "(uint32) The appid."
     :pathandfilename
     "(string) path and file name of the cheat executable."
     :webcheaturl
     "(string) web url where the cheat was found and downloaded."
     :time_now
     "(uint64) local system time now."
     :time_started
     "(uint64) local system time when cheat process started. ( 0 if not yet run )"
     :time_stopped
     "(uint64) local system time when cheat process stopped. ( 0 if still running )"
     :cheatname
     "(string) descriptive name for the cheat."
     :game_process_id
     "(uint32) process ID of the running game."
     :cheat_process_id
     "(uint32) process ID of the cheat process that ran"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamEconomy"
  {"CanTradeV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/CanTrade/v0001"
    ""
    :get
    [:appid
     "(uint32) That the key is associated with. Must be a steam economy app."
     :steamid
     "(uint64) SteamID of user attempting to initiate a trade"
     :targetid
     "(uint64) SteamID of user that is the target of the trade invitation"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FinalizeAssetTransactionV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/FinalizeAssetTransaction/v0001"
    ""
    :post
    [:appid
     "(uint32) The app ID the user is buying assets for"
     :steamid
     "(uint64) SteamID of the user making a purchase"
     :txnid
     "(string) The transaction ID"
     :language
     "(string) The local language for the user"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAssetClassInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetAssetClassInfo/v0001"
    ""
    :get
    [:appid
     "(uint32) Must be a steam economy app."
     :language
     "(string, optional) The user's local language"
     :class_count
     "(uint32) Number of classes requested. Must be at least one."
     :classid0
     "(uint64) Class ID of the nth class."
     :instanceid0
     "(uint64, optional) Instance ID of the nth class."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAssetPricesV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetAssetPrices/v0001"
    ""
    :get
    [:appid
     "(uint32) Must be a steam economy app."
     :currency
     "(string, optional) The currency to filter for"
     :language
     "(string, optional) The user's local language"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetExportedAssetsForUserV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetExportedAssetsForUser/v0001"
    ""
    :get
    [:steamid
     "(uint64) SteamID of user"
     :appid
     "(uint32) The app to get exported items from."
     :contextid
     "(uint64) The context in the app to get exported items from."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMarketPricesV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetMarketPrices/v0001"
    ""
    :get
    [:appid
     "(uint32) Must be a steam economy app."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "StartAssetTransactionV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/StartAssetTransaction/v0001"
    ""
    :post
    [:appid
     "(uint32) The app ID the user is buying assets for"
     :steamid
     "(uint64) SteamID of user making a purchase"
     :assetid0
     "(string) The ID of the first asset the user is buying - there must be at least one"
     :assetquantity0
     "(uint32) The quantity of assetid0's the the user is buying"
     :currency
     "(string) The local currency for the user"
     :language
     "(string) The local language for the user"
     :ipaddress
     "(string) The user's IP address"
     :referrer
     "(string, optional) The referring URL"
     :clientauth
     "(bool, optional) If true (default is false), the authorization will appear in the user's steam client overlay, rather than as a web page - useful for stores that are embedded in products."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "StartTradeV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/StartTrade/v0001"
    ""
    :get
    [:appid
     "(uint32) That the key is associated with. Must be a steam economy app."
     :partya
     "(uint64) SteamID of first user in the trade"
     :partyb
     "(uint64) SteamID of second user in the trade"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconDOTA2_205790"
  {"GetEventStatsForAccountV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetEventStatsForAccount/v0001"
    ""
    :get
    [:eventid
     "(uint32) The League ID of the compendium you're looking for."
     :accountid
     "(uint32) The account ID to look up."
     :language
     "(string, optional) The language to provide hero names in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetGameItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetGameItems/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to provide item names in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetHeroesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetHeroes/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to provide hero names in."
     :itemizedonly
     "(bool, optional) Return a list of itemized heroes only."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetItemIconPathV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetItemIconPath/v0001"
    ""
    :get
    [:iconname
     "(string) The item icon name to get the CDN path of"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetRaritiesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetRarities/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to provide rarity names in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTournamentPrizePoolV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetTournamentPrizePool/v0001"
    ""
    :get
    [:leagueid
     "(uint32, optional) The ID of the league to get the prize pool of"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconMarketService"
  {"GetMarketEligibilityV1"
   (steam-request
    "https://api.steampowered.com/IEconMarketService/GetMarketEligibility/v0001"
    "Checks whether or not an account is allowed to use the market"
    :get
    [:key
     "(string) Access key"
     :steamid
     "(uint64) The SteamID of the user to check"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAssetIDV1"
   (steam-request
    "https://api.steampowered.com/IEconMarketService/GetAssetID/v0001"
    "Returns the asset ID of the item sold in a listing"
    :get
    [:key
     "(string) Access key"
     :appid
     "(uint32) The app that's asking. Must match the app of the listing and must belong to the publisher group that owns the API key making the request"
     :listingid
     "(uint64) The identifier of the listing to get information for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPopularV1"
   (steam-request
    "https://api.steampowered.com/IEconMarketService/GetPopular/v0001"
    "Gets the most popular items"
    :get
    [:key
     "(string) Access key"
     :language
     "(string) The language to use in item descriptions"
     :rows
     "(uint32, optional) Number of rows per page"
     :start
     "(uint32) The result number to start at"
     :filter_appid
     "(uint32) If present, the app ID to limit results to"
     :ecurrency
     "(uint32) If present, prices returned will be represented in this currency"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamPayPalPaymentsHub"
  {"PayPalPaymentsHubPaymentNotificationV1"
   (steam-request
    "https://api.steampowered.com/ISteamPayPalPaymentsHub/PayPalPaymentsHubPaymentNotification/v0001"
    ""
    :post
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IDOTA2Match_205790"
  {"GetLeagueListingV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetLeagueListing/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetLiveLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetLiveLeagueGames/v0001"
    ""
    :get
    [:league_id
     "(uint32, optional) Only show matches of the specified league id"
     :match_id
     "(uint64, optional) Only show matches of the specified match id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMatchDetailsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetMatchDetails/v0001"
    ""
    :get
    [:match_id
     "(uint64) Match id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMatchHistoryV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetMatchHistory/v0001"
    ""
    :get
    [:hero_id
     "(uint32, optional) The ID of the hero that must be in the matches being queried"
     :game_mode
     "(uint32, optional) Which game mode to return matches for"
     :skill
     "(uint32, optional) The average skill range of the match, these can be [1-3] with lower numbers being lower skill. Ignored if an account ID is specified"
     :min_players
     "(string, optional) Minimum number of human players that must be in a match for it to be returned"
     :account_id
     "(string, optional) An account ID to get matches from. This will fail if the user has their match history hidden"
     :league_id
     "(string, optional) The league ID to return games from"
     :start_at_match_id
     "(uint64, optional) The minimum match ID to start from"
     :matches_requested
     "(string, optional) The number of requested matches to return"
     :tournament_games_only
     "(string, optional) Whether or not tournament games should only be returned"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetMatchHistoryBySequenceNumV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetMatchHistoryBySequenceNum/v0001"
    ""
    :get
    [:start_at_match_seq_num
     "(uint64, optional) "
     :matches_requested
     "(uint32, optional) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetScheduledLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetScheduledLeagueGames/v0001"
    ""
    :get
    [:date_min
     "(uint32, optional) The starting time stamp to collect scheduled games from. If ignored, it will use the current time"
     :date_max
     "(uint32, optional) The ending time stamp. If this is more than 7 days past the starting time stamp, it will be clamped to 7 days."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTeamInfoByTeamIDV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetTeamInfoByTeamID/v0001"
    ""
    :get
    [:start_at_team_id
     "(uint64, optional) "
     :teams_requested
     "(uint32, optional) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetTournamentPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetTournamentPlayerStats/v0001"
    ""
    :get
    [:account_id
     "(string) "
     :league_id
     "(string, optional) "
     :hero_id
     "(string, optional) "
     :time_frame
     "(string, optional) "
     :match_id
     "(uint64, optional) "
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITFPromos_205790"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_205790/GetItemID/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :promoid
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_205790/GrantItem/v0001"
    ""
    :post
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :promoid
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamDirectory"
  {"GetCMListV1"
   (steam-request
    "https://api.steampowered.com/ISteamDirectory/GetCMList/v0001"
    ""
    :get
    [:cellid
     "(uint32) Client's Steam cell ID"
     :maxcount
     "(uint32, optional) Max number of servers to return"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IDOTA2Ticket_570"
  {"SetSteamAccountPurchasedV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Ticket_570/SetSteamAccountPurchased/v0001"
    ""
    :post
    [:eventid
     "(uint32) Event ID"
     :steamid
     "(uint64) The 64-bit Steam ID"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "SteamAccountValidForEventV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Ticket_570/SteamAccountValidForEvent/v0001"
    ""
    :get
    [:eventid
     "(uint32) Event ID"
     :steamid
     "(uint64) The 64-bit Steam ID"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITestExternalPrivilegeService"
  {"CallPublisherKeyV1"
   (steam-request
    "https://api.steampowered.com/ITestExternalPrivilegeService/CallPublisherKey/v0001"
    ""
    :post
    [:key
     "(string) Access key"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "CallPublisherKeyOwnsAppV1"
   (steam-request
    "https://api.steampowered.com/ITestExternalPrivilegeService/CallPublisherKeyOwnsApp/v0001"
    ""
    :post
    [:key
     "(string) Access key"
     :appid
     "(uint32)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ITFPromos_570"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_570/GetItemID/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :promoid
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_570/GrantItem/v0001"
    ""
    :post
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :promoid
     "(uint32) The promo ID to grant an item for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamCDN"
  {"SetClientFiltersV1"
   (steam-request
    "https://api.steampowered.com/ISteamCDN/SetClientFilters/v0001"
    ""
    :post
    [:key
     "(string) access key"
     :cdnname
     "(string) Steam name of CDN property"
     :allowedipblocks
     "(string, optional) comma-separated list of allowed IP address blocks in CIDR format - blank for not used"
     :allowedasns
     "(string, optional) comma-separated list of allowed client network AS numbers - blank for not used"
     :allowedipcountries
     "(string, optional) comma-separated list of allowed client IP country codes in ISO 3166-1 format - blank for not used"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamApps"
  {"SetAppBuildLiveV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/SetAppBuildLive/v0001"
    ""
    :post
    [:appid
     "(uint32) AppID of game"
     :buildid
     "(uint32) BuildID"
     :betakey
     "(string) beta key, required. Use public for default branch"
     :description
     "(string, optional) optional description for this build"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServersAtAddressV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetServersAtAddress/v0001"
    ""
    :get
    [:addr
     "(string) IP or IP:queryport to list"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAppDepotVersionsV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppDepotVersions/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of depot"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetCheatingReportsV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetCheatingReports/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game"
     :timebegin
     "(uint32) Time range begin"
     :timeend
     "(uint32) Time range end"
     :includereports
     "(bool) include reports that were not bans"
     :includebans
     "(bool) include reports that were bans"
     :reportidmin
     "(uint64, optional) minimum report id"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAppListV2"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppList/v0002"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAppBuildsV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppBuilds/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game"
     :count
     "(uint32, optional) # of builds to retrieve (default 10)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "UpToDateCheckV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/UpToDateCheck/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game"
     :version
     "(uint32) The installed version of the game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPlayersBannedV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetPlayersBanned/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServerListV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetServerList/v0001"
    ""
    :get
    [:filter
     "(string, optional) Query filter string"
     :limit
     "(uint32, optional) Limit number of servers in the response"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetAppListV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppList/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamWebAPIUtil"
  {"GetServerInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamWebAPIUtil/GetServerInfo/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSupportedAPIListV1"
   (steam-request
    "https://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001"
    ""
    :get
    [:key
     "(string, optional) access key"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IDOTA2Fantasy_205790"
  {"GetFantasyPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_205790/GetFantasyPlayerStats/v0001"
    ""
    :get
    [:FantasyLeagueID
     "(uint32) The fantasy league ID"
     :StartTime
     "(uint32, optional) An optional filter for minimum timestamp"
     :EndTime
     "(uint32, optional) An optional filter for maximum timestamp"
     :matchid
     "(uint64, optional) An optional filter for a specific match"
     :SeriesID
     "(uint32, optional) An optional filter for a specific series"
     :PlayerAccountID
     "(uint32, optional) An optional filter for a specific player"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetPlayerOfficialInfoV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_205790/GetPlayerOfficialInfo/v0001"
    ""
    :get
    [:accountid
     "(uint32) The account ID to look up"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_440"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetSchema/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to return the names in. Defaults to returning string keys."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaURLV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetSchemaURL/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetStoreMetaData/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to results in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetStoreStatusV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetStoreStatus/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IGCVersion_440"
  {"GetClientVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_440/GetClientVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_440/GetServerVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IGCVersion_730"
  {"GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_730/GetServerVersion/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamMicroTxn"
  {"AdjustAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/AdjustAgreement/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user with the agreement"
     :agreementid
     "(uint64) ID of agreement"
     :appid
     "(uint32) AppID of game"
     :nextprocessdate
     "(string) Date for next process"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "QueryTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/QueryTxn/v0002"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :orderid
     "(uint64, optional) 3rd party ID for transaction"
     :transid
     "(uint64, optional) Steam transaction ID"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "CancelAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/CancelAgreement/v0001"
    ""
    :post
    [:steamid
     "(uint64) SteamID of user with the agreement"
     :agreementid
     "(uint64) ID of agreement"
     :appid
     "(uint32) AppID of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "InitTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/InitTxn/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items in cart"
     :language
     "(string) ISO 639-1 language code of description"
     :currency
     "(string) ISO 4217 currency code"
     :usersession
     "(string, optional) session where user will authorize the transaction. client or web (defaults to client)"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :itemid
     [:indexed-array "(uint32) 3rd party ID for item"]
     :qty
     [:indexed-array "(uint32) Quantity of this item"]
     :amount
     [:indexed-array "(int32) Total cost (in cents) of item(s)"]
     :description
     [:indexed-array "(string) Description of item"]
     :category
     [:indexed-array
      "(string, optional) Optional category grouping for item"]
     :associated_bundle
     [:indexed-array
      "(uint32, optional) Optional bundleid of associated bundle"]
     :billingtype
     [:indexed-array
      "(string, optional) Optional recurring billing type"]
     :startdate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :enddate
     [:indexed-array
      "(string, optional) Optional end date for recurring billing"]
     :period
     [:indexed-array
      "(string, optional) Optional period for recurring billing"]
     :frequency
     [:indexed-array
      "(uint32, optional) Optional frequency for recurring billing"]
     :recurringamt
     [:indexed-array
      "(string, optional) Optional recurring billing amount"]
     :bundlecount
     "(uint32, optional) Number of bundles in cart"
     :bundleid
     [:indexed-array
      "(uint32, optional) 3rd party ID of the bundle. This shares the same ID space as 3rd party items."]
     :bundle_qty
     [:indexed-array "(uint32, optional) Quantity of this bundle"]
     :bundle_desc
     [:indexed-array "(string, optional) Description of bundle"]
     :bundle_category
     [:indexed-array
      "(string, optional) Optional category grouping for bundle"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetReportV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetReport/v0003"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :type
     "(string) Report type (GAMESALES, STEAMSTORE, SETTLEMENT)"
     :time
     "(string) Beginning time to start report from (RFC 3339 UTC format)"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetReportV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetReport/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :type
     "(string) Report type (GAMESALES, STEAMSTORE, SETTLEMENT)"
     :time
     "(string) Beginning time to start report from (RFC 3339 UTC format)"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetReportV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetReport/v0002"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :type
     "(string) Report type (GAMESALES, STEAMSTORE, SETTLEMENT)"
     :time
     "(string) Beginning time to start report from (RFC 3339 UTC format)"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FinalizeTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/FinalizeTxn/v0002"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "InitTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/InitTxn/v0002"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items in cart"
     :language
     "(string) ISO 639-1 language code of description"
     :currency
     "(string) ISO 4217 currency code"
     :usersession
     "(string, optional) session where user will authorize the transaction. client or web (defaults to client)"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :itemid
     [:indexed-array "(uint32) 3rd party ID for item"]
     :qty
     [:indexed-array "(uint32) Quantity of this item"]
     :amount
     [:indexed-array "(int32) Total cost (in cents) of item(s)"]
     :description
     [:indexed-array "(string) Description of item"]
     :category
     [:indexed-array
      "(string, optional) Optional category grouping for item"]
     :associated_bundle
     [:indexed-array
      "(uint32, optional) Optional bundleid of associated bundle"]
     :billingtype
     [:indexed-array
      "(string, optional) Optional recurring billing type"]
     :startdate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :enddate
     [:indexed-array
      "(string, optional) Optional end date for recurring billing"]
     :period
     [:indexed-array
      "(string, optional) Optional period for recurring billing"]
     :frequency
     [:indexed-array
      "(uint32, optional) Optional frequency for recurring billing"]
     :recurringamt
     [:indexed-array
      "(string, optional) Optional recurring billing amount"]
     :bundlecount
     "(uint32, optional) Number of bundles in cart"
     :bundleid
     [:indexed-array
      "(uint32, optional) 3rd party ID of the bundle. This shares the same ID space as 3rd party items."]
     :bundle_qty
     [:indexed-array "(uint32, optional) Quantity of this bundle"]
     :bundle_desc
     [:indexed-array "(string, optional) Description of bundle"]
     :bundle_category
     [:indexed-array
      "(string, optional) Optional category grouping for bundle"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetUserInfo/v0001"
    ""
    :get
    [:steamid
     "(uint64, optional) SteamID of user making purchase"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "InitTxnV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/InitTxn/v0003"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game this transaction is for"
     :itemcount
     "(uint32) Number of items in cart"
     :language
     "(string) ISO 639-1 language code of description"
     :currency
     "(string) ISO 4217 currency code"
     :usersession
     "(string, optional) session where user will authorize the transaction. client or web (defaults to client)"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :itemid
     [:indexed-array "(uint32) 3rd party ID for item"]
     :qty
     [:indexed-array "(uint32) Quantity of this item"]
     :amount
     [:indexed-array "(int32) Total cost (in cents) of item(s)"]
     :description
     [:indexed-array "(string) Description of item"]
     :category
     [:indexed-array
      "(string, optional) Optional category grouping for item"]
     :associated_bundle
     [:indexed-array
      "(uint32, optional) Optional bundleid of associated bundle"]
     :billingtype
     [:indexed-array
      "(string, optional) Optional recurring billing type"]
     :startdate
     [:indexed-array
      "(string, optional) Optional start date for recurring billing"]
     :enddate
     [:indexed-array
      "(string, optional) Optional end date for recurring billing"]
     :period
     [:indexed-array
      "(string, optional) Optional period for recurring billing"]
     :frequency
     [:indexed-array
      "(uint32, optional) Optional frequency for recurring billing"]
     :recurringamt
     [:indexed-array
      "(string, optional) Optional recurring billing amount"]
     :bundlecount
     "(uint32, optional) Number of bundles in cart"
     :bundleid
     [:indexed-array
      "(uint32, optional) 3rd party ID of the bundle. This shares the same ID space as 3rd party items."]
     :bundle_qty
     [:indexed-array "(uint32, optional) Quantity of this bundle"]
     :bundle_desc
     [:indexed-array "(string, optional) Description of bundle"]
     :bundle_category
     [:indexed-array
      "(string, optional) Optional category grouping for bundle"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserInfoV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetUserInfo/v0002"
    ""
    :get
    [:steamid
     "(uint64, optional) SteamID of user making purchase"
     :ipaddress
     "(string, optional) ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "FinalizeTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/FinalizeTxn/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RefundTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/RefundTxn/v0002"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "ProcessAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/ProcessAgreement/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :steamid
     "(uint64) SteamID of user with the agreement"
     :agreementid
     "(uint64) ID of agreement"
     :appid
     "(uint32) AppID of game"
     :amount
     "(int32) Total cost (in cents) to charge"
     :currency
     "(string) ISO 4217 currency code"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetUserAgreementInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetUserAgreementInfo/v0001"
    ""
    :get
    [:steamid
     "(uint64) SteamID of user making purchase"
     :appid
     "(uint32) AppID of game"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "QueryTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/QueryTxn/v0001"
    ""
    :get
    [:appid
     "(uint32) AppID of game this transaction is for"
     :orderid
     "(uint64, optional) 3rd party ID for transaction"
     :transid
     "(uint64, optional) Steam transaction ID"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "RefundTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/RefundTxn/v0001"
    ""
    :post
    [:orderid
     "(uint64) 3rd party ID for transaction"
     :appid
     "(uint32) AppID of game this transaction is for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "IEconItems_205790"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetPlayerItems/v0001"
    ""
    :get
    [:steamid
     "(uint64) The Steam ID to fetch items for"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetSchema/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to return the names in. Defaults to returning string keys."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetSchemaURLV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetSchemaURL/v0001"
    ""
    :get
    [:format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetStoreMetaData/v0001"
    ""
    :get
    [:language
     "(string, optional) The language to results in."
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamGameServerStats"
  {"GetGameServerPlayerStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamGameServerStats/GetGameServerPlayerStatsForGame/v0001"
    ""
    :get
    [:key
     "(string) access key"
     :gameid
     "(uint64) game id to get stats for, if not a mod, it's safe to use appid here"
     :appid
     "(uint32) appID of the game"
     :rangestart
     "(string) range start date/time (Format: YYYY-MM-DD HH:MM:SS, seattle local time"
     :rangeend
     "(string) range end date/time (Format: YYYY-MM-DD HH:MM:SS, seattle local time"
     :maxresults
     "(uint32, optional) Max number of results to return (up to 1000)"
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])},
  "ISteamPublishedItemVoting"
  {"ItemVoteSummaryV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemVoting/ItemVoteSummary/v0001"
    ""
    :post
    [:steamid
     "(uint64) Steam ID of user"
     :appid
     "(uint32) appID of product"
     :count
     "(uint32) Count of how many items we are querying"
     :publishedfileid
     [:indexed-array
      "(uint64, optional) The Published File ID who's vote details are required"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"]),
   "UserVoteSummaryV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemVoting/UserVoteSummary/v0001"
    ""
    :post
    [:steamid
     "(uint64) Steam ID of user"
     :count
     "(uint32) Count of how many items we are querying"
     :publishedfileid
     [:indexed-array "(uint64, optional) A Specific Published Item"]
     :format
     "(string, optional) The desired response format: json, xml, or vdf. Default: json"])}})

