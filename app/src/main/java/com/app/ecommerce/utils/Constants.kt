package com.app.ecommerce.utils

object Constants {

    const val DEFAULT_API_ERROR_MSG: String = "Oops! Something went wrong on our end. Our team has been notified and is working to resolve the issue as soon as possible. Please try again later, or contact support if the problem persists."

    // Shared Preference name and password key from API that must be 32 character long
    const val ENCRYPTED_SHARED_PREF_NAME = "app_shared_prefs_encrypted"
    const val HOW_TO_CLEAN_URL = "https://www.irs.gov/pub/irs-pdf/fw9.pdf"
    const val PER_PAGE_RECORDS = 20
    const val PER_PAGE_RECORDS_50 = 50

    // Display date formats
    const val DISPLAY_DATE_FORMAT = "MM/dd/yy"
    const val DISPLAY_DATE_FORMAT_WITH_DAY = "EE, MMM dd"
    const val DISPLAY_DATE_TIME_FORMAT = "MM/dd/yy '|' hh:mm a"
    const val DISPLAY_TIME_FORMAT = "hh:mm a"
    const val DISPLAY_DATE_FORMAT_WITH_MONTH_DAY = "MMM dd"
    const val DISPLAY_DATE_FORMAT_WITH_MONTH_DAY_YEAR = "MMM dd, yyyy"
    const val DISPLAY_DATE_TIME_FORMAT_WITH_MONTH_DAY_TIME = "MMM dd - hh:mm a"
    const val DISPLAY_DATE_FORMAT_NEW = "MM/dd/yyyy"

    // API date formats
    const val API_DATE_FORMAT = "yyyy-MM-dd"
    const val API_DATE_FORMAT_2 = "MM-dd-yyyy"
    const val API_DATE_FORMAT_SLASH = "yyyy/MM/dd"
    const val API_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss"
    const val API_DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm:ss"
//    const val API_DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm"
    const val API_DATE_TIME_MS_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSSSSS'Z'"

    // Network
    const val CONNECT_TIMEOUT = 60
    const val WRITE_TIMEOUT = 60
    const val READ_TIMEOUT = 90

    //response codes
    const val RESPONSE_CODE_UNAUTHORIZED = 401
    const val NETWORK_OTHER_ERROR_CODE = 0
    const val RESPONSE_CODE_400 = 400
    const val RESPONSE_CODE_404 = 404
    const val RESPONSE_CODE_406 = 406
    const val RESPONSE_CODE_444 = 444
    const val RESPONSE_CODE_409 = 409
    const val RESPONSE_CODE_434 = 434
    const val RESPONSE_CODE_449 = 449
    const val RESPONSE_CODE_429 = 429
    const val RESPONSE_CODE_422 = 422
    const val RESPONSE_CODE_423 = 423
    const val RESPONSE_CODE_403 = 403
    const val RESPONSE_CODE_425 = 425
    const val RESPONSE_CODE_426 = 426
    const val RESPONSE_CODE_200 = 200
    const val RESPONSE_CODE_201 = 201
    const val RESPONSE_CODE_202 = 202
    const val RESPONSE_CODE_203 = 203
    const val RESPONSE_CODE_302 = 302
    const val RESPONSE_CODE_500 = 500

    const val UNKNOWN_API_ERROR_MSG =
        "Oops! Something went wrong on our end. Our team has been notified and is working to resolve the issue as soon as possible. Please try again later, or contact support if the problem persists."
    const val NETWORK_ERROR_MSG = "Internet connection is not available."
    const val RESPONSE_MESSAGE_RESERVATION_DETAIL = "display_reservation_detail_error"

    const val GUEST_SCREEN_FROM = "guestScreenFrom"
    const val GUEST_SCREEN_FROM_GET_MY_KEYS = "getMyKeys"
    const val GUEST_SCREEN_FROM_RESERVATION = "reservation"
    const val GUEST_SCREEN_FROM_HOME = "home"

    // notification
    const val TAG_TITLE = "title"
    const val TAG_CREATED = "created"
    const val TAG_THREAD_SLUG = "where_to_redirect"
    const val TAG_MESSAGE = "message"
    const val TAG_NOTIFICATION_TYPE = "notification_type"
    const val TAG_PROPERTY_SLUG = "property_slug"

