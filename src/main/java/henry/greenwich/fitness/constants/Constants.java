package henry.greenwich.fitness.constants;

public class Constants {
    // email subject reset password
    public static final String EMAIL_SUBJECT_RESET_PASSWORD = "Greenwich Fitness - Reset Password";

    // email subject active account
    public static final String EMAIL_SUBJECT_ACTIVE_ACCOUNT = "Greenwich Fitness - Active Account";

    // login url
    public static final String LOGIN_URL = "/greenwich-fitness/api/v1/login";

    // header meta information
    public static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";
    public static final String HEADER_X_TOTAL_PAGE = "X-Total-Page";
    public static final String HEADER_X_TOTAL_PAYMENT = "X-Total-Payment";

    // security constants
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    // path to upload image
    public static final String PATH_PUBLIC_IMAGE_STRING = "src/main/resources/static/upload/";

    // paypal client
    public static final String PAYPAL_CLIENT_ID = "AZO0eJ86SfloUJjP5C_B9uzZaTsiX_VJVtTijSGaEVr16WYGFpS-YdIEvH2oYDo1hk_mPJ79jlVcMT0K";

    // paypal secret
    public static final String PAYPAL_SECRET = "EMgFSH4yBhc74CRcSZ0yWvznsNYN6x-BZfsqPLE_rMSAoPVLxKYQFOT36R-9ghBV9HzUIfI1B14OsEk4";

    // number items per page
    public static final int NUMBER_ITEMS_PER_PAGE = 8;

    // newfeed table and columns
    public static final String NEW_FEED_TABLE = "new_feed";

    public static final String NEW_FEED_ID = "id";
    public static final String NEW_FEED_IMAGE = "image";
    public static final String NEW_FEED_ACHIEVEMENT = "achievement";
    public static final String NEW_FEED_ACHIEVEMENT_TIME = "achievement_time";
    public static final String NEW_FEED_CONTENT = "content";
    public static final String NEW_FEED_USER_PROFILE_ID = "user_profile_id";
    public static final String NEW_FEED_CREATED_DATE = "created_date";
    public static final String NEW_FEED_STATUS = "status";
    public static final String NEW_FEED_NUMBER_OF_LIKES = "number_of_likes";
    public static final String NEW_FEED_NUMBER_OF_DISLIKES = "number_of_dislikes";
    public static final String NEW_FEED_NUMBER_OF_COMMENTS = "number_of_comments";

    // newfeed's comment table and columns
    public static final String NEW_FEED_COMMENT_TABLE = "new_feed_comment";

    public static final String NEW_FEED_COMMENT_ID = "id";
    public static final String NEW_FEED_COMMENT_CONTENT = "new_feed_comment_content";
    public static final String NEW_FEED_COMMENT_CREATED_DATE = "new_feedback_comment_created_date";
    public static final String NEW_FEED_COMMENT_STATUS = "new_feedback_comment_status";
    public static final String NEW_FEED_COMMENT_USER_PROFILE_ID = "user_profile_id";
    public static final String NEW_FEED_COMMENT_NEW_FEED_ID = "new_feed_id";
    public static final String NEW_FEED_COMMENT_NUMBER_OF_LIKES = "number_of_likes";
    public static final String NEW_FEED_COMMENT_NUMBER_OF_DISLIKES = "number_of_dislikes";
    public static final String NEW_FEED_COMMENT_NUMBER_OF_REPLIES = "number_of_replies";

    // newfeed's comment's reaction table and columns
    public static final String NEW_FEED_COMMENT_REACTION_TABLE = "new_feed_comment_reaction";

    public static final String NEW_FEED_COMMENT_REACTION_ID = "id";
    public static final String NEW_FEED_COMMENT_REACTION_REACTION = "reaction";
    public static final String NEW_FEED_COMMENT_REACTION_NEW_FEED_COMMENT_ID = "new_feed_comment_id";
    public static final String NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID = "user_profile_id";

    // newfeed's reaction table and columns
    public static final String NEW_FEED_REACTION_TABLE = "new_feed_reaction";

    public static final String NEW_FEED_REACTION_ID = "id";
    public static final String NEW_FEED_REACTION_REACTION = "reaction";
    public static final String NEW_FEED_REACTION_NEW_FEED_ID = "new_feed_id";
    public static final String NEW_FEED_REACTION_USER_PROFILE_ID = "user_profile_id";

    // reply on newfeed's comment table and columns
    public static final String REPLY_ON_NEW_FEED_COMMENT_TABLE = "reply_on_new_feed_comment";

