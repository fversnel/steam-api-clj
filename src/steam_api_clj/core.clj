(ns
 steam-api-clj.core
 (:require [steam-api-clj.api :refer [steam-request]]))
(declare requests)
(defn
 request
 [interface method parameters]
 ((get-in requests [interface method]) parameters))
(def
 requests
 {"ITFPromos_620"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_620/GetItemID/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\nPromoID [uint32] - The promo ID to grant an item for"
    :get
    [:steamid :PromoID :format]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_620/GrantItem/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\nPromoID [uint32] - The promo ID to grant an item for"
    :post
    [:steamid :PromoID :format])},
  "IPortal2Leaderboards_620"
  {"GetBucketizedDataV1"
   (steam-request
    "https://api.steampowered.com/IPortal2Leaderboards_620/GetBucketizedData/v0001"
    "leaderboardName [string] - The leaderboard name to fetch data for."
    :get
    [:leaderboardName :format])},
  "ISteamRemoteStorage"
  {"EnumerateUserPublishedFilesV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/EnumerateUserPublishedFiles/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product"
    :post
    [:steamid :appid :format]),
   "EnumerateUserSubscribedFilesV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/EnumerateUserSubscribedFiles/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product\nlisttype [uint32, optional] - EUCMListType"
    :post
    [:steamid :appid :listtype :format]),
   "GetCollectionDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/GetCollectionDetails/v0001"
    "collectioncount [uint32] - Number of collections being requested\npublishedfileids[0] [uint64] - collection ids to get the details for"
    :post
    [:collectioncount [:indexed-array :publishedfileids] :format]),
   "GetPublishedFileDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/GetPublishedFileDetails/v0001"
    "itemcount [uint32] - Number of items being requested\npublishedfileids[0] [uint64] - published file id to look up"
    :post
    [:itemcount [:indexed-array :publishedfileids] :format]),
   "GetUGCFileDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/GetUGCFileDetails/v0001"
    "steamid [uint64, optional] - If specified, only returns details if the file is owned by the SteamID specified\nugcid [uint64] - ID of UGC file to get info for\nappid [uint32] - appID of product"
    :get
    [:steamid :ugcid :appid :format]),
   "SetUGCUsedByGCV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/SetUGCUsedByGC/v0001"
    "steamid [uint64] - SteamID of user\nugcid [uint64] - ID of UGC file whose bits are being fiddled with\nappid [uint32] - appID of product to change updating state for\nused [bool] - New state of flag"
    :post
    [:steamid :ugcid :appid :used :format]),
   "SubscribePublishedFileV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/SubscribePublishedFile/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product\npublishedfileid [uint64] - published file id to subscribe to"
    :post
    [:steamid :appid :publishedfileid :format]),
   "UnsubscribePublishedFileV1"
   (steam-request
    "https://api.steampowered.com/ISteamRemoteStorage/UnsubscribePublishedFile/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product\npublishedfileid [uint64] - published file id to unsubscribe from"
    :post
    [:steamid :appid :publishedfileid :format])},
  "IEconItems_730"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format]),
   "GetSchemaV2"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetSchema/v0002"
    "language [string, optional] - The language to return the names in. Defaults to returning string keys."
    :get
    [:language :format]),
   "GetSchemaURLV2"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetSchemaURL/v0002"
    ""
    :get
    [:format]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_730/GetStoreMetaData/v0001"
    "language [string, optional] - The language to results in."
    :get
    [:language :format])},
  "ISteamVideo"
  {"AddVideoV1"
   (steam-request
    "https://api.steampowered.com/ISteamVideo/AddVideo/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of the video\nvideoid [string] - ID of the video on the provider's site\naccountname [string] - Account name of the video's owner on the provider's site"
    :post
    [:steamid :appid :videoid :accountname :format])},
  "IEconService"
  {"FlushInventoryCacheV1"
   (steam-request
    "https://api.steampowered.com/IEconService/FlushInventoryCache/v0001"
    "Flushes the cache for a user's inventory in a specific app context\n\nkey [string] - Access key\nsteamid [uint64] - User to clear cache for.\nappid [uint32] - App to clear cache for.\ncontextid [uint64] - Context to clear cache for."
    :post
    [:key :steamid :appid :contextid :format]),
   "FlushAssetAppearanceCacheV1"
   (steam-request
    "https://api.steampowered.com/IEconService/FlushAssetAppearanceCache/v0001"
    "Flushes the display cache for assets.  This will result in calls to GetAssetClassInfo for each asset class the next time it is displayed.\n\nkey [string] - Access key\nappid [uint32]"
    :post
    [:key :appid :format]),
   "GetTradeOffersV1"
   (steam-request
    "https://api.steampowered.com/IEconService/GetTradeOffers/v0001"
    "Get a list of sent or received trade offers\n\nkey [string] - Access key\nget_sent_offers [bool] - Request the list of sent offers.\nget_received_offers [bool] - Request the list of received offers.\nget_descriptions [bool] - If set, the item display data for the items included in the returned trade offers will also be returned.\nlanguage [string] - The language to use when loading item display data.\nactive_only [bool] - Indicates we should only return offers which are still active, or offers that have changed in state since the time_historical_cutoff\nhistorical_only [bool] - Indicates we should only return offers which are not active.\ntime_historical_cutoff [uint32] - When active_only is set, offers updated since this time will also be returned"
    :get
    [:key
     :get_sent_offers
     :get_received_offers
     :get_descriptions
     :language
     :active_only
     :historical_only
     :time_historical_cutoff
     :format]),
   "GetTradeOfferV1"
   (steam-request
    "https://api.steampowered.com/IEconService/GetTradeOffer/v0001"
    "Gets a specific trade offer\n\nkey [string] - Access key\ntradeofferid [uint64]\nlanguage [string]"
    :get
    [:key :tradeofferid :language :format]),
   "GetTradeOffersSummaryV1"
   (steam-request
    "https://api.steampowered.com/IEconService/GetTradeOffersSummary/v0001"
    "Get counts of pending and new trade offers\n\nkey [string] - Access key\ntime_last_visit [uint32] - The time the user last visited.  If not passed, will use the time the user last visited the trade offer page."
    :get
    [:key :time_last_visit :format]),
   "DeclineTradeOfferV1"
   (steam-request
    "https://api.steampowered.com/IEconService/DeclineTradeOffer/v0001"
    "Decline a trade offer someone sent to us\n\nkey [string] - Access key\ntradeofferid [uint64]"
    :post
    [:key :tradeofferid :format]),
   "CancelTradeOfferV1"
   (steam-request
    "https://api.steampowered.com/IEconService/CancelTradeOffer/v0001"
    "Cancel a trade offer we sent\n\nkey [string] - Access key\ntradeofferid [uint64]"
    :post
    [:key :tradeofferid :format])},
  "IEconItems_221540"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_221540/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format])},
  "ITFPromos_841"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_841/GetItemID/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\nPromoID [uint32] - The promo ID to grant an item for"
    :get
    [:steamid :PromoID :format]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_841/GrantItem/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\nPromoID [uint32] - The promo ID to grant an item for"
    :post
    [:steamid :PromoID :format])},
  "ISteamLeaderboards"
  {"DeleteLeaderboardV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/DeleteLeaderboard/v0001"
    "appid [uint32] - appid of game\nname [string] - name of the leaderboard to delete"
    :post
    [:appid :name :format]),
   "FindOrCreateLeaderboardV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/FindOrCreateLeaderboard/v0001"
    "appid [uint32] - appid of game\nname [string] - name of the leaderboard to create\nsortmethod [string, optional] - sort method to use for this leaderboard (defaults to Ascending)\ndisplaytype [string, optional] - display type for this leaderboard (defaults to Numeric)\ncreateifnotfound [bool, optional] - if this is true the leaderboard will be created if it doesn't exist. Defaults to true.\nonlytrustedwrites [bool, optional] - if this is true the leaderboard scores cannot be set by clients, and can only be set by publisher via SetLeaderboardScore WebAPI. Defaults to false.\nonlyfriendsreads [bool, optional] - if this is true the leaderboard scores can only be read for friends by clients, scores can always be read by publisher. Defaults to false."
    :post
    [:appid
     :name
     :sortmethod
     :displaytype
     :createifnotfound
     :onlytrustedwrites
     :onlyfriendsreads
     :format]),
   "FindOrCreateLeaderboardV2"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/FindOrCreateLeaderboard/v0002"
    "appid [uint32] - appid of game\nname [string] - name of the leaderboard to create\nsortmethod [string, optional] - sort method to use for this leaderboard (defaults to Ascending)\ndisplaytype [string, optional] - display type for this leaderboard (defaults to Numeric)\ncreateifnotfound [bool, optional] - if this is true the leaderboard will be created if it doesn't exist. Defaults to true.\nonlytrustedwrites [bool, optional] - if this is true the leaderboard scores cannot be set by clients, and can only be set by publisher via SetLeaderboardScore WebAPI. Defaults to false.\nonlyfriendsreads [bool, optional] - if this is true the leaderboard scores can only be read for friends by clients, scores can always be read by publisher. Defaults to false."
    :post
    [:appid
     :name
     :sortmethod
     :displaytype
     :createifnotfound
     :onlytrustedwrites
     :onlyfriendsreads
     :format]),
   "GetLeaderboardEntriesV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/GetLeaderboardEntries/v0001"
    "key [string] - access key\nappid [uint32] - appid of game\nrangestart [int32] - range start or 0\nrangeend [int32] - range end or max LB entries\nsteamid [uint64, optional] - SteamID used for friend & around user requests\nleaderboardid [int32] - ID of the leaderboard to view\ndatarequest [uint32] - type of request: RequestGlobal, RequestAroundUser, RequestFriends"
    :get
    [:key
     :appid
     :rangestart
     :rangeend
     :steamid
     :leaderboardid
     :datarequest
     :format]),
   "GetLeaderboardsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/GetLeaderboardsForGame/v0001"
    "key [string] - access key\nappid [uint32] - appid of game"
    :get
    [:key :appid :format]),
   "GetLeaderboardsForGameV2"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/GetLeaderboardsForGame/v0002"
    "key [string] - access key\nappid [uint32] - appid of game"
    :get
    [:key :appid :format]),
   "ResetLeaderboardV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/ResetLeaderboard/v0001"
    "appid [uint32] - appid of game\nleaderboardid [uint32] - numeric ID of the target leaderboard. Can be retrieved from GetLeaderboardsForGame"
    :post
    [:appid :leaderboardid :format]),
   "SetLeaderboardScoreV1"
   (steam-request
    "https://api.steampowered.com/ISteamLeaderboards/SetLeaderboardScore/v0001"
    "appid [uint32] - appid of game\nleaderboardid [uint32] - numeric ID of the target leaderboard. Can be retrieved from GetLeaderboardsForGame\nsteamid [uint64] - steamID to set the score for\nscore [int32] - the score to set for this user\nscoremethod [string] - update method to use. Can be \"KeepBest\" or \"ForceUpdate\"\ndetails [rawbinary, optional] - game-specific details for how the score was earned. Up to 256 bytes."
    :post
    [:appid
     :leaderboardid
     :steamid
     :score
     :scoremethod
     :details
     :format])},
  "IPlayerService"
  {"RecordOfflinePlaytimeV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/RecordOfflinePlaytime/v0001"
    "Tracks playtime for a user when they are offline\n\nsteamid [uint64]\nticket [string]\nplay_sessions [{message}]"
    :post
    [:steamid :ticket :play_sessions :format]),
   "GetRecentlyPlayedGamesV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001"
    "Gets information about a player's recently played games\n\nkey [string] - Access key\nsteamid [uint64] - The player we're asking about\ncount [uint32] - The number of games to return (0/unset: all)"
    :get
    [:key :steamid :count :format]),
   "GetOwnedGamesV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001"
    "Return a list of games owned by the player\n\nkey [string] - Access key\nsteamid [uint64] - The player we're asking about\ninclude_appinfo [bool] - true if we want additional details (name, icon) about each game\ninclude_played_free_games [bool] - Free games are excluded by default.  If this is set, free games the user has played will be returned.\nappids_filter [uint32] - if set, restricts result set to the passed in apps"
    :get
    [:key
     :steamid
     :include_appinfo
     :include_played_free_games
     :appids_filter
     :format]),
   "GetSteamLevelV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetSteamLevel/v0001"
    "Returns the Steam Level of a user\n\nkey [string] - Access key\nsteamid [uint64] - The player we're asking about"
    :get
    [:key :steamid :format]),
   "GetBadgesV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetBadges/v0001"
    "Gets badges that are owned by a specific user\n\nkey [string] - Access key\nsteamid [uint64] - The player we're asking about"
    :get
    [:key :steamid :format]),
   "GetCommunityBadgeProgressV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/GetCommunityBadgeProgress/v0001"
    "Gets all the quests needed to get the specified badge, and which are completed\n\nkey [string] - Access key\nsteamid [uint64] - The player we're asking about\nbadgeid [int32] - The badge we're asking about"
    :get
    [:key :steamid :badgeid :format]),
   "IsPlayingSharedGameV1"
   (steam-request
    "https://api.steampowered.com/IPlayerService/IsPlayingSharedGame/v0001"
    "Returns valid lender SteamID if game currently played is borrowed\n\nkey [string] - Access key\nsteamid [uint64] - The player we're asking about\nappid_playing [uint32] - The game player is currently playing"
    :get
    [:key :steamid :appid_playing :format])},
  "ISteamCommunity"
  {"ReportAbuseV1"
   (steam-request
    "https://api.steampowered.com/ISteamCommunity/ReportAbuse/v0001"
    "key [string] - access key\nsteamidActor [uint64] - SteamID of user doing the reporting\nsteamidTarget [uint64] - SteamID of the entity being accused of abuse\nappid [uint32] - AppID to check for ownership\nabuseType [uint32] - Abuse type code (see EAbuseReportType enum)\ncontentType [uint32] - Content type code (see ECommunityContentType enum)\ndescription [string] - Narrative from user\ngid [uint64, optional] - GID of related record (depends on content type)"
    :post
    [:key
     :steamidActor
     :steamidTarget
     :appid
     :abuseType
     :contentType
     :description
     :gid
     :format])},
  "IBroadcastService"
  {"PostGameDataFrameV1"
   (steam-request
    "https://api.steampowered.com/IBroadcastService/PostGameDataFrame/v0001"
    "Add a game meta data frame to broadcast\n\nkey [string] - Access key\nappid [uint32]\nsteamid [uint64]\nbroadcast_id [uint64]\nframe_data [string]"
    :post
    [:key :appid :steamid :broadcast_id :frame_data :format])},
  "IWorkshopService"
  {"SetItemPaymentRulesV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/SetItemPaymentRules/v0001"
    "Set item payment rules.\n\nkey [string] - Access key\nappid [uint32]\ngameitemid [uint32]\nassociated_workshop_files [{message}]\npartner_accounts [{message}]\nvalidate_only [bool, optional] - Only validates the rules and does not persist them."
    :post
    [:key
     :appid
     :gameitemid
     :associated_workshop_files
     :partner_accounts
     :validate_only
     :format]),
   "GetFinalizedContributorsV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/GetFinalizedContributors/v0001"
    "Get a list of contributors for a specific gameitemid/app combination.\n\nkey [string] - Access key\nappid [uint32]\ngameitemid [uint32]"
    :post
    [:key :appid :gameitemid :format]),
   "GetItemDailyRevenueV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/GetItemDailyRevenue/v0001"
    "Get item revenue for a specific app/item definition for a date range.\n\nkey [string] - Access key\nappid [uint32]\nitem_id [uint32]\ndate_start [uint32]\ndate_end [uint32]"
    :post
    [:key :appid :item_id :date_start :date_end :format]),
   "PopulateItemDescriptionsV1"
   (steam-request
    "https://api.steampowered.com/IWorkshopService/PopulateItemDescriptions/v0001"
    "Populate block of item descriptions.\n\nkey [string] - Access key\nappid [uint32]\nlanguages [{message}]"
    :post
    [:key :appid :languages :format])},
  "ISteamMicroTxnSandbox"
  {"AdjustAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/AdjustAgreement/v0001"
    "steamid [uint64] - SteamID of user with the agreement\nagreementid [uint64] - ID of agreement\nappid [uint32] - AppID of game\nnextprocessdate [string] - Date for next process"
    :post
    [:steamid :agreementid :appid :nextprocessdate :format]),
   "QueryTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/QueryTxn/v0002"
    "appid [uint32] - AppID of game this transaction is for\norderid [uint64, optional] - 3rd party ID for transaction\ntransid [uint64, optional] - Steam transaction ID"
    :get
    [:appid :orderid :transid :format]),
   "CancelAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/CancelAgreement/v0001"
    "steamid [uint64] - SteamID of user with the agreement\nagreementid [uint64] - ID of agreement\nappid [uint32] - AppID of game"
    :post
    [:steamid :agreementid :appid :format]),
   "InitTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/InitTxn/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items in cart\nlanguage [string] - ISO 639-1 language code of description\ncurrency [string] - ISO 4217 currency code\nitemid[0] [uint32] - 3rd party ID for item\nqty[0] [uint32] - Quantity of this item\namount[0] [int32] - Total cost (in cents) of item(s)\ndescription[0] [string] - Description of item\ncategory[0] [string, optional] - Optional category grouping for item\nbillingtype[0] [string, optional] - Optional recurring billing type\nstartdate[0] [string, optional] - Optional start date for recurring billing\nenddate[0] [string, optional] - Optional start date for recurring billing\nperiod[0] [string, optional] - Optional period for recurring billing\nfrequency[0] [uint32, optional] - Optional frequency for recurring billing\nrecurringamt[0] [string, optional] - Optional recurring billing amount"
    :post
    [:orderid
     :steamid
     :appid
     :itemcount
     :language
     :currency
     [:indexed-array :itemid]
     [:indexed-array :qty]
     [:indexed-array :amount]
     [:indexed-array :description]
     [:indexed-array :category]
     [:indexed-array :billingtype]
     [:indexed-array :startdate]
     [:indexed-array :enddate]
     [:indexed-array :period]
     [:indexed-array :frequency]
     [:indexed-array :recurringamt]
     :format]),
   "GetReportV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetReport/v0003"
    "appid [uint32] - AppID of game this transaction is for\ntype [string] - Report type (GAMESALES, STEAMSTORE, SETTLEMENT)\ntime [string] - Beginning time to start report from (RFC 3339 UTC format)\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:appid :type :time :maxresults :format]),
   "GetReportV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetReport/v0001"
    "appid [uint32] - AppID of game this transaction is for\ntype [string] - Report type (GAMESALES, STEAMSTORE, SETTLEMENT)\ntime [string] - Beginning time to start report from (RFC 3339 UTC format)\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:appid :type :time :maxresults :format]),
   "GetReportV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetReport/v0002"
    "appid [uint32] - AppID of game this transaction is for\ntype [string] - Report type (GAMESALES, STEAMSTORE, SETTLEMENT)\ntime [string] - Beginning time to start report from (RFC 3339 UTC format)\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:appid :type :time :maxresults :format]),
   "FinalizeTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/FinalizeTxn/v0002"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format]),
   "InitTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/InitTxn/v0002"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items in cart\nlanguage [string] - ISO 639-1 language code of description\ncurrency [string] - ISO 4217 currency code\nitemid[0] [uint32] - 3rd party ID for item\nqty[0] [uint32] - Quantity of this item\namount[0] [int32] - Total cost (in cents) of item(s)\ndescription[0] [string] - Description of item\ncategory[0] [string, optional] - Optional category grouping for item\nbillingtype[0] [string, optional] - Optional recurring billing type\nstartdate[0] [string, optional] - Optional start date for recurring billing\nenddate[0] [string, optional] - Optional end date for recurring billing\nperiod[0] [string, optional] - Optional period for recurring billing\nfrequency[0] [uint32, optional] - Optional frequency for recurring billing\nrecurringamt[0] [string, optional] - Optional recurring billing amount"
    :post
    [:orderid
     :steamid
     :appid
     :itemcount
     :language
     :currency
     [:indexed-array :itemid]
     [:indexed-array :qty]
     [:indexed-array :amount]
     [:indexed-array :description]
     [:indexed-array :category]
     [:indexed-array :billingtype]
     [:indexed-array :startdate]
     [:indexed-array :enddate]
     [:indexed-array :period]
     [:indexed-array :frequency]
     [:indexed-array :recurringamt]
     :format]),
   "GetUserInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetUserInfo/v0001"
    "steamid [uint64, optional] - SteamID of user making purchase\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
    :get
    [:steamid :ipaddress :format]),
   "InitTxnV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/InitTxn/v0003"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items in cart\nlanguage [string] - ISO 639-1 language code of description\ncurrency [string] - ISO 4217 currency code\nitemid[0] [uint32] - 3rd party ID for item\nqty[0] [uint32] - Quantity of this item\namount[0] [int32] - Total cost (in cents) of item(s)\ndescription[0] [string] - Description of item\ncategory[0] [string, optional] - Optional category grouping for item\nbillingtype[0] [string, optional] - Optional recurring billing type\nstartdate[0] [string, optional] - Optional start date for recurring billing\nenddate[0] [string, optional] - Optional end date for recurring billing\nperiod[0] [string, optional] - Optional period for recurring billing\nfrequency[0] [uint32, optional] - Optional frequency for recurring billing\nrecurringamt[0] [string, optional] - Optional recurring billing amount"
    :post
    [:orderid
     :steamid
     :appid
     :itemcount
     :language
     :currency
     [:indexed-array :itemid]
     [:indexed-array :qty]
     [:indexed-array :amount]
     [:indexed-array :description]
     [:indexed-array :category]
     [:indexed-array :billingtype]
     [:indexed-array :startdate]
     [:indexed-array :enddate]
     [:indexed-array :period]
     [:indexed-array :frequency]
     [:indexed-array :recurringamt]
     :format]),
   "GetUserInfoV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetUserInfo/v0002"
    "steamid [uint64, optional] - SteamID of user making purchase\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
    :get
    [:steamid :ipaddress :format]),
   "FinalizeTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/FinalizeTxn/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format]),
   "RefundTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/RefundTxn/v0002"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format]),
   "ProcessAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/ProcessAgreement/v0001"
    "steamid [uint64] - SteamID of user with the agreement\nagreementid [uint64] - ID of agreement\nappid [uint32] - AppID of game\namount [int32] - Total cost (in cents) to charge\ncurrency [string] - ISO 4217 currency code"
    :post
    [:steamid :agreementid :appid :amount :currency :format]),
   "GetUserAgreementInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/GetUserAgreementInfo/v0001"
    "steamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game"
    :get
    [:steamid :appid :format]),
   "QueryTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/QueryTxn/v0001"
    "appid [uint32] - AppID of game this transaction is for\norderid [uint64, optional] - 3rd party ID for transaction\ntransid [uint64, optional] - Steam transaction ID"
    :get
    [:appid :orderid :transid :format]),
   "RefundTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxnSandbox/RefundTxn/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format])},
  "IEconItems_570"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetSchema/v0001"
    "language [string, optional] - The language to return the names in. Defaults to returning string keys."
    :get
    [:language :format]),
   "GetSchemaURLV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetSchemaURL/v0001"
    ""
    :get
    [:format]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_570/GetStoreMetaData/v0001"
    "language [string, optional] - The language to results in."
    :get
    [:language :format])},
  "ISteamPublishedItemSearch"
  {"RankedByPublicationOrderV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/RankedByPublicationOrder/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product\nstartidx [uint32] - Starting index in the result set (0 based)\ncount [uint32] - Number Requested\ntagcount [uint32] - Number of Tags Specified\nusertagcount [uint32] - Number of User specific tags requested\nhasappadminaccess [bool, optional] - Whether the user making the request is an admin for the app and can see private files\nfileType [uint32, optional] - EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items\ntag[0] [string, optional] - Tag to filter result set\nusertag[0] [string, optional] - A user specific tag"
    :post
    [:steamid
     :appid
     :startidx
     :count
     :tagcount
     :usertagcount
     :hasappadminaccess
     :fileType
     [:indexed-array :tag]
     [:indexed-array :usertag]
     :format]),
   "RankedByTrendV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/RankedByTrend/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product\nstartidx [uint32] - Starting index in the result set (0 based)\ncount [uint32] - Number Requested\ntagcount [uint32] - Number of Tags Specified\nusertagcount [uint32] - Number of User specific tags requested\nhasappadminaccess [bool, optional] - Whether the user making the request is an admin for the app and can see private files\nfileType [uint32, optional] - EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items\ndays [uint32, optional] - [1,7] number of days for the trend period, including today\ntag[0] [string, optional] - Tag to filter result set\nusertag[0] [string, optional] - A user specific tag"
    :post
    [:steamid
     :appid
     :startidx
     :count
     :tagcount
     :usertagcount
     :hasappadminaccess
     :fileType
     :days
     [:indexed-array :tag]
     [:indexed-array :usertag]
     :format]),
   "RankedByVoteV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/RankedByVote/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - appID of product\nstartidx [uint32] - Starting index in the result set (0 based)\ncount [uint32] - Number Requested\ntagcount [uint32] - Number of Tags Specified\nusertagcount [uint32] - Number of User specific tags requested\nhasappadminaccess [bool, optional] - Whether the user making the request is an admin for the app and can see private files\nfileType [uint32, optional] - EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items\ntag[0] [string, optional] - Tag to filter result set\nusertag[0] [string, optional] - A user specific tag"
    :post
    [:steamid
     :appid
     :startidx
     :count
     :tagcount
     :usertagcount
     :hasappadminaccess
     :fileType
     [:indexed-array :tag]
     [:indexed-array :usertag]
     :format]),
   "ResultSetSummaryV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemSearch/ResultSetSummary/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint64] - appID relevant to all subsequent tags\ntagcount [uint32] - Number of Tags Specified\nusertagcount [uint32] - Number of User specific tags requested\nhasappadminaccess [bool, optional] - Whether the user making the request is an admin for the app and can see private files\nfileType [uint32, optional] - EPublishedFileInfoMatchingFileType, defaults to k_PFI_MatchingFileType_Items\ntag[0] [string, optional] - Tag to filter result set\nusertag[0] [string, optional] - A user specific tag"
    :post
    [:steamid
     :appid
     :tagcount
     :usertagcount
     :hasappadminaccess
     :fileType
     [:indexed-array :tag]
     [:indexed-array :usertag]
     :format])},
  "ISteamEnvoy"
  {"PaymentOutNotificationV1"
   (steam-request
    "https://api.steampowered.com/ISteamEnvoy/PaymentOutNotification/v0001"
    ""
    :post
    [:format]),
   "PaymentOutReversalNotificationV1"
   (steam-request
    "https://api.steampowered.com/ISteamEnvoy/PaymentOutReversalNotification/v0001"
    ""
    :post
    [:format])},
  "ITFPromos_730"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_730/GetItemID/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\nPromoID [uint32] - The promo ID to grant an item for"
    :get
    [:steamid :PromoID :format]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_730/GrantItem/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\nPromoID [uint32] - The promo ID to grant an item for"
    :post
    [:steamid :PromoID :format])},
  "IGCVersion_205790"
  {"GetClientVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_205790/GetClientVersion/v0001"
    ""
    :get
    [:format]),
   "GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_205790/GetServerVersion/v0001"
    ""
    :get
    [:format])},
  "IPublishedFileService"
  {"QueryFilesV1"
   (steam-request
    "https://api.steampowered.com/IPublishedFileService/QueryFiles/v0001"
    "Performs a search query for published files\n\nkey [string] - Access key\nquery_type [uint32] - enumeration EPublishedFileQueryType in clientenums.h\npage [uint32] - Current page\nnumperpage [uint32, optional] - (Optional) The number of results, per page to return.\ncreator_appid [uint32] - App that created the files\nappid [uint32] - App that consumes the files\nrequiredtags [string] - Tags to match on. See match_all_tags parameter below\nexcludedtags [string] - (Optional) Tags that must NOT be present on a published file to satisfy the query.\nmatch_all_tags [bool, optional] - If true, then items must have all the tags specified, otherwise they must have at least one of the tags.\nrequired_flags [string] - Required flags that must be set on any returned items\nomitted_flags [string] - Flags that must not be set on any returned items\nsearch_text [string] - Text to match in the item's title or description\nfiletype [uint32] - EPublishedFileInfoMatchingFileType\nchild_publishedfileid [uint64] - Find all items that reference the given item.\ndays [uint32] - If query_type is k_PublishedFileQueryType_RankedByTrend, then this is the number of days to get votes for [1,7].\ninclude_recent_votes_only [bool] - If query_type is k_PublishedFileQueryType_RankedByTrend, then limit result set just to items that have votes within the day range given\ncache_max_age_seconds [uint32, optional] - Allow stale data to be returned for the specified number of seconds.\nlanguage [int32, optional] - Language to search in and also what gets returned. Defaults to English.\nrequired_kv_tags [{message}] - Required key-value tags to match on.\ntotalonly [bool] - (Optional) If true, only return the total number of files that satisfy this query.\nreturn_vote_data [bool] - Return vote data\nreturn_tags [bool] - Return tags in the file details\nreturn_kv_tags [bool] - Return key-value tags in the file details\nreturn_previews [bool] - Return preview image and video details in the file details\nreturn_children [bool] - Return child item ids in the file details\nreturn_short_description [bool] - Populate the short_description field instead of file_description\nreturn_for_sale_data [bool] - Return pricing information, if applicable\nreturn_metadata [bool, optional] - Populate the metadata"
    :get
    [:key
     :query_type
     :page
     :numperpage
     :creator_appid
     :appid
     :requiredtags
     :excludedtags
     :match_all_tags
     :required_flags
     :omitted_flags
     :search_text
     :filetype
     :child_publishedfileid
     :days
     :include_recent_votes_only
     :cache_max_age_seconds
     :language
     :required_kv_tags
     :totalonly
     :return_vote_data
     :return_tags
     :return_kv_tags
     :return_previews
     :return_children
     :return_short_description
     :return_for_sale_data
     :return_metadata
     :format]),
   "SetDeveloperMetadataV1"
   (steam-request
    "https://api.steampowered.com/IPublishedFileService/SetDeveloperMetadata/v0001"
    "Sets the metadata for a developer on the published file\n\nkey [string] - Access key\npublishedfileid [uint64]\nappid [uint32]\nmetadata [string]"
    :post
    [:key :publishedfileid :appid :metadata :format]),
   "UpdateTagsV1"
   (steam-request
    "https://api.steampowered.com/IPublishedFileService/UpdateTags/v0001"
    "Updates tags on the published file\n\nkey [string] - Access key\npublishedfileid [uint64]\nappid [uint32]\nadd_tags [string]\nremove_tags [string]"
    :post
    [:key :publishedfileid :appid :add_tags :remove_tags :format])},
  "ITFItems_440"
  {"GetGoldenWrenchesV2"
   (steam-request
    "https://api.steampowered.com/ITFItems_440/GetGoldenWrenches/v0002"
    ""
    :get
    [:format])},
  "ISteamWebUserPresenceOAuth"
  {"PollStatusV1"
   (steam-request
    "https://api.steampowered.com/ISteamWebUserPresenceOAuth/PollStatus/v0001"
    "steamid [string] - Steam ID of the user\numqid [uint64] - UMQ Session ID\nmessage [uint32] - Message that was last known to the user\npollid [uint32, optional] - Caller-specific poll id\nsectimeout [uint32, optional] - Long-poll timeout in seconds\nsecidletime [uint32, optional] - How many seconds is client considering itself idle, e.g. screen is off\nuse_accountids [uint32, optional] - Boolean, 0 (default): return steamid_from in output, 1: return accountid_from"
    :post
    [:steamid
     :umqid
     :message
     :pollid
     :sectimeout
     :secidletime
     :use_accountids
     :format])},
  "IEconItems_620"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_620/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_620/GetSchema/v0001"
    "language [string, optional] - The language to return the names in. Defaults to returning string keys."
    :get
    [:language :format])},
  "IEconItems_218620"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_218620/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format])},
  "IAccountRecoveryService"
  {"ReportAccountRecoveryDataV1"
   (steam-request
    "https://api.steampowered.com/IAccountRecoveryService/ReportAccountRecoveryData/v0001"
    "Send account recovery data\n\nloginuser_list [string]\ninstall_config [string]\nshasentryfile [string]\nmachineid [string]"
    :post
    [:loginuser_list
     :install_config
     :shasentryfile
     :machineid
     :format]),
   "RetrieveAccountRecoveryDataV1"
   (steam-request
    "https://api.steampowered.com/IAccountRecoveryService/RetrieveAccountRecoveryData/v0001"
    "Send account recovery data\n\nrequesthandle [string]"
    :post
    [:requesthandle :format])},
  "ISteamUserAuth"
  {"AuthenticateUserV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserAuth/AuthenticateUser/v0001"
    "steamid [uint64] - Should be the users steamid, unencrypted.\nsessionkey [rawbinary] - Should be a 32 byte random blob of data, which is then encrypted with RSA using the Steam system's public key.  Randomness is important here for security.\nencrypted_loginkey [rawbinary] - Should be the users hashed loginkey, AES encrypted with the sessionkey."
    :post
    [:steamid :sessionkey :encrypted_loginkey :format]),
   "AuthenticateUserTicketV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserAuth/AuthenticateUserTicket/v0001"
    "key [string] - access key\nappid [uint32] - appid of game\nticket [string] - Ticket from GetAuthSessionTicket."
    :get
    [:key :appid :ticket :format])},
  "ISteamUserOAuth"
  {"GetTokenDetailsV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserOAuth/GetTokenDetails/v0001"
    "access_token [string] - OAuth2 token for which to return details"
    :get
    [:access_token :format])},
  "ISteamWorkshop"
  {"AssociateWorkshopItemsV1"
   (steam-request
    "https://api.steampowered.com/ISteamWorkshop/AssociateWorkshopItems/v0001"
    "appid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items to associate\npublishedfileid[0] [uint64, optional] - the workshop published file id\ngameitemid[0] [uint32, optional] - 3rd party ID for item\nrevenuepercentage[0] [float, optional] - Percentage of revenue the owners of the workshop item will get from the sale of the item [0.0, 100.0].  For bundle-like items, send over an entry for each item in the bundle (gameitemid = bundle id) with different publishedfileids and with the revenue percentage pre-split amongst the items in the bundle (i.e. 30% / 10 items in the bundle)\ngameitemdescription[0] [string, optional] - Game's description of the game item"
    :post
    [:appid
     :itemcount
     [:indexed-array :publishedfileid]
     [:indexed-array :gameitemid]
     [:indexed-array :revenuepercentage]
     [:indexed-array :gameitemdescription]
     :format]),
   "GetContributorsV1"
   (steam-request
    "https://api.steampowered.com/ISteamWorkshop/GetContributors/v0001"
    "appid [uint32] - AppID of game this transaction is for"
    :post
    [:appid :format])},
  "IEconItems_841"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_841/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_841/GetSchema/v0001"
    "language [string, optional] - The language to return the names in. Defaults to returning string keys."
    :get
    [:language :format])},
  "IGameServersService"
  {"GetAccountListV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetAccountList/v0001"
    "Gets a list of game server accounts with their logon tokens\n\nkey [string] - Access key"
    :get
    [:key :format]),
   "CreateAccountV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/CreateAccount/v0001"
    "Creates a persistent game server account\n\nkey [string] - Access key\nappid [uint32] - The app to use the account for\nmemo [string] - The memo to set on the new account"
    :post
    [:key :appid :memo :format]),
   "SetMemoV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/SetMemo/v0001"
    "This method changes the memo associated with the game server account. Memos do not affect the account in any way. The memo shows up in the GetAccountList response and serves only as a reminder of what the account is used for.\n\nkey [string] - Access key\nsteamid [uint64] - The SteamID of the game server to set the memo on\nmemo [string] - The memo to set on the new account"
    :post
    [:key :steamid :memo :format]),
   "ResetLoginTokenV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/ResetLoginToken/v0001"
    "Generates a new login token for the specified game server\n\nkey [string] - Access key\nsteamid [uint64] - The SteamID of the game server to reset the login token of"
    :post
    [:key :steamid :format]),
   "GetAccountPublicInfoV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetAccountPublicInfo/v0001"
    "Gets public information about a given game server account\n\nkey [string] - Access key\nsteamid [uint64] - The SteamID of the game server to get info on"
    :get
    [:key :steamid :format]),
   "GetServerSteamIDsByIPV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetServerSteamIDsByIP/v0001"
    "Gets a list of server SteamIDs given a list of IPs\n\nkey [string] - Access key\nserver_ips [string]"
    :get
    [:key :server_ips :format]),
   "GetServerIPsBySteamIDV1"
   (steam-request
    "https://api.steampowered.com/IGameServersService/GetServerIPsBySteamID/v0001"
    "Gets a list of server IP addresses given a list of SteamIDs\n\nkey [string] - Access key\nserver_steamids [uint64]"
    :get
    [:key :server_steamids :format])},
  "ICSGOServers_730"
  {"GetGameServersStatusV1"
   (steam-request
    "https://api.steampowered.com/ICSGOServers_730/GetGameServersStatus/v0001"
    ""
    :get
    [:format])},
  "IPortal2Leaderboards_841"
  {"GetBucketizedDataV1"
   (steam-request
    "https://api.steampowered.com/IPortal2Leaderboards_841/GetBucketizedData/v0001"
    "leaderboardName [string] - The leaderboard name to fetch data for."
    :get
    [:leaderboardName :format])},
  "ISteamUserStats"
  {"GetPlayerAchievementsV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nappid [uint32] - AppID to get achievements for\nl [string, optional] - Language to return strings for"
    :get
    [:key :steamid :appid :l :format]),
   "GetGlobalAchievementPercentagesForAppV2"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0002"
    "gameid [uint64] - GameID to retrieve the achievement percentages for"
    :get
    [:gameid :format]),
   "GetSchemaForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v0001"
    "key [string] - access key\nappid [uint32] - appid of game\nl [string, optional] - localized langauge to return (english, french, etc.)"
    :get
    [:key :appid :l :format]),
   "GetUserStatsForGameV2"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nappid [uint32] - appid of game"
    :get
    [:key :steamid :appid :format]),
   "GetGlobalStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetGlobalStatsForGame/v0001"
    "appid [uint32] - AppID that we're getting global stats for\ncount [uint32] - Number of stats get data for\nname[0] [string] - Names of stat to get data for\nstartdate [uint32, optional] - Start date for daily totals (unix epoch timestamp)\nenddate [uint32, optional] - End date for daily totals (unix epoch timestamp)"
    :get
    [:appid
     :count
     [:indexed-array :name]
     :startdate
     :enddate
     :format]),
   "GetSchemaForGameV2"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v0002"
    "key [string] - access key\nappid [uint32] - appid of game\nl [string, optional] - localized language to return (english, french, etc.)"
    :get
    [:key :appid :l :format]),
   "GetNumberOfCurrentPlayersV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v0001"
    "appid [uint32] - AppID that we're getting user count for"
    :get
    [:appid :format]),
   "SetUserStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/SetUserStatsForGame/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nappid [uint32] - appid of game\ncount [uint32] - Number of stats and achievements to set a value for (name/value param pairs)\nname[0] [string] - Name of stat or achievement to set\nvalue[0] [uint32] - Value to set"
    :post
    [:key
     :steamid
     :appid
     :count
     [:indexed-array :name]
     [:indexed-array :value]
     :format]),
   "GetGlobalAchievementPercentagesForAppV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetGlobalAchievementPercentagesForApp/v0001"
    "gameid [uint64] - GameID to retrieve the achievement percentages for"
    :get
    [:gameid :format]),
   "GetUserStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nappid [uint32] - appid of game"
    :get
    [:key :steamid :appid :format])},
  "ISteamSpecialSurvey"
  {"CheckUserStatusV1"
   (steam-request
    "https://api.steampowered.com/ISteamSpecialSurvey/CheckUserStatus/v0001"
    "key [string] - access key\nappid [uint32] - appid of game\nsurveyid [uint32] - ID of the survey being taken\nsteamid [uint64] - SteamID of the user taking the survey\ntoken [string] - Survey identity verification token for the user"
    :get
    [:key :appid :surveyid :steamid :token :format]),
   "SetUserFinishedV1"
   (steam-request
    "https://api.steampowered.com/ISteamSpecialSurvey/SetUserFinished/v0001"
    "key [string] - access key\nappid [uint32] - appid of game\nsurveyid [uint32] - ID of the survey being taken\nsteamid [uint64] - SteamID of the user taking the survey\ntoken [string] - Survey identity verification token for the user"
    :post
    [:key :appid :surveyid :steamid :token :format])},
  "IGameNotificationsService"
  {"CreateSessionV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/CreateSession/v0001"
    "Creates an async game session\n\nkey [string] - Access key\nappid [uint32] - The appid to create the session for.\ncontext [uint64] - Game-specified context value the game can used to associate the session with some object on their backend.\ntitle [{message}] - The title of the session to be displayed within each user's list of sessions.\nusers [{message}] - The initial state of all users in the session.\nsteamid [uint64] - (Optional) steamid to make the request on behalf of -- if specified, the user must be in the session and all users being added to the session must be friends with the user."
    :post
    [:key :appid :context :title :users :steamid :format]),
   "UpdateSessionV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/UpdateSession/v0001"
    "Updates a game session\n\nkey [string] - Access key\nsessionid [uint64] - The sessionid to update.\nappid [uint32] - The appid of the session to update.\ntitle [{message}] - (Optional) The new title of the session.  If not specified, the title will not be changed.\nusers [{message}] - (Optional) A list of users whose state will be updated to reflect the given state. If the users are not already in the session, they will be added to it.\nsteamid [uint64] - (Optional) steamid to make the request on behalf of -- if specified, the user must be in the session and all users being added to the session must be friends with the user."
    :post
    [:key :sessionid :appid :title :users :steamid :format]),
   "EnumerateSessionsForAppV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/EnumerateSessionsForApp/v0001"
    "Enumerates a user's sessions\n\nkey [string] - Access key\nappid [uint32] - The sessionid to request details for. Optional. If not specified, all the user's sessions will be returned.\nsteamid [uint64] - The user whose sessions are to be enumerated.\ninclude_all_user_messages [bool] - (Optional) Boolean determining whether the message for all users should be included. Defaults to false.\ninclude_auth_user_message [bool] - (Optional) Boolean determining whether the message for the authenticated user should be included. Defaults to false.\nlanguage [string] - (Optional) Language to localize the text in."
    :get
    [:key
     :appid
     :steamid
     :include_all_user_messages
     :include_auth_user_message
     :language
     :format]),
   "GetSessionDetailsForAppV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/GetSessionDetailsForApp/v0001"
    "Get the details for a specific session\n\nkey [string] - Access key\nsessions [{message}]\nappid [uint32] - The appid for the sessions.\nlanguage [string] - Language to localize the text in."
    :get
    [:key :sessions :appid :language :format]),
   "RequestNotificationsV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/RequestNotifications/v0001"
    "Requests that a user receive game notifications for an app\n\nkey [string] - Access key\nsteamid [uint64] - The steamid to request notifications for.\nappid [uint32] - The appid to request notifications for."
    :post
    [:key :steamid :appid :format]),
   "DeleteSessionV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/DeleteSession/v0001"
    "Deletes an async game session\n\nkey [string] - Access key\nsessionid [uint64] - The sessionid to delete.\nappid [uint32] - The appid of the session to delete.\nsteamid [uint64] - (Optional) steamid to make the request on behalf of -- if specified, the user must be in the session."
    :post
    [:key :sessionid :appid :steamid :format]),
   "DeleteSessionBatchV1"
   (steam-request
    "https://api.steampowered.com/IGameNotificationsService/DeleteSessionBatch/v0001"
    "Deletes a batch of async game sessions\n\nkey [string] - Access key\nsessionid [uint64] - The sessionid to delete.\nappid [uint32] - The appid of the session to delete."
    :post
    [:key :sessionid :appid :format])},
  "IEconDOTA2_570"
  {"GetEventStatsForAccountV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetEventStatsForAccount/v0001"
    "eventid [uint32] - The League ID of the compendium you're looking for.\naccountid [uint32] - The account ID to look up.\nlanguage [string, optional] - The language to provide hero names in."
    :get
    [:eventid :accountid :language :format]),
   "GetGameItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetGameItems/v0001"
    "language [string, optional] - The language to provide item names in."
    :get
    [:language :format]),
   "GetHeroesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001"
    "language [string, optional] - The language to provide hero names in.\nitemizedonly [bool, optional] - Return a list of itemized heroes only."
    :get
    [:language :itemizedonly :format]),
   "GetItemIconPathV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetItemIconPath/v0001"
    "iconname [string] - The item icon name to get the CDN path of"
    :get
    [:iconname :format]),
   "GetRaritiesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetRarities/v0001"
    "language [string, optional] - The language to provide rarity names in."
    :get
    [:language :format]),
   "GetTournamentPrizePoolV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_570/GetTournamentPrizePool/v0001"
    "leagueid [uint32, optional] - The ID of the league to get the prize pool of"
    :get
    [:leagueid :format])},
  "ITFPromos_440"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_440/GetItemID/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\npromoid [uint32] - The promo ID to grant an item for"
    :get
    [:steamid :promoid :format]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_440/GrantItem/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\npromoid [uint32] - The promo ID to grant an item for"
    :post
    [:steamid :promoid :format])},
  "ISteamUser"
  {"ResolveVanityURLV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001"
    "key [string] - access key\nvanityurl [string] - The vanity URL to get a SteamID for\nurl_type [int32, optional] - The type of vanity URL. 1 (default): Individual profile, 2: Group, 3: Official game group"
    :get
    [:key :vanityurl :url_type :format]),
   "GrantPackageV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GrantPackage/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\npackageid [uint32] - PackageID to grant\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx).\nthirdpartykey [string, optional] - Optionally associate third party key during grant. 'thirdpartyappid' will have to be set.\nthirdpartyappid [uint32, optional] - Has to be set if 'thirdpartykey' is set. The appid associated with the 'thirdpartykey'."
    :post
    [:key
     :steamid
     :packageid
     :ipaddress
     :thirdpartykey
     :thirdpartyappid
     :format]),
   "GetAppPriceInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetAppPriceInfo/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nappids [string] - Comma-delimited list of appids (max: 100)"
    :get
    [:key :steamid :appids :format]),
   "GetPublisherAppOwnershipV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPublisherAppOwnership/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user"
    :get
    [:key :steamid :format]),
   "GetPublisherAppOwnershipV2"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPublisherAppOwnership/v0002"
    "key [string] - access key\nsteamid [uint64] - SteamID of user"
    :get
    [:key :steamid :format]),
   "GetUserGroupListV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetUserGroupList/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user"
    :get
    [:key :steamid :format]),
   "GetPlayerSummariesV2"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002"
    "key [string] - access key\nsteamids [string] - Comma-delimited list of SteamIDs (max: 100)"
    :get
    [:key :steamids :format]),
   "GetPlayerBansV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPlayerBans/v0001"
    "key [string] - access key\nsteamids [string] - Comma-delimited list of SteamIDs"
    :get
    [:key :steamids :format]),
   "GetFriendListV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetFriendList/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nrelationship [string, optional] - relationship type (ex: friend)"
    :get
    [:key :steamid :relationship :format]),
   "GetPlayerSummariesV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0001"
    "key [string] - access key\nsteamids [string] - Comma-delimited list of SteamIDs"
    :get
    [:key :steamids :format]),
   "CheckAppOwnershipV1"
   (steam-request
    "https://api.steampowered.com/ISteamUser/CheckAppOwnership/v0001"
    "key [string] - access key\nsteamid [uint64] - SteamID of user\nappid [uint32] - AppID to check for ownership"
    :get
    [:key :steamid :appid :format])},
  "IDOTA2Match_570"
  {"GetLeagueListingV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetLeagueListing/v0001"
    ""
    :get
    [:format]),
   "GetLiveLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v0001"
    "league_id [uint32, optional] - Only show matches of the specified league id\nmatch_id [uint64, optional] - Only show matches of the specified match id"
    :get
    [:league_id :match_id :format]),
   "GetMatchDetailsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v0001"
    "match_id [uint64] - Match id"
    :get
    [:match_id :format]),
   "GetMatchHistoryV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v0001"
    "hero_id [uint32, optional] - The ID of the hero that must be in the matches being queried\ngame_mode [uint32, optional] - Which game mode to return matches for\nskill [uint32, optional] - The average skill range of the match, these can be [1-3] with lower numbers being lower skill. Ignored if an account ID is specified\nmin_players [string, optional] - Minimum number of human players that must be in a match for it to be returned\naccount_id [string, optional] - An account ID to get matches from. This will fail if the user has their match history hidden\nleague_id [string, optional] - The league ID to return games from\nstart_at_match_id [uint64, optional] - The minimum match ID to start from\nmatches_requested [string, optional] - The number of requested matches to return\ntournament_games_only [string, optional] - Whether or not tournament games should only be returned"
    :get
    [:hero_id
     :game_mode
     :skill
     :min_players
     :account_id
     :league_id
     :start_at_match_id
     :matches_requested
     :tournament_games_only
     :format]),
   "GetMatchHistoryBySequenceNumV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001"
    "start_at_match_seq_num [uint64, optional] - \nmatches_requested [uint32, optional] - "
    :get
    [:start_at_match_seq_num :matches_requested :format]),
   "GetScheduledLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetScheduledLeagueGames/v0001"
    "date_min [uint32, optional] - The starting time stamp to collect scheduled games from. If ignored, it will use the current time\ndate_max [uint32, optional] - The ending time stamp. If this is more than 7 days past the starting time stamp, it will be clamped to 7 days."
    :get
    [:date_min :date_max :format]),
   "GetTeamInfoByTeamIDV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetTeamInfoByTeamID/v0001"
    "start_at_team_id [uint64, optional] - \nteams_requested [uint32, optional] - "
    :get
    [:start_at_team_id :teams_requested :format]),
   "GetTournamentPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_570/GetTournamentPlayerStats/v0001"
    "account_id [string] - \nleague_id [string, optional] - \nhero_id [string, optional] - \ntime_frame [string, optional] - \nmatch_id [uint64, optional] - \nphase_id [uint32, optional] - "
    :get
    [:account_id
     :league_id
     :hero_id
     :time_frame
     :match_id
     :phase_id
     :format])},
  "IDOTA2MatchStats_570"
  {"GetRealtimeStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2MatchStats_570/GetRealtimeStats/v0001"
    "server_steam_id [uint64] - "
    :get
    [:server_steam_id :format])},
  "IGCVersion_570"
  {"GetClientVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_570/GetClientVersion/v0001"
    ""
    :get
    [:format]),
   "GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_570/GetServerVersion/v0001"
    ""
    :get
    [:format])},
  "IDOTA2Fantasy_570"
  {"GetFantasyPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_570/GetFantasyPlayerStats/v0001"
    "FantasyLeagueID [uint32] - The fantasy league ID\nStartTime [uint32, optional] - An optional filter for minimum timestamp\nEndTime [uint32, optional] - An optional filter for maximum timestamp\nmatchid [uint64, optional] - An optional filter for a specific match\nSeriesID [uint32, optional] - An optional filter for a specific series\nPlayerAccountID [uint32, optional] - An optional filter for a specific player"
    :get
    [:FantasyLeagueID
     :StartTime
     :EndTime
     :matchid
     :SeriesID
     :PlayerAccountID
     :format]),
   "GetPlayerOfficialInfoV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_570/GetPlayerOfficialInfo/v0001"
    "accountid [uint32] - The account ID to look up"
    :get
    [:accountid :format])},
  "IEconItems_238460"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_238460/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format])},
  "ISteamNews"
  {"GetNewsForAppV1"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0001"
    "appid [uint32] - AppID to retrieve news for\nmaxlength [uint32, optional] - Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit.\nenddate [uint32, optional] - Retrieve posts earlier than this date (unix epoch timestamp)\ncount [uint32, optional] - # of posts to retrieve (default 20)"
    :get
    [:appid :maxlength :enddate :count :format]),
   "GetNewsForAppV2"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002"
    "appid [uint32] - AppID to retrieve news for\nmaxlength [uint32, optional] - Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit.\nenddate [uint32, optional] - Retrieve posts earlier than this date (unix epoch timestamp)\ncount [uint32, optional] - # of posts to retrieve (default 20)\nfeeds [string, optional] - Comma-seperated list of feed names to return news for"
    :get
    [:appid :maxlength :enddate :count :feeds :format]),
   "GetNewsForAppAuthedV1"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForAppAuthed/v0001"
    "key [string] - access key\nappid [uint32] - AppID to retrieve news for\nmaxlength [uint32, optional] - Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit.\nenddate [uint32, optional] - Retrieve posts earlier than this date (unix epoch timestamp)\ncount [uint32, optional] - # of posts to retrieve (default 20)"
    :get
    [:key :appid :maxlength :enddate :count :format]),
   "GetNewsForAppAuthedV2"
   (steam-request
    "https://api.steampowered.com/ISteamNews/GetNewsForAppAuthed/v0002"
    "key [string] - access key\nappid [uint32] - AppID to retrieve news for\nmaxlength [uint32, optional] - Maximum length for the content to return, if this is 0 the full content is returned, if it's less then a blurb is generated to fit.\nenddate [uint32, optional] - Retrieve posts earlier than this date (unix epoch timestamp)\ncount [uint32, optional] - # of posts to retrieve (default 20)\nfeeds [string, optional] - Comma-seperated list of feed names to return news for"
    :get
    [:key :appid :maxlength :enddate :count :feeds :format])},
  "ICheatReportingService"
  {"ReportPlayerCheatingV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/ReportPlayerCheating/v0001"
    "Reports a player cheating\n\nkey [string] - Access key\nsteamid [uint64] - steamid of the user who is reported as cheating.\nappid [uint32] - The appid.\nsteamidreporter [uint64] - (Optional) steamid of the user or game server who is reporting the cheating.\nappdata [uint64] - (Optional) App specific data about the cheating.\nheuristic [bool] - (Optional) extra information about the source of the cheating - was it a heuristic.\ndetection [bool] - (Optional) extra information about the source of the cheating - was it a detection.\nplayerreport [bool] - (Optional) extra information about the source of the cheating - was it a player report.\nnoreportid [bool] - (Optional) dont return report id\ngamemode [uint32] - (Optional) extra information about state of game - was it a specific type of game play (0 = generic)\nsuspicionstarttime [uint32] - (Optional) extra information indicating how far back the game thinks is interesting for this user\nseverity [uint32] - (Optional) level of severity of bad action being reported"
    :post
    [:key
     :steamid
     :appid
     :steamidreporter
     :appdata
     :heuristic
     :detection
     :playerreport
     :noreportid
     :gamemode
     :suspicionstarttime
     :severity
     :format]),
   "RequestPlayerGameBanV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/RequestPlayerGameBan/v0001"
    "Requests a ban on a player\n\nkey [string] - Access key\nsteamid [uint64] - steamid of the user who is reported as cheating.\nappid [uint32] - The appid.\nreportid [uint64] - The reportid originally used to report cheating.\ncheatdescription [string] - Text describing cheating infraction.\nduration [uint32] - Ban duration requested in seconds.\ndelayban [bool] - Delay the ban according to default ban delay rules.\nflags [uint32] - Additional information about the ban request."
    :post
    [:key
     :steamid
     :appid
     :reportid
     :cheatdescription
     :duration
     :delayban
     :flags
     :format]),
   "GetCheatingReportsV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/GetCheatingReports/v0001"
    "Get a list of cheating reports submitted for this app\n\nkey [string] - Access key\nappid [uint32] - The appid.\ntimeend [uint32] - The beginning of the time range .\ntimebegin [uint32] - The end of the time range.\nreportidmin [uint64] - Minimum reportID to include\nincludereports [bool] - (Optional) Include reports.\nincludebans [bool] - (Optional) Include ban requests.\nsteamid [uint64] - (Optional) Query just for this steamid."
    :get
    [:key
     :appid
     :timeend
     :timebegin
     :reportidmin
     :includereports
     :includebans
     :steamid
     :format]),
   "RequestVacStatusForUserV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/RequestVacStatusForUser/v0001"
    "Checks a user's VAC session status. If verification fails, then do not let the user matchmake into a secure game.\n\nkey [string] - Access key\nsteamid [uint64] - steamid of the user.\nappid [uint32] - The appid the user is playing.\nsession_id [uint64] - session id"
    :post
    [:key :steamid :appid :session_id :format]),
   "StartSecureMultiplayerSessionV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/StartSecureMultiplayerSession/v0001"
    "Tell the VAC servers that a secure multiplayer session has started\n\nkey [string] - Access key\nsteamid [uint64] - steamid of the user.\nappid [uint32] - The appid the user is playing."
    :post
    [:key :steamid :appid :format]),
   "EndSecureMultiplayerSessionV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/EndSecureMultiplayerSession/v0001"
    "Tell the VAC servers that a secure multiplayer session has ended.\n\nkey [string] - Access key\nsteamid [uint64] - steamid of the user.\nappid [uint32] - The appid the user is playing.\nsession_id [uint64] - session id"
    :post
    [:key :steamid :appid :session_id :format]),
   "ReportCheatDataV1"
   (steam-request
    "https://api.steampowered.com/ICheatReportingService/ReportCheatData/v0001"
    "Reports cheat data. Only use on test account that is running the game but not in a multiplayer session.\n\nkey [string] - Access key\nsteamid [uint64] - steamid of the user running and reporting the cheat.\nappid [uint32] - The appid.\npathandfilename [string] - path and file name of the cheat executable.\nwebcheaturl [string] - web url where the cheat was found and downloaded.\ntime_now [uint64] - local system time now.\ntime_started [uint64] - local system time when cheat process started. ( 0 if not yet run )\ntime_stopped [uint64] - local system time when cheat process stopped. ( 0 if still running )\ncheatname [string] - descriptive name for the cheat.\ngame_process_id [uint32] - process ID of the running game.\ncheat_process_id [uint32] - process ID of the cheat process that ran"
    :post
    [:key
     :steamid
     :appid
     :pathandfilename
     :webcheaturl
     :time_now
     :time_started
     :time_stopped
     :cheatname
     :game_process_id
     :cheat_process_id
     :format])},
  "ISteamEconomy"
  {"CanTradeV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/CanTrade/v0001"
    "appid [uint32] - That the key is associated with. Must be a steam economy app.\nsteamid [uint64] - SteamID of user attempting to initiate a trade\ntargetid [uint64] - SteamID of user that is the target of the trade invitation"
    :get
    [:appid :steamid :targetid :format]),
   "FinalizeAssetTransactionV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/FinalizeAssetTransaction/v0001"
    "appid [uint32] - The app ID the user is buying assets for\nsteamid [uint64] - SteamID of the user making a purchase\ntxnid [string] - The transaction ID\nlanguage [string] - The local language for the user"
    :post
    [:appid :steamid :txnid :language :format]),
   "GetAssetClassInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetAssetClassInfo/v0001"
    "appid [uint32] - Must be a steam economy app.\nlanguage [string, optional] - The user's local language\nclass_count [uint32] - Number of classes requested. Must be at least one.\nclassid0 [uint64] - Class ID of the nth class.\ninstanceid0 [uint64, optional] - Instance ID of the nth class."
    :get
    [:appid :language :class_count :classid0 :instanceid0 :format]),
   "GetAssetPricesV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetAssetPrices/v0001"
    "appid [uint32] - Must be a steam economy app.\ncurrency [string, optional] - The currency to filter for\nlanguage [string, optional] - The user's local language"
    :get
    [:appid :currency :language :format]),
   "GetExportedAssetsForUserV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetExportedAssetsForUser/v0001"
    "steamid [uint64] - SteamID of user\nappid [uint32] - The app to get exported items from.\ncontextid [uint64] - The context in the app to get exported items from."
    :get
    [:steamid :appid :contextid :format]),
   "GetMarketPricesV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/GetMarketPrices/v0001"
    "appid [uint32] - Must be a steam economy app."
    :get
    [:appid :format]),
   "StartAssetTransactionV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/StartAssetTransaction/v0001"
    "appid [uint32] - The app ID the user is buying assets for\nsteamid [uint64] - SteamID of user making a purchase\nassetid0 [string] - The ID of the first asset the user is buying - there must be at least one\nassetquantity0 [uint32] - The quantity of assetid0's the the user is buying\ncurrency [string] - The local currency for the user\nlanguage [string] - The local language for the user\nipaddress [string] - The user's IP address\nreferrer [string, optional] - The referring URL\nclientauth [bool, optional] - If true (default is false), the authorization will appear in the user's steam client overlay, rather than as a web page - useful for stores that are embedded in products."
    :post
    [:appid
     :steamid
     :assetid0
     :assetquantity0
     :currency
     :language
     :ipaddress
     :referrer
     :clientauth
     :format]),
   "StartTradeV1"
   (steam-request
    "https://api.steampowered.com/ISteamEconomy/StartTrade/v0001"
    "appid [uint32] - That the key is associated with. Must be a steam economy app.\npartya [uint64] - SteamID of first user in the trade\npartyb [uint64] - SteamID of second user in the trade"
    :get
    [:appid :partya :partyb :format])},
  "IEconDOTA2_205790"
  {"GetEventStatsForAccountV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetEventStatsForAccount/v0001"
    "eventid [uint32] - The League ID of the compendium you're looking for.\naccountid [uint32] - The account ID to look up.\nlanguage [string, optional] - The language to provide hero names in."
    :get
    [:eventid :accountid :language :format]),
   "GetGameItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetGameItems/v0001"
    "language [string, optional] - The language to provide item names in."
    :get
    [:language :format]),
   "GetHeroesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetHeroes/v0001"
    "language [string, optional] - The language to provide hero names in.\nitemizedonly [bool, optional] - Return a list of itemized heroes only."
    :get
    [:language :itemizedonly :format]),
   "GetItemIconPathV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetItemIconPath/v0001"
    "iconname [string] - The item icon name to get the CDN path of"
    :get
    [:iconname :format]),
   "GetRaritiesV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetRarities/v0001"
    "language [string, optional] - The language to provide rarity names in."
    :get
    [:language :format]),
   "GetTournamentPrizePoolV1"
   (steam-request
    "https://api.steampowered.com/IEconDOTA2_205790/GetTournamentPrizePool/v0001"
    "leagueid [uint32, optional] - The ID of the league to get the prize pool of"
    :get
    [:leagueid :format])},
  "IEconMarketService"
  {"GetMarketEligibilityV1"
   (steam-request
    "https://api.steampowered.com/IEconMarketService/GetMarketEligibility/v0001"
    "Checks whether or not an account is allowed to use the market\n\nkey [string] - Access key\nsteamid [uint64] - The SteamID of the user to check"
    :get
    [:key :steamid :format]),
   "GetAssetIDV1"
   (steam-request
    "https://api.steampowered.com/IEconMarketService/GetAssetID/v0001"
    "Returns the asset ID of the item sold in a listing\n\nkey [string] - Access key\nappid [uint32] - The app that's asking. Must match the app of the listing and must belong to the publisher group that owns the API key making the request\nlistingid [uint64] - The identifier of the listing to get information for"
    :get
    [:key :appid :listingid :format]),
   "GetPopularV1"
   (steam-request
    "https://api.steampowered.com/IEconMarketService/GetPopular/v0001"
    "Gets the most popular items\n\nkey [string] - Access key\nlanguage [string] - The language to use in item descriptions\nrows [uint32, optional] - Number of rows per page\nstart [uint32] - The result number to start at\nfilter_appid [uint32] - If present, the app ID to limit results to\necurrency [uint32] - If present, prices returned will be represented in this currency"
    :get
    [:key :language :rows :start :filter_appid :ecurrency :format])},
  "ISteamPayPalPaymentsHub"
  {"PayPalPaymentsHubPaymentNotificationV1"
   (steam-request
    "https://api.steampowered.com/ISteamPayPalPaymentsHub/PayPalPaymentsHubPaymentNotification/v0001"
    ""
    :post
    [:format])},
  "IDOTA2Match_205790"
  {"GetLeagueListingV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetLeagueListing/v0001"
    ""
    :get
    [:format]),
   "GetLiveLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetLiveLeagueGames/v0001"
    "league_id [uint32, optional] - Only show matches of the specified league id\nmatch_id [uint64, optional] - Only show matches of the specified match id"
    :get
    [:league_id :match_id :format]),
   "GetMatchDetailsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetMatchDetails/v0001"
    "match_id [uint64] - Match id"
    :get
    [:match_id :format]),
   "GetMatchHistoryV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetMatchHistory/v0001"
    "hero_id [uint32, optional] - The ID of the hero that must be in the matches being queried\ngame_mode [uint32, optional] - Which game mode to return matches for\nskill [uint32, optional] - The average skill range of the match, these can be [1-3] with lower numbers being lower skill. Ignored if an account ID is specified\nmin_players [string, optional] - Minimum number of human players that must be in a match for it to be returned\naccount_id [string, optional] - An account ID to get matches from. This will fail if the user has their match history hidden\nleague_id [string, optional] - The league ID to return games from\nstart_at_match_id [uint64, optional] - The minimum match ID to start from\nmatches_requested [string, optional] - The number of requested matches to return\ntournament_games_only [string, optional] - Whether or not tournament games should only be returned"
    :get
    [:hero_id
     :game_mode
     :skill
     :min_players
     :account_id
     :league_id
     :start_at_match_id
     :matches_requested
     :tournament_games_only
     :format]),
   "GetMatchHistoryBySequenceNumV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetMatchHistoryBySequenceNum/v0001"
    "start_at_match_seq_num [uint64, optional] - \nmatches_requested [uint32, optional] - "
    :get
    [:start_at_match_seq_num :matches_requested :format]),
   "GetScheduledLeagueGamesV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetScheduledLeagueGames/v0001"
    "date_min [uint32, optional] - The starting time stamp to collect scheduled games from. If ignored, it will use the current time\ndate_max [uint32, optional] - The ending time stamp. If this is more than 7 days past the starting time stamp, it will be clamped to 7 days."
    :get
    [:date_min :date_max :format]),
   "GetTeamInfoByTeamIDV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetTeamInfoByTeamID/v0001"
    "start_at_team_id [uint64, optional] - \nteams_requested [uint32, optional] - "
    :get
    [:start_at_team_id :teams_requested :format]),
   "GetTournamentPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Match_205790/GetTournamentPlayerStats/v0001"
    "account_id [string] - \nleague_id [string, optional] - \nhero_id [string, optional] - \ntime_frame [string, optional] - \nmatch_id [uint64, optional] - "
    :get
    [:account_id :league_id :hero_id :time_frame :match_id :format])},
  "ITFPromos_205790"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_205790/GetItemID/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\npromoid [uint32] - The promo ID to grant an item for"
    :get
    [:steamid :promoid :format]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_205790/GrantItem/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\npromoid [uint32] - The promo ID to grant an item for"
    :post
    [:steamid :promoid :format])},
  "ISteamDirectory"
  {"GetCMListV1"
   (steam-request
    "https://api.steampowered.com/ISteamDirectory/GetCMList/v0001"
    "cellid [uint32] - Client's Steam cell ID\nmaxcount [uint32, optional] - Max number of servers to return"
    :get
    [:cellid :maxcount :format])},
  "IDOTA2Ticket_570"
  {"SetSteamAccountPurchasedV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Ticket_570/SetSteamAccountPurchased/v0001"
    "eventid [uint32] - Event ID\nsteamid [uint64] - The 64-bit Steam ID"
    :post
    [:eventid :steamid :format]),
   "SteamAccountValidForEventV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Ticket_570/SteamAccountValidForEvent/v0001"
    "eventid [uint32] - Event ID\nsteamid [uint64] - The 64-bit Steam ID"
    :get
    [:eventid :steamid :format])},
  "ITestExternalPrivilegeService"
  {"CallPublisherKeyV1"
   (steam-request
    "https://api.steampowered.com/ITestExternalPrivilegeService/CallPublisherKey/v0001"
    "key [string] - Access key"
    :post
    [:key :format]),
   "CallPublisherKeyOwnsAppV1"
   (steam-request
    "https://api.steampowered.com/ITestExternalPrivilegeService/CallPublisherKeyOwnsApp/v0001"
    "key [string] - Access key\nappid [uint32]"
    :post
    [:key :appid :format])},
  "ITFPromos_570"
  {"GetItemIDV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_570/GetItemID/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\npromoid [uint32] - The promo ID to grant an item for"
    :get
    [:steamid :promoid :format]),
   "GrantItemV1"
   (steam-request
    "https://api.steampowered.com/ITFPromos_570/GrantItem/v0001"
    "steamid [uint64] - The Steam ID to fetch items for\npromoid [uint32] - The promo ID to grant an item for"
    :post
    [:steamid :promoid :format])},
  "ISteamCDN"
  {"SetClientFiltersV1"
   (steam-request
    "https://api.steampowered.com/ISteamCDN/SetClientFilters/v0001"
    "key [string] - access key\ncdnname [string] - Steam name of CDN property\nallowedipblocks [string, optional] - comma-separated list of allowed IP address blocks in CIDR format - blank for not used\nallowedasns [string, optional] - comma-separated list of allowed client network AS numbers - blank for not used\nallowedipcountries [string, optional] - comma-separated list of allowed client IP country codes in ISO 3166-1 format - blank for not used"
    :post
    [:key
     :cdnname
     :allowedipblocks
     :allowedasns
     :allowedipcountries
     :format])},
  "ISteamApps"
  {"SetAppBuildLiveV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/SetAppBuildLive/v0001"
    "appid [uint32] - AppID of game\nbuildid [uint32] - BuildID\nbetakey [string] - beta key, required. Use public for default branch\ndescription [string, optional] - optional description for this build"
    :post
    [:appid :buildid :betakey :description :format]),
   "GetServersAtAddressV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetServersAtAddress/v0001"
    "addr [string] - IP or IP:queryport to list"
    :get
    [:addr :format]),
   "GetAppDepotVersionsV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppDepotVersions/v0001"
    "appid [uint32] - AppID of depot"
    :get
    [:appid :format]),
   "GetCheatingReportsV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetCheatingReports/v0001"
    "appid [uint32] - AppID of game\ntimebegin [uint32] - Time range begin\ntimeend [uint32] - Time range end\nincludereports [bool] - include reports that were not bans\nincludebans [bool] - include reports that were bans\nreportidmin [uint64, optional] - minimum report id"
    :get
    [:appid
     :timebegin
     :timeend
     :includereports
     :includebans
     :reportidmin
     :format]),
   "GetAppListV2"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppList/v0002"
    ""
    :get
    [:format]),
   "GetAppBuildsV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppBuilds/v0001"
    "appid [uint32] - AppID of game\ncount [uint32, optional] - # of builds to retrieve (default 10)"
    :get
    [:appid :count :format]),
   "UpToDateCheckV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/UpToDateCheck/v0001"
    "appid [uint32] - AppID of game\nversion [uint32] - The installed version of the game"
    :get
    [:appid :version :format]),
   "GetPlayersBannedV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetPlayersBanned/v0001"
    "appid [uint32] - AppID of game"
    :get
    [:appid :format]),
   "GetServerListV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetServerList/v0001"
    "filter [string, optional] - Query filter string\nlimit [uint32, optional] - Limit number of servers in the response"
    :get
    [:filter :limit :format]),
   "GetAppListV1"
   (steam-request
    "https://api.steampowered.com/ISteamApps/GetAppList/v0001"
    ""
    :get
    [:format])},
  "ISteamWebAPIUtil"
  {"GetServerInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamWebAPIUtil/GetServerInfo/v0001"
    ""
    :get
    [:format]),
   "GetSupportedAPIListV1"
   (steam-request
    "https://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001"
    "key [string, optional] - access key"
    :get
    [:key :format])},
  "IDOTA2Fantasy_205790"
  {"GetFantasyPlayerStatsV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_205790/GetFantasyPlayerStats/v0001"
    "FantasyLeagueID [uint32] - The fantasy league ID\nStartTime [uint32, optional] - An optional filter for minimum timestamp\nEndTime [uint32, optional] - An optional filter for maximum timestamp\nmatchid [uint64, optional] - An optional filter for a specific match\nSeriesID [uint32, optional] - An optional filter for a specific series\nPlayerAccountID [uint32, optional] - An optional filter for a specific player"
    :get
    [:FantasyLeagueID
     :StartTime
     :EndTime
     :matchid
     :SeriesID
     :PlayerAccountID
     :format]),
   "GetPlayerOfficialInfoV1"
   (steam-request
    "https://api.steampowered.com/IDOTA2Fantasy_205790/GetPlayerOfficialInfo/v0001"
    "accountid [uint32] - The account ID to look up"
    :get
    [:accountid :format])},
  "IEconItems_440"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetSchema/v0001"
    "language [string, optional] - The language to return the names in. Defaults to returning string keys."
    :get
    [:language :format]),
   "GetSchemaURLV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetSchemaURL/v0001"
    ""
    :get
    [:format]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetStoreMetaData/v0001"
    "language [string, optional] - The language to results in."
    :get
    [:language :format]),
   "GetStoreStatusV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_440/GetStoreStatus/v0001"
    ""
    :get
    [:format])},
  "IGCVersion_440"
  {"GetClientVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_440/GetClientVersion/v0001"
    ""
    :get
    [:format]),
   "GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_440/GetServerVersion/v0001"
    ""
    :get
    [:format])},
  "IGCVersion_730"
  {"GetServerVersionV1"
   (steam-request
    "https://api.steampowered.com/IGCVersion_730/GetServerVersion/v0001"
    ""
    :get
    [:format])},
  "ISteamMicroTxn"
  {"AdjustAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/AdjustAgreement/v0001"
    "steamid [uint64] - SteamID of user with the agreement\nagreementid [uint64] - ID of agreement\nappid [uint32] - AppID of game\nnextprocessdate [string] - Date for next process"
    :post
    [:steamid :agreementid :appid :nextprocessdate :format]),
   "QueryTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/QueryTxn/v0002"
    "appid [uint32] - AppID of game this transaction is for\norderid [uint64, optional] - 3rd party ID for transaction\ntransid [uint64, optional] - Steam transaction ID"
    :get
    [:appid :orderid :transid :format]),
   "CancelAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/CancelAgreement/v0001"
    "steamid [uint64] - SteamID of user with the agreement\nagreementid [uint64] - ID of agreement\nappid [uint32] - AppID of game"
    :post
    [:steamid :agreementid :appid :format]),
   "InitTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/InitTxn/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items in cart\nlanguage [string] - ISO 639-1 language code of description\ncurrency [string] - ISO 4217 currency code\nusersession [string, optional] - session where user will authorize the transaction. client or web (defaults to client)\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web\nitemid[0] [uint32] - 3rd party ID for item\nqty[0] [uint32] - Quantity of this item\namount[0] [int32] - Total cost (in cents) of item(s)\ndescription[0] [string] - Description of item\ncategory[0] [string, optional] - Optional category grouping for item\nassociated_bundle[0] [uint32, optional] - Optional bundleid of associated bundle\nbillingtype[0] [string, optional] - Optional recurring billing type\nstartdate[0] [string, optional] - Optional start date for recurring billing\nenddate[0] [string, optional] - Optional end date for recurring billing\nperiod[0] [string, optional] - Optional period for recurring billing\nfrequency[0] [uint32, optional] - Optional frequency for recurring billing\nrecurringamt[0] [string, optional] - Optional recurring billing amount\nbundlecount [uint32, optional] - Number of bundles in cart\nbundleid[0] [uint32, optional] - 3rd party ID of the bundle. This shares the same ID space as 3rd party items.\nbundle_qty[0] [uint32, optional] - Quantity of this bundle\nbundle_desc[0] [string, optional] - Description of bundle\nbundle_category[0] [string, optional] - Optional category grouping for bundle"
    :post
    [:orderid
     :steamid
     :appid
     :itemcount
     :language
     :currency
     :usersession
     :ipaddress
     [:indexed-array :itemid]
     [:indexed-array :qty]
     [:indexed-array :amount]
     [:indexed-array :description]
     [:indexed-array :category]
     [:indexed-array :associated_bundle]
     [:indexed-array :billingtype]
     [:indexed-array :startdate]
     [:indexed-array :enddate]
     [:indexed-array :period]
     [:indexed-array :frequency]
     [:indexed-array :recurringamt]
     :bundlecount
     [:indexed-array :bundleid]
     [:indexed-array :bundle_qty]
     [:indexed-array :bundle_desc]
     [:indexed-array :bundle_category]
     :format]),
   "GetReportV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetReport/v0003"
    "appid [uint32] - AppID of game this transaction is for\ntype [string] - Report type (GAMESALES, STEAMSTORE, SETTLEMENT)\ntime [string] - Beginning time to start report from (RFC 3339 UTC format)\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:appid :type :time :maxresults :format]),
   "GetReportV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetReport/v0001"
    "appid [uint32] - AppID of game this transaction is for\ntype [string] - Report type (GAMESALES, STEAMSTORE, SETTLEMENT)\ntime [string] - Beginning time to start report from (RFC 3339 UTC format)\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:appid :type :time :maxresults :format]),
   "GetReportV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetReport/v0002"
    "appid [uint32] - AppID of game this transaction is for\ntype [string] - Report type (GAMESALES, STEAMSTORE, SETTLEMENT)\ntime [string] - Beginning time to start report from (RFC 3339 UTC format)\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:appid :type :time :maxresults :format]),
   "FinalizeTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/FinalizeTxn/v0002"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format]),
   "InitTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/InitTxn/v0002"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items in cart\nlanguage [string] - ISO 639-1 language code of description\ncurrency [string] - ISO 4217 currency code\nusersession [string, optional] - session where user will authorize the transaction. client or web (defaults to client)\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web\nitemid[0] [uint32] - 3rd party ID for item\nqty[0] [uint32] - Quantity of this item\namount[0] [int32] - Total cost (in cents) of item(s)\ndescription[0] [string] - Description of item\ncategory[0] [string, optional] - Optional category grouping for item\nassociated_bundle[0] [uint32, optional] - Optional bundleid of associated bundle\nbillingtype[0] [string, optional] - Optional recurring billing type\nstartdate[0] [string, optional] - Optional start date for recurring billing\nenddate[0] [string, optional] - Optional end date for recurring billing\nperiod[0] [string, optional] - Optional period for recurring billing\nfrequency[0] [uint32, optional] - Optional frequency for recurring billing\nrecurringamt[0] [string, optional] - Optional recurring billing amount\nbundlecount [uint32, optional] - Number of bundles in cart\nbundleid[0] [uint32, optional] - 3rd party ID of the bundle. This shares the same ID space as 3rd party items.\nbundle_qty[0] [uint32, optional] - Quantity of this bundle\nbundle_desc[0] [string, optional] - Description of bundle\nbundle_category[0] [string, optional] - Optional category grouping for bundle"
    :post
    [:orderid
     :steamid
     :appid
     :itemcount
     :language
     :currency
     :usersession
     :ipaddress
     [:indexed-array :itemid]
     [:indexed-array :qty]
     [:indexed-array :amount]
     [:indexed-array :description]
     [:indexed-array :category]
     [:indexed-array :associated_bundle]
     [:indexed-array :billingtype]
     [:indexed-array :startdate]
     [:indexed-array :enddate]
     [:indexed-array :period]
     [:indexed-array :frequency]
     [:indexed-array :recurringamt]
     :bundlecount
     [:indexed-array :bundleid]
     [:indexed-array :bundle_qty]
     [:indexed-array :bundle_desc]
     [:indexed-array :bundle_category]
     :format]),
   "GetUserInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetUserInfo/v0001"
    "steamid [uint64, optional] - SteamID of user making purchase\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
    :get
    [:steamid :ipaddress :format]),
   "InitTxnV3"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/InitTxn/v0003"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game this transaction is for\nitemcount [uint32] - Number of items in cart\nlanguage [string] - ISO 639-1 language code of description\ncurrency [string] - ISO 4217 currency code\nusersession [string, optional] - session where user will authorize the transaction. client or web (defaults to client)\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web\nitemid[0] [uint32] - 3rd party ID for item\nqty[0] [uint32] - Quantity of this item\namount[0] [int32] - Total cost (in cents) of item(s)\ndescription[0] [string] - Description of item\ncategory[0] [string, optional] - Optional category grouping for item\nassociated_bundle[0] [uint32, optional] - Optional bundleid of associated bundle\nbillingtype[0] [string, optional] - Optional recurring billing type\nstartdate[0] [string, optional] - Optional start date for recurring billing\nenddate[0] [string, optional] - Optional end date for recurring billing\nperiod[0] [string, optional] - Optional period for recurring billing\nfrequency[0] [uint32, optional] - Optional frequency for recurring billing\nrecurringamt[0] [string, optional] - Optional recurring billing amount\nbundlecount [uint32, optional] - Number of bundles in cart\nbundleid[0] [uint32, optional] - 3rd party ID of the bundle. This shares the same ID space as 3rd party items.\nbundle_qty[0] [uint32, optional] - Quantity of this bundle\nbundle_desc[0] [string, optional] - Description of bundle\nbundle_category[0] [string, optional] - Optional category grouping for bundle"
    :post
    [:orderid
     :steamid
     :appid
     :itemcount
     :language
     :currency
     :usersession
     :ipaddress
     [:indexed-array :itemid]
     [:indexed-array :qty]
     [:indexed-array :amount]
     [:indexed-array :description]
     [:indexed-array :category]
     [:indexed-array :associated_bundle]
     [:indexed-array :billingtype]
     [:indexed-array :startdate]
     [:indexed-array :enddate]
     [:indexed-array :period]
     [:indexed-array :frequency]
     [:indexed-array :recurringamt]
     :bundlecount
     [:indexed-array :bundleid]
     [:indexed-array :bundle_qty]
     [:indexed-array :bundle_desc]
     [:indexed-array :bundle_category]
     :format]),
   "GetUserInfoV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetUserInfo/v0002"
    "steamid [uint64, optional] - SteamID of user making purchase\nipaddress [string, optional] - ip address of user in string format (xxx.xxx.xxx.xxx). Only required if usersession=web"
    :get
    [:steamid :ipaddress :format]),
   "FinalizeTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/FinalizeTxn/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format]),
   "RefundTxnV2"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/RefundTxn/v0002"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format]),
   "ProcessAgreementV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/ProcessAgreement/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nsteamid [uint64] - SteamID of user with the agreement\nagreementid [uint64] - ID of agreement\nappid [uint32] - AppID of game\namount [int32] - Total cost (in cents) to charge\ncurrency [string] - ISO 4217 currency code"
    :post
    [:orderid :steamid :agreementid :appid :amount :currency :format]),
   "GetUserAgreementInfoV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/GetUserAgreementInfo/v0001"
    "steamid [uint64] - SteamID of user making purchase\nappid [uint32] - AppID of game"
    :get
    [:steamid :appid :format]),
   "QueryTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/QueryTxn/v0001"
    "appid [uint32] - AppID of game this transaction is for\norderid [uint64, optional] - 3rd party ID for transaction\ntransid [uint64, optional] - Steam transaction ID"
    :get
    [:appid :orderid :transid :format]),
   "RefundTxnV1"
   (steam-request
    "https://api.steampowered.com/ISteamMicroTxn/RefundTxn/v0001"
    "orderid [uint64] - 3rd party ID for transaction\nappid [uint32] - AppID of game this transaction is for"
    :post
    [:orderid :appid :format])},
  "IEconItems_205790"
  {"GetPlayerItemsV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetPlayerItems/v0001"
    "steamid [uint64] - The Steam ID to fetch items for"
    :get
    [:steamid :format]),
   "GetSchemaV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetSchema/v0001"
    "language [string, optional] - The language to return the names in. Defaults to returning string keys."
    :get
    [:language :format]),
   "GetSchemaURLV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetSchemaURL/v0001"
    ""
    :get
    [:format]),
   "GetStoreMetaDataV1"
   (steam-request
    "https://api.steampowered.com/IEconItems_205790/GetStoreMetaData/v0001"
    "language [string, optional] - The language to results in."
    :get
    [:language :format])},
  "ISteamGameServerStats"
  {"GetGameServerPlayerStatsForGameV1"
   (steam-request
    "https://api.steampowered.com/ISteamGameServerStats/GetGameServerPlayerStatsForGame/v0001"
    "key [string] - access key\ngameid [uint64] - game id to get stats for, if not a mod, it's safe to use appid here\nappid [uint32] - appID of the game\nrangestart [string] - range start date/time (Format: YYYY-MM-DD HH:MM:SS, seattle local time\nrangeend [string] - range end date/time (Format: YYYY-MM-DD HH:MM:SS, seattle local time\nmaxresults [uint32, optional] - Max number of results to return (up to 1000)"
    :get
    [:key :gameid :appid :rangestart :rangeend :maxresults :format])},
  "ISteamPublishedItemVoting"
  {"ItemVoteSummaryV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemVoting/ItemVoteSummary/v0001"
    "steamid [uint64] - Steam ID of user\nappid [uint32] - appID of product\ncount [uint32] - Count of how many items we are querying\npublishedfileid[0] [uint64, optional] - The Published File ID who's vote details are required"
    :post
    [:steamid
     :appid
     :count
     [:indexed-array :publishedfileid]
     :format]),
   "UserVoteSummaryV1"
   (steam-request
    "https://api.steampowered.com/ISteamPublishedItemVoting/UserVoteSummary/v0001"
    "steamid [uint64] - Steam ID of user\ncount [uint32] - Count of how many items we are querying\npublishedfileid[0] [uint64, optional] - A Specific Published Item"
    :post
    [:steamid :count [:indexed-array :publishedfileid] :format])}})