    // Menu slug
    const val MENU_GET_MY_KEY = "key-cafe-list"
    const val MENU_RESERVATIONS = "reservations"
    const val MENU_MANAGE_ACCESS = "manage-access"
    const val MENU_NEIGHBOURHOOD = "neighbourhood-list"
    const val MENU_INFLUENCERS = "influencers-list"
    const val BOOK_NOW = "explore"
    const val MENU_LEASING = "lease"
    const val MENU_LISTINGS = "listings"
    const val MENU_HOUSEKEEPING = "housekeeping"
    const val MENU_ONBOARDING = "onboarding"
    const val MENU_ECOMMERCE = "ecommerce"
    const val MENU_PRODUCTS = "ecommerce-list"
    const val MENU_HOSTING = "hosting"
    const val MENU_RESIDENT_PAYOUT = "resident-payout"
    const val MENU_HELP_CENTER = "app-cms-list"
    const val MENU_CALENDER = "calendar"
    const val MENU_AMBASSADOR = "ambassadors-list"
    const val MENU_RESOURCES = "resources-list"
    const val MENU_INSIGHTS = "insight-view"

    // Finalize your listing
    const val FINALIZE_LISTING_TURNO = "setup_turno_payment"
    const val FINALIZE_LISTING_KEYS = "drop_off_keys"
    const val FINALIZE_LISTING_GUEST_MANUAL = "complete_your_guest_manual"
    const val FINALIZE_LISTING_RESIDENT_STAY = "open_your_calender"
    const val FINALIZE_LISTING_VIDEO = "watch_video"

    const val RESIDENT_CHECKOUT_BATHROOM = "bathroom_checklist"
    const val RESIDENT_CHECKOUT_KITCHEN = "kitchen_checklist"
    const val RESIDENT_CHECKOUT_BEDROOM = "bedroom_checklist"
    const val RESIDENT_CHECKOUT_LIVING = "living_checklist"
    const val RESIDENT_CHECKOUT_GUIDELINE = "checkout_guidline"

    const val IS_REQUEST_FOR_LISTING = "listing_steps"
    const val IS_REQUEST_FOR_CHECKLIST = "checklist"

    const val IS_REQUEST_FOR_RESIDENT = "resident"
    const val IS_REQUEST_FOR_RESERVATION = "reservation"

    // Listings tab type
    const val TAB_ACTIVE = "active"
    const val TAB_INACTIVE = "inactive"

    // Resident payout tab type
    const val RESIDENT_PAY_OUT_TAB_COMPLETED = "1"
    const val RESIDENT_PAY_OUT_TAB_UPCOMING = "2"

    // Reservations tab type
    const val TAB_CURRENT_HOSTING = "currentHosting"
    const val TAB_UPCOMING = "upcoming"
    const val TAB_COMPLETED = "completed"
    const val TAB_CANCELLED = "cancelled"
    const val TAB_ALL = "all"
    const val TAB_CHECK_INS = "checkIns"
    const val TAB_CHECK_OUTS = "checkOuts"

    // Housekeeping tab type
    const val TAB_CALENDAR = "calendar"
    const val TAB_TODAY = "today"
    const val TAB_TOMORROW = "tomorrow"
    const val TAB_PAST = "past"

    // Add listings tab type
    const val TAB_INFO = "info"
    const val TAB_IMAGES = "images"
    const val TAB_AMENITIES = "amenities"
    const val TAB_PRICING = "pricing"
    const val TAB_RESIDENT_STAY = "resident_stay"
    const val TAB_SYNC = "sync"

    // Booking tab type
    const val TAB_LIVE_HERE = "live_here"
    const val TAB_STAY_HERE = "stay_here"

    //Explore tab type
    const val TAB_EXPLORE = "explore"
    const val TAB_YOUR_TRIPS = "your_trips"

    // LTR confirmation URL
    const val LTR_CONFIRMATION_URL = "https://calendly.com/orion-haus/discovery?"
    const val LATCH_APP_PACKAGE_NAME = "com.latch.android.latchapp"

    // stripe status
    const val STRIPE_SUCCESS = "2"
    const val STRIPE_FAILURE = "1"
    const val STRIPE_PENDING = "3"

    // stripe linked status
    const val STRIPE_TBNB_LINKED_NO_STATUS = 0
    const val STRIPE_TBNB_LINKED_IN_REVIEW = 1
    const val STRIPE_TBNB_LINKED_APPROVED = 2
    const val STRIPE_TBNB_LINKED_REJECTED = 3

    // Store onboarding steps
    const val ON_BOARDING_STEP_2 = 2
    const val ON_BOARDING_STEP_3 = 3
    const val ON_BOARDING_STEP_3_1 = 3.1
    const val ON_BOARDING_STEP_4 = 4

    //social name
    const val SOCIAL_FACEBOOK = "Facebook"
    const val SOCIAL_INSTAGRAM = "Instagram"
    const val SOCIAL_YOUTUBE = "YouTube"
    const val SOCIAL_TIKTOK = "Tiktok"
    const val SOCIAL_MOBILE = "Mobile"
    const val SOCIAL_EMAIL = "Email"

    const val THREAD_TYPE_GUEST = 1
    const val THREAD_TYPE_RESIDENT = 3
    const val THREAD_TYPE_STAFF = 2