    public static final String REPLY_ON_NEW_FEED_COMMENT_ID = "id";
    public static final String REPLY_ON_NEW_FEED_COMMENT_CONTENT = "reply_on_new_feed_comment_content";
    public static final String REPLY_ON_NEW_FEED_COMMENT_CREATED_DATE = "reply_on_new_feed_comment_created_date";
    public static final String REPLY_ON_NEW_FEED_COMMENT_STATUS = "reply_on_new_feed_comment_status";
    public static final String REPLY_ON_NEW_FEED_COMMENT_NEW_FEED_COMMENT_ID = "new_feed_comment_id";
    public static final String REPLY_ON_NEW_FEED_COMMENT_USER_PROFILE_ID = "user_profile_id";
    public static final String REPLY_ON_NEW_FEED_COMMENT_NUMBER_OF_LIKES = "number_of_likes";
    public static final String REPLY_ON_NEW_FEED_COMMENT_NUMBER_OF_DISLIKES = "number_of_dislikes";

    // reactions of reply on newfeed's comment table and columns
    public static final String REPLY_ON_NEW_FEED_COMMENT_REACTION_TABLE = "reply_on_new_feed_comment_reaction";

    public static final String REPLY_ON_NEW_FEED_COMMENT_REACTION_ID = "id";
    public static final String REPLY_ON_NEW_FEED_COMMENT_REACTION_REACTION = "reaction";
    public static final String REPLY_ON_NEW_FEED_COMMENT_REACTION_USER_PROFILE_ID = "user_profile_id";
    public static final String REPLY_ON_NEW_FEED_COMMENT_REACTION_REPLY_ON_NEW_FEED_COMMENT_ID = "reply_on_new_feed_comment_id";

    // coach's table and columns
    public static final String COACH_TABLE = "coach";

    public static final String COACH_ID = "id";
    public static final String COACH_ABOUT = "about";
    public static final String COACH_USER_PROFILE_ID = "user_profile_id";
    public static final String COACH_STATUS = "status";
    public static final String COACH_RATING_AVERAGE = "rating_average";
    public static final String COACH_NUMBER_OF_MEMBERSHIPS = "number_of_memberships";

    // user profile's table and columns
    public static final String USER_PROFILE_TABLE = "user_profile";

    public static final String USER_PROFILE_ID = "id";
    public static final String USER_PROFILE_FULL_NAME = "full_name";
    public static final String USER_PROFILE_AVATAR = "avatar";
    public static final String USER_PROFILE_ACCEPT_TERMS_OF_SERVICE = "accept_terms_of_service";
    public static final String USER_PROFILE_STATUS = "status";
    public static final String USER_PROFILE_POINT = "point";

    // coach's feedback's table and columns
    public static final String COACH_FEEDBACK_TABLE = "coach_feedback";

    public static final String COACH_FEEDBACK_ID = "id";
    public static final String COACH_FEEDBACK_CONTENT = "coach_feedback_content";
    public static final String COACH_FEEDBACK_CREATED_DATE = "coach_feedback_created_date";
    public static final String COACH_FEEDBACK_STATUS = "coach_feedback_status";
    public static final String COACH_FEEDBACK_USER_PROFILE_ID = "user_profile_id";
    public static final String COACH_FEEDBACK_COACH_ID = "coach_id";
    public static final String COACH_FEEDBACK_NUMBER_OF_LIKES = "number_of_likes";
    public static final String COACH_FEEDBACK_NUMBER_OF_DISLIKES = "number_of_dislikes";
    public static final String COACH_FEEDBACK_NUMBER_OF_REPLIES = "number_of_replies";

    // coach's feedback's reaction table and columns
    public static final String COACH_FEEDBACK_REACTION_TABLE = "coach_feedback_reaction";

    public static final String COACH_FEEDBACK_REACTION_ID = "id";
    public static final String COACH_FEEDBACK_REACTION_REACTION = "reaction";
    public static final String COACH_FEEDBACK_REACTION_COACH_FEEDBACK_ID = "coach_feedback_id";
    public static final String COACH_FEEDBACK_REACTION_USER_PROFILE_ID = "user_profile_id";

    // coach's rate table and columns
    public static final String COACH_RATE_TABLE = "coach_rate";

    public static final String COACH_RATE_ID = "id";
    public static final String COACH_RATE_RATE = "rate";
    public static final String COACH_RATE_COACH_ID = "coach_id";
    public static final String COACH_RATE_USER_PROFILE_ID = "user_profile_id";

    // reply on coach's feedback table and columns
    public static final String REPLY_ON_COACH_FEEDBACK_TABLE = "reply_on_coach_feedback";

    public static final String REPLY_ON_COACH_FEEDBACK_ID = "id";
    public static final String REPLY_ON_COACH_FEEDBACK_CONTENT = "reply_on_coach_feedback_content";
    public static final String REPLY_ON_COACH_FEEDBACK_CREATED_DATE = "reply_on_coach_feedback_created_date";
    public static final String REPLY_ON_COACH_FEEDBACK_STATUS = "reply_on_coach_feedback_status";
    public static final String REPLY_ON_COACH_FEEDBACK_COACH_FEED_BACK_ID = "coach_feedback_id";
    public static final String REPLY_ON_COACH_FEEDBACK_USER_PROFILE_ID = "user_profile_id";
    public static final String REPLY_ON_COACH_FEEDBACK_NUMBER_OF_LIKES = "number_of_likes";
    public static final String REPLY_ON_COACH_FEEDBACK_NUMBER_OF_DISLIKES = "number_of_dislikes";

    // reply on coach's feedback's reaction and columns
    public static final String REPLY_ON_COACH_FEEDBACK_REACTION_TABLE = "reply_on_coach_feedback_reaction";

    public static final String REPLY_ON_COACH_FEEDBACK_REACTION_ID = "id";
    public static final String REPLY_ON_COACH_FEEDBACK_REACTION_REACTION = "reaction";
    public static final String REPLY_ON_COACH_FEEDBACK_REACTION_REPLY_ON_COACH_FEEDBACK_ID = "reply_on_coach_feedback_id";
    public static final String REPLY_ON_COACH_FEEDBACK_REACTION_USER_PROFILE_ID = "user_profile_id";

    // membership's table and columns
    public static final String MEMBERSHIP_TABLE = "membership";

    public static final String MEMBERSHIP_COACH_ID = "coach_id";
    public static final String MEMBERSHIP_USER_PROFILE_ID = "user_profile_id";
    public static final String MEMBERSHIP_ID = "id";
    public static final String MEMBERSHIP_STATUS = "status";
    public static final String MEMBERSHIP_START_DATE = "start_date";

    // gallery's table and columns
    public static final String GALLERY_TABLE = "gallery";

    public static final String GALLERY_ID = "id";
    public static final String GALLERY_IMAGE = "image";
    public static final String GALLERY_TITLE = "title";
    public static final String GALLERY_CREATED_DATE = "created_date";
    public static final String GALLERY_MODIFIED_DATE = "modified_date";
    public static final String GALLERY_STATUS = "status";
    public static final String GALLERY_THUMBNAIL = "thumbnail";
    public static final String GALLERY_USER_PROFILE_ID = "user_profile_id";

    // music's table and columns
    public static final String MUSIC_TABLE = "music";

    public static final String MUSIC_ID = "id";
    public static final String MUSIC_NAME = "music_name";
    public static final String MUSIC_LINK = "music_link";
    public static final String MUSIC_AUTHOR = "music_author";
    public static final String MUSIC_CREATED_DATE = "music_created_date";
    public static final String MUSIC_MODIFIED_DATE = "music_modified_date";
    public static final String MUSIC_STATUS = "status";
    public static final String MUSIC_IMAGE = "music_image";

    // coach membership notification's table and columns
    public static final String COACH_MEMBERSHIP_NOTIFICATION_TABLE = "coach_membership_notification";

    public static final String COACH_MEMBERSHIP_NOTIFICATION_ID = "id";
    public static final String COACH_MEMBERSHIP_NOTIFICATION_CONTENT = "content";
    public static final String COACH_MEMBERSHIP_NOTIFICATION_USER_PROFILE_ID = "user_profile_id";
    public static final String COACH_MEMBERSHIP_NOTIFICATION_COACH_ID = "coach_id";
    public static final String COACH_MEMBERSHIP_NOTIFICATION_STATUS = "status";
    public static final String COACH_MEMBERSHIP_NOTIFICATION_CREATED_DATE = "created_date";

    // notification table and columns
    public static final String NOTIFICATION_TABLE = "notification";

    public static final String NOTIFICATION_ID = "id";
    public static final String NOTIFICATION_CONTENT = "content";
    public static final String NOTIFICATION_USER_PROFILE_ID = "user_profile_id";
    public static final String NOTIFICATION_STATUS = "status";
    public static final String NOTIFICATION_CREATED_DATE = "created_date";

    // coach payment table and columns
    public static final String COACH_PAYMENT_TABLE = "coach_payment";