    const val QUICK_REPLAY_CONFIRMATION_CODE = "[Confirmation Code]"
    const val QUICK_REPLAY_CHECK_IN = "[Check-In]"
    const val QUICK_REPLAY_CHECK_OUT = "[Check-Out]"
    const val QUICK_REPLAY_ADDRESS = "[Address]"
    const val QUICK_REPLAY_UNIT_NO = "[Unit No]"
    const val QUICK_REPLAY_GUEST_NAME = "[Guest Name]"
    const val QUICK_REPLAY_LISTING = "[Listing]"
    const val QUICK_REPLAY_WIFI_NAME = "[Wifi-Name]"
    const val QUICK_REPLAY_WIFI_PASSWORD = "[Wifi-Password]"
    const val QUICK_REPLAY_KEY_CAFE_CODE = "[Key-Cafe-Code]"
    const val QUICK_REPLAY_ARRIVAL_INSTRUCTION = "[Arrival-Instruction]"
    const val QUICK_REPLAY_PARKING_CODE = "[Parking Space]"
    const val QUICK_REPLAY_STAFF_NAME = "[Staff Name]"
    const val QUICK_REPLAY_RESIDENT_NAME = "[Resident Name]"
    const val QUICK_REPLAY_KEY_INSTRUCTION = "[Key-Instruction]"

    var CURRENT_THREAD_SLUG = ""

    const val W9_PERMISSION_NAME = "w9form"
    const val PAYOUT_PERMISSION_NAME = "resident-payout"
    const val CHAT_PERMISSION_NAME = "communication-message-module"
    const val STAFF_CONCIERGE_PERMISSION_NAME = "communication-message-contact-concierge"
    const val STAFF_VIEW_CONCIERGE_PERMISSION_NAME = "communication-message-view-concierge"
    const val QUICK_REPLAY_PERMISSION_PERMISSION_NAME =
        "communication-message-see-quick-reply-template"
    const val PERMISSION_KEY_CAFE_ACCESS_VIEW = "key-cafe-access-view"
    const val PERMISSION_RESERVATIONS_GUEST_PAID = "reservations-guest-paid"
    const val PERMISSION_RESERVATIONS_PROPERTY_EARNING = "reservations-property-earning"
    const val PERMISSION_RESERVATIONS_RESIDENT_EARNING = "reservations-resident-earning"
    const val PERMISSION_COMMUNICATION_MESSAGE_TECH_SUPPORT_VIEW = "communication-message-tech-support-view"
    const val PERMISSION_BYPASS_PARKING_EDIT= "reservations-bypass-parking-edit"
    const val PERMISSION_CHAT_GPT_ENABLE = "communication-message-chat-gpt-enable"


    const val BROADCAST_UPDATE_ON_LOGIN: String = "actionUpdateOnLogin"
    const val BROADCAST_UPDATE_ON_NEW_MESSAGE: String = "actionUpdateOnNewMessageReceived"
    const val BROADCAST_UPDATE_ON_RESIDENT_STAY_ADDED: String = "actionUpdateOnResidentStayChanged"
    const val BROADCAST_UPDATE_ON_CHECK_IN: String = "actionUpdateOnCheckIn"

    // Notification type
    const val NOTIFICATION_TYPE_MESSAGING = "1"
    const val NOTIFICATION_TYPE_RESERVATION = "2"
    const val NOTIFICATION_TYPE_LISTING = "3"
    const val NOTIFICATION_TYPE_CLEANING_DETAIL = "4"

    // To handle show popup once
    var FORCE_UPDATE_POPUP_SHOW = false
    var FORCE_UPDATE_MESSAGE = ""

    //update Request
    const val IS_REQUEST_FOR_LISTING_UPDATE = "listing_update"
    const val IS_REQUEST_FOR_PROFILE_UPDATE = "1"
    const val IS_REQUEST_FOR_PROFILE_IMAGE_DELETE = "2"

    //ReservationCard Progress TitleSlugs
    const val RESERVATION_CARD_CONFIRMATION_MESSAGE = "confirmation_message"
    const val RESERVATION_CARD_SIGNED_RENTAL_AGREEMENT = "signed_rental_agreement"
    const val RESERVATION_CARD_HOUSEKEEPING_COMPLETED = "housekeeping_completed"


    //publish
    const val STATUS_TEXT_SUCCESS = "Success"


    const val SET_CHANNEL_NAME_AIRBNB_GUEST_INQUIRY = "AirbnbGuestInquiry"

    const val TRANSACTION_TYPE_PAYOUT = "Payout"
    const val TRANSACTION_TYPE_RESERVATION = "Reservation Earning"
    const val TRANSACTIONITEM = "TransactionItem"
}