    public static final String COACH_PAYMENT_ID = "id";
    public static final String COACH_PAYMENT_PAYMENT_ID = "payment_id";
    public static final String COACH_PAYMENT_PAYER_ID = "payer_id";
    public static final String COACH_PAYMENT_TOKEN = "token";
    public static final String COACH_PAYMENT_MEMBERSHIP_ID = "membership_id";
    public static final String COACH_PAYMENT_CREATED_DATE = "created_date";
    public static final String COACH_PAYMENT_SUM = "sum";

    // product payment table and columns
    public static final String PRODUCT_PAYMENT_TABLE = "product_payment";

    public static final String PRODUCT_PAYMENT_ID = "id";
    public static final String PRODUCT_PAYMENT_PAYMENT_ID = "payment_id";
    public static final String PRODUCT_PAYMENT_PAYER_ID = "payer_id";
    public static final String PRODUCT_PAYMENT_TOKEN = "token";
    public static final String PRODUCT_PAYMENT_PRODUCT_ORDER_PRODUCT_ORDER_ID = "product_order_product_order_id";
    public static final String PRODUCT_PAYMENT_CREATED_DATE = "created_date";

    // post category table and columns
    public static final String POST_CATEGORY_TABLE = "post_category";

    public static final String POST_CATEGORY_ID = "id";
    public static final String POST_CATEGORY_NAME = "name";
    public static final String POST_CATEGORY_CREATED_DATE = "created_date";
    public static final String POST_CATEGORY_MODIFIED_DATE = "modified_date";
    public static final String POST_CATEGORY_STATUS = "status";
    public static final String POST_CATEGORY_META_TITLE = "meta_title";
    public static final String POST_CATEGORY_PARENT_ID = "parent_id";
    public static final String POST_CATEGORY_DISPLAY_ORDER = "display_order";
    public static final String POST_CATEGORY_SEO_TITLE = "seo_title";
    public static final String POST_CATEGORY_META_KEYWORDS = "meta_keywords";
    public static final String POST_CATEGORY_META_DESCRIPTION = "meta_description";
    public static final String POST_CATEGORY_SHOW_ON_HOME = "show_on_home";
    public static final String POST_CATEGORY_IMAGE = "image";
    public static final String POST_CATEGORY_MORE_IMAGE = "more_image";

    // post tables and columns
    public static final String POST_TABLE = "post";

    public static final String POST_ID = "id";
    public static final String POST_CONTENT = "content";
    public static final String POST_DESCRIPTION = "description";
    public static final String POST_META_KEYWORDS = "meta_keywords";
    public static final String POST_TITLE = "title";
    public static final String POST_IMAGE = "image";
    public static final String POST_CREATED_DATE = "created_date";
    public static final String POST_MODIFIED_DATE = "modified_date";
    public static final String POST_STATUS = "status";
    public static final String POST_POST_CATEGORY_ID = "post_category_id";
    public static final String POST_META_TITLE = "meta_title";
    public static final String POST_META_DESCRIPTION = "meta_description";
    public static final String POST_USER_PROFILE_ID = "user_profile_id";
    public static final String POST_VIEW_COUNT = "view_count";
    public static final String POST_TOP_HOT = "top_hot";
    public static final String POST_NEW = "new";

    // post's comment's reaction table and columns
    public static final String POST_COMMENT_REACTION_TABLE = "post_comment_reaction";

    public static final String POST_COMMENT_REACTION_ID = "id";
    public static final String POST_COMMENT_REACTION_REACTION = "reaction";
    public static final String POST_COMMENT_REACTION_POST_COMMENT_ID = "post_comment_id";
    public static final String POST_COMMENT_REACTION_USER_PROFILE_ID = "user_profile_id";

    // post's rate table and columns
    public static final String POST_RATE_TABLE = "post_rate";

    public static final String POST_RATE_ID = "id";
    public static final String POST_RATE_RATE = "rate";
    public static final String POST_RATE_POST_ID = "post_id";
    public static final String POST_RATE_USER_PROFILE_ID = "user_profile_id";

    // post's slide table and columns
    public static final String POST_SLIDE_TABLE = "post_slide";

    public static final String POST_SLIDE_ID = "id";
    public static final String POST_SLIDE_IMAGE = "post_slide_image";
    public static final String POST_SLIDE_LINK = "post_slide_link";
    public static final String POST_SLIDE_DESCRIPTION = "post_slide_description";
    public static final String POST_SLIDE_CREATED_DATE = "post_slide_created_date";
    public static final String POST_SLIDE_MODIFIED_DATE = "post_slide_modified_date";
    public static final String POST_SLIDE_STATUS = "post_slide_status";
    public static final String POST_SLIDE_TITLE = "post_slide_title";

    // tag's table and columns
    public static final String TAG_TABLE = "tag";

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_STATUS = "status";

    // post's tag table and columns
    public static final String POST_TAG_TABLE = "post_tag";

    public static final String POST_TAG_ID = "id";
    public static final String POST_TAG_TAG_ID = "tag_id";
    public static final String POST_TAG_POST_ID = "post_id";

    // reply on post's comment table and columns
    public static final String REPLY_ON_POST_COMMENT_TABLE = "reply_on_post_comment";

    public static final String REPLY_ON_POST_COMMENT_CONTENT = "reply_on_post_comment_content";
    public static final String REPLY_ON_POST_COMMENT_ID = "id";
    public static final String REPLY_ON_POST_COMMENT_POST_COMMENT_ID = "post_comment_id";
    public static final String REPLY_ON_POST_COMMENT_USER_PROFILE_ID = "user_profile_id";
    public static final String REPLY_ON_POST_COMMENT_STATUS = "status";
    public static final String REPLY_ON_POST_COMMENT_CREATED_DATE = "reply_on_post_comment_created_date";
    public static final String REPLY_ON_POST_COMMENT_NUMBER_OF_LIKES = "number_of_likes";
    public static final String REPLY_ON_POST_COMMENT_NUMBER_OF_DISLIKES = "number_of_dislikes";

    // reply on post's comment reaction table and columns
    public static final String REPLY_ON_POST_COMMENT_REACTION_TABLE = "reply_on_post_comment_reaction";

    public static final String REPLY_ON_POST_COMMENT_REACTION_ID = "id";
    public static final String REPLY_ON_POST_COMMENT_REACTION_REACTION = "reaction";
    public static final String REPLY_ON_POST_COMMENT_REACTION_REPLY_ON_POST_COMMENT_ID = "reply_on_post_comment_id";
    public static final String REPLY_ON_POST_COMMENT_REACTION_USER_PROFILE_ID = "user_profile_id";

    // post's comment table and columns
    public static final String POST_COMMENT_TABLE = "post_comment";

    public static final String POST_COMMENT_CONTENT = "post_comment_content";
    public static final String POST_COMMENT_ID = "id";
    public static final String POST_COMMENT_POST_ID = "post_id";
    public static final String POST_COMMENT_USER_PROFILE_ID = "user_profile_id";
    public static final String POST_COMMENT_STATUS = "status";
    public static final String POST_COMMENT_CREATED_DATE = "post_comment_created_date";
    public static final String POST_COMMENT_NUMBER_OF_LIKES = "number_of_likes";
    public static final String POST_COMMENT_NUMBER_OF_DISLIKES = "number_of_dislikes";
    public static final String POST_COMMENT_NUMBER_OF_REPLIES = "number_of_replies";

    // product's category's table and columns
    public static final String PRODUCT_CATEGORY_TABLE = "product_category";

    public static final String PRODUCT_CATEGORY_ID = "id";
    public static final String PRODUCT_CATEGORY_NAME = "product_category_name";
    public static final String PRODUCT_CATEGORY_META_TITLE = "product_category_meta_title";
    public static final String PRODUCT_CATEGORY_PARENT_ID = "product_category_parent_id";
    public static final String PRODUCT_CATEGORY_DISPLAY_ORDER = "product_category_display_order";
    public static final String PRODUCT_CATEGORY_SEO_TITLE = "product_category_seo_title";
    public static final String PRODUCT_CATEGORY_CREATED_DATE = "product_category_created_date";
    public static final String PRODUCT_CATEGORY_MODIFIED_DATE = "product_category_modified_date";
    public static final String PRODUCT_CATEGORY_META_KEYWORDS = "product_category_meta_keywords";
    public static final String PRODUCT_CATEGORY_META_DESCRIPTION = "product_category_meta_desccription";
    public static final String PRODUCT_CATEGORY_STATUS = "product_category_status";
    public static final String PRODUCT_CATEGORY_SHOW_ON_HOME = "product_category_show_on_home";
    public static final String PRODUCT_CATEGORY_IMAGE = "product_category_image";
    public static final String PRODUCT_CATEGORY_MORE_IMAGE = "product_category_more_image";

    // product's table and columns
    public static final String PRODUCT_TABLE = "product";

    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_META_TITLE = "product_meta_title";
    public static final String PRODUCT_CODE = "product_code";
    public static final String PRODUCT_IMAGE = "product_image";
    public static final String PRODUCT_MORE_IMAGE = "product_more_image";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_PROMOTION_PRICE = "product_promotion_price";
    public static final String PRODUCT_INCLUDE_VAT = "product_include_vat";
    public static final String PRODUCT_QUANTITY = "product_quantity";
    public static final String PRODUCT_WARANTY = "product_waranty";
    public static final String PRODUCT_CREATED_DATE = "product_created_date";
    public static final String PRODUCT_MODIFIED_DATE = "product_modified_date";
    public static final String PRODUCT_META_KEYWORDS = "product_meta_keywords";
    public static final String PRODUCT_META_DESCRIPTION = "product_meta_description";
    public static final String PRODUCT_TOP_HOT = "product_top_hot";
    public static final String PRODUCT_NEW = "product_new";
    public static final String PRODUCT_STATUS = "product_status";
    public static final String PRODUCT_VIEW_COUNT = "product_view_count";
    public static final String PRODUCT_PRODUCT_CATEGORY_ID = "product_category_id";

    // product's feedback table and columns
    public static final String PRODUCT_FEEDBACK_TABLE = "product_feedback";

    public static final String PRODUCT_FEEDBACK_ID = "id";
    public static final String PRODUCT_FEEDBACK_CONTENT = "feedback_content";
    public static final String PRODUCT_FEEDBACK_CREATED_DATE = "feedback_create_date";
    public static final String PRODUCT_FEEDBACK_STATUS = "feedback_status";
    public static final String PRODUCT_FEEDBACK_USER_PROFILE_ID = "user_profile_id";
    public static final String PRODUCT_FEEDBACK_PRODUCT_ID = "product_id";
    public static final String PRODUCT_FEEDBACK_NUMBER_OF_LIKES = "number_of_likes";
    public static final String PRODUCT_FEEDBACK_NUMBER_OF_DISLIKES = "number_of_dislikes";
    public static final String PRODUCT_FEEDBACK_NUMBER_OF_REPLIES = "number_of_replies";

    // product's feedback's reaction table and columns
    public static final String PRODUCT_FEEDBACK_REACTION_TABLE = "product_feedback_reaction";

    public static final String PRODUCT_FEEDBACK_REACTION_ID = "id";
    public static final String PRODUCT_FEEDBACK_REACTION_PRODUCT_FEEDBACK_ID = "product_feedback_id";
    public static final String PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID = "user_profile_id";
    public static final String PRODUCT_FEEDBACK_REACTION_REACTION = "reaction";

    // product's order table and columns
    public static final String PRODUCT_ORDER_TABLE = "product_order";

    public static final String PRODUCT_ORDER_PRODUCT_ORDER_ID = "product_order_id";
    public static final String PRODUCT_ORDER_PRODUCT_ORDER_STATUS = "product_order_status";
    public static final String PRODUCT_ORDER_PRODUCT_ORDER_DATE = "product_order_date";
    public static final String PRODUCT_ORDER_USER_PROFILE_ID = "user_profile_id";

    // product's order's detail and columns
    public static final String PRODUCT_ORDER_DETAIL_TABLE = "product_order_detail";

    public static final String PRODUCT_ORDER_DETAIL_ID = "product_order_detail_id";
    public static final String PRODUCT_ORDER_DETAIL_QUANTITY = "quantity";
    public static final String PRODUCT_ORDER_DETAIL_PRODUCT_ID = "product_id";
    public static final String PRODUCT_ORDER_DETAIL_PRODUCT_ORDER_ID = "product_order_product_order_id";

    // product's rate table and columns
    public static final String PRODUCT_RATE_TABLE = "product_rate";

    public static final String PRODUCT_RATE_RATE_ID = "rate_id";
    public static final String PRODUCT_RATE_RATE = "rate";
    public static final String PRODUCT_RATE_PRODUCT_ID = "product_id";
    public static final String PRODUCT_RATE_USER_PROFILE_ID = "user_profile_id";

    // product's slide table and columns
    public static final String PRODUCT_SLIDE_TABLE = "product_slide";

    public static final String PRODUCT_SLIDE_ID = "id";
    public static final String PRODUCT_SLIDE_IMAGE = "product_slide_image";
    public static final String PRODUCT_SLIDE_LINK = "product_slide_link";
    public static final String PRODUCT_SLIDE_DESCRIPTION = "product_slide_description";
    public static final String PRODUCT_SLIDE_CREATED_DATE = "product_slide_created_date";
    public static final String PRODUCT_SLIDE_MODIFIED_DATE = "product_slide_modified_date";
    public static final String PRODUCT_SLIDE_STATUS = "product_slide_status";
    public static final String PRODUCT_SLIDE_TITLE = "product_slide_title";

    // reply on product's feedback table and columns
    public static final String REPLY_ON_PRODUCT_FEEDBACK_TABLE = "reply_on_product_feedback";

    public static final String REPLY_ON_PRODUCT_FEEDBACK_ID = "id";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_CONTENT = "reply_on_product_feedback_content";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_PRODUCT_FEEDBACK_ID = "product_feedback_id";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_USER_PROFILE_ID = "user_profile_id";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_STATUS = "reply_on_product_feedback_status";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_CREATED_DATE = "reply_on_product_feedback_created_date";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_NUMBER_OF_LIKES = "number_of_likes";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_NUMBER_OF_DISLIKES = "number_of_dislikes";

    // reply on product feedback reaction table and columns
    public static final String REPLY_ON_PRODUCT_FEEDBACK_REACTION_TABLE = "reply_on_product_feedback_reaction";

    public static final String REPLY_ON_PRODUCT_FEEDBACK_REACTION_ID = "id";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_REACTION_REPLY_ON_PRODUCT_FEEDBACK_ID = "reply_on_product_feedback_id";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_REACTION_USER_PROFILE_ID = "user_profile_id";
    public static final String REPLY_ON_PRODUCT_FEEDBACK_REACTION_REACTION = "reaction";

    // training table and columns
    public static final String TRAINING_TABLE = "training";

    public static final String TRAINING_ID = "id";
    public static final String TRAINING_COACH_ID = "coach_id";
    public static final String TRAINING_USER_PROFILE_ID = "user_profile_id";
    public static final String TRAINING_DATE = "training_date";
    public static final String TRAINING_NAME = "name";
    public static final String TRAINING_NUMBER_OF_REPS = "number_of_reps";
    public static final String TRAINING_LOG = "log";
    public static final String TRAINING_CURRENT_HEALTH = "current_health";
    public static final String TRAINING_STATUS = "status";

    // achievement table and columns
    public static final String ACHIEVEMENT_TABLE = "achievement";

    public static final String ACHIEVEMENT_TITLE = "achievement_title";
    public static final String ACHIEVEMENT_TIME = "achievement_time";
    public static final String ACHIEVEMENT_USER_PROFILE_ID = "user_profile_id";
    public static final String ACHIEVEMENT_ID = "id";
    public static final String ACHIEVEMENT_NUMBER_OF_REPS = "number_of_reps";
    public static final String ACHIEVEMENT_LOG = "log";
    public static final String ACHIEVEMENT_CURRENT_HEALTH = "current_health";

    // body index table and columns
    public static final String BODY_INDEX_TABLE = "body_index";

    public static final String BODY_INDEX_WEIGHT = "body_index_weight";
    public static final String BODY_INDEX_HEIGHT = "body_index_height";
    public static final String BODY_INDEX_DATE = "body_index_date";
    public static final String BODY_INDEX_USER_PROFILE_ID = "user_profile_id";
    public static final String BODY_INDEX_ID = "body_index_id";

    // user's account table and columns
    public static final String USER_ACCOUNT_TABLE = "user_account";

    public static final String USER_ACCOUNT_USER_NAME = "user_name";
    public static final String USER_ACCOUNT_PASSWORD = "password";
    public static final String USER_ACCOUNT_USER_ACCOUNT_STATUS_ID = "user_account_status_id";
    public static final String USER_ACCOUNT_PASSWORD_REMINDER_TOKEN = "password_reminder_token";
    public static final String USER_ACCOUNT_PASSWORD_REMINDER_EXPIRED = "password_reminder_expired";
    public static final String USER_ACCOUNT_EMAIL_CONFIRMATION_TOKEN = "email_confirmation_token";
    public static final String USER_ACCOUNT_REGISTRATION_TIME = "registration_time";
    public static final String USER_ACCOUNT_USER_PROFILE_ID = "user_profile_id";

    // about table and columns
    public static final String ABOUT_TABLE = "about";

    public static final String ABOUT_ID = "id";
    public static final String ABOUT_NAME = "about_name";
    public static final String ABOUT_CONTENT = "about_content";
    public static final String ABOUT_META_TITLE = "about_meta_title";
    public static final String ABOUT_META_KEYWORDS = "about_meta_keywords";
    public static final String ABOUT_META_DESCRIPTION = "about_meta_description";
    public static final String ABOUT_CREATED_DATE = "about_created_date";
    public static final String ABOUT_STATUS = "about_status";

    // contact table and columns
    public static final String CONTACT_TABLE = "contact";

    public static final String CONTACT_ID = "id";
    public static final String CONTACT_NAME = "contact_name";
    public static final String CONTACT_CONTENT = "contact_content";
    public static final String CONTACT_META_TITLE = "contact_meta_title";
    public static final String CONTACT_META_KEYWORDS = "contact_meta_keywords";
    public static final String CONTACT_META_DESCRIPTION = "contact_meta_description";
    public static final String CONTACT_CREATED_DATE = "contact_created_date";
    public static final String CONTACT_STATUS = "contact_status";

    // privacy policy table and columns
    public static final String PRIVACY_POLICY_TABLE = "privacy_policy";

    public static final String PRIVACY_POLICY_ID = "id";
    public static final String PRIVACY_POLICY_NAME = "privacy_policy_name";
    public static final String PRIVACY_POLICY_CONTENT = "privacy_policy_content";
    public static final String PRIVACY_POLICY_META_TITLE = "privacy_policy_meta_title";
    public static final String PRIVACY_POLICY_META_KEYWORDS = "privacy_policy_meta_keywords";
    public static final String PRIVACY_POLICY_META_DESCRIPTION = "privacy_policy_meta_description";
    public static final String PRIVACY_POLICY_CREATED_DATE = "privacy_policy_created_date";
    public static final String PRIVACY_POLICY_STATUS = "privacy_policy_status";

    // facebook account table and columns
    public static final String FACEBOOK_ACCOUNT_TABLE = "facebook_account";

    public static final String FACEBOOK_ACCOUNT_FACEBOOK_ID = "facebook_id";
    public static final String FACEBOOK_ACCOUNT_USER_PROFILE_ID = "user_profile_id";

    // google account table and columns
    public static final String GOOGLE_ACCOUNT_TABLE = "google_account";

    public static final String GOOGLE_ACCOUNT_GOOGLE_ID = "google_id";
    public static final String GOOGLE_ACCOUNT_USER_PROFILE_ID = "user_profile_id";

    // role table and columns
    public static final String ROLE_TABLE = "role";

    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "name";
    public static final String ROLE_STATUS = "status";

    // user account status table and columns
    public static final String USER_ACCOUNT_STATUS_TABLE = "user_account_status";

    public static final String USER_ACCOUNT_STATUS_ID = "id";
    public static final String USER_ACCOUNT_STATUS_NAME = "name";

    // user role table and columns
    public static final String USER_ROLE_TABLE = "user_role";

    public static final String USER_ROLE_ROLE_ID = "role_id";
    public static final String USER_ROLE_USER_PROFILE_ID = "user_profile_id";

    // gift type table and columns
    public static final String GIFT_TYPE_TABLE = "gift_type";
    public static final String GIFT_TYPE_ID = "id";
    public static final String GIFT_TYPE_NAME = "name";

    // gift table and columns
    public static final String GIFT_TABLE = "gift";
    public static final String GIFT_ID = "id";
    public static final String GIFT_NAME = "name";
    public static final String GIFT_POINT = "point";
    public static final String GIFT_GIFT_TYPE_ID = "gift_type_id";
    public static final String GIFT_IMAGE = "image";

    // user gift table and columns
    public static final String USER_GIFT_TABLE = "user_gift";
    public static final String USER_GIFT_ID = "id";
    public static final String USER_GIFT_STATUS = "status";
    public static final String USER_GIFT_GIFT_ID = "gift_id";
    public static final String USER_GIFT_USER_PROFILE_ID = "user_profile_id";

    // chat room table and columns
    public static final String CHAT_ROOM_TABLE = "chat_room";
    public static final String CHAT_ROOM_ID = "id";
    public static final String CHAT_ROOM_NAME = "name";
    public static final String CHAT_ROOM_TYPE = "type";

    // participant table and columns
    public static final String PARTICIPANT_TABLE = "participant";
    public static final String PARTICIPANT_ID = "id";
    public static final String PARTICIPANT_USER_PROFILE_ID = "user_profile_id";
    public static final String PARTICIPANT_COACH_ID = "coach_id";
    public static final String PARTICIPANT_CHAT_ROOM_ID = "chat_room_id";

    // chat message table and columns
    public static final String CHAT_MESSAGE_TABLE = "chat_message";
    public static final String CHAT_MESSAGE_ID = "id";
    public static final String CHAT_MESSAGE_USER_PROFILE_ID = "user_profile_id";
    public static final String CHAT_MESSAGE_CHAT_ROOM_ID = "chat_room_id";
    public static final String CHAT_MESSAGE_MESSAGE = "message";

    // chat bot message table and columns
    public static final String CHAT_BOT_MESSAGE_TABLE = "chat_bot_message";
    public static final String CHAT_BOT_MESSAGE_ID = "id";
    public static final String CHAT_BOT_MESSAGE_MESSAGE = "message";
    public static final String CHAT_BOT_MESSAGE_USER_PROFILE_ID = "user_profile_id";


}
