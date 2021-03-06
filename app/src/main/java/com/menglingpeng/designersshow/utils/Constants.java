package com.menglingpeng.designersshow.utils;

/**
 * Created by mengdroid on 2017/10/11.
 */

public class Constants {

    public static final String TYPE = "type";
    public static final String POSITION = "position";
    public static final String IS_FIRST_START = "is_first_start";
    public static final String IS_LOGIN = "is_login";
    public static final String ID = "id";
    public static final String REQUEST_GET_MEIHOD = "get";
    public static final String REQUEST_POST_MEIHOD = "post";
    public static final String REQUEST_PUT_MEIHOD = "put";
    public static final String REQUEST_DELETE_MEIHOD = "delete";
    public static final String CODE_404_NOT_FOUND = "404";
    public static final String CODE_204_NO_CONTENT = "204";
    public static final String CODE_403_FORBIDDEN = "403";
    public static final String CODE_201_CREATED = "201";
    public static final String TIME_OUT = "timed out";
    public static final String EMPTY = "[]";
    public static final String SNACKBAR_TEXT = "snackbar_text";
    public static final String DETAIL = "detail";
    public static final String SETTINGS_DATA_PREF = "settings_data_pref";
    public static final String SAVING_LOWER_IMAGE = "saving_lower_image";
    public static final String GIFS_AUTO_PLAY = "gifs_auto_play";
    public static final String NIGHT_MODE = "night_mode";
    public static final String DOUBLE_BACK_TO_EXIT = "double_back_to_exit";
    public static final String SETTINGS_ABOUT_PREF = "settings_about_pref";
    public static final String SETTINGS_ADVANCED_SETTINGS_PREF = "settings_advanced_settings_pref";
    public static final String LOGIN_DIALOG_FRAGMENT_TAG = "login_dialog_fragment";
    public static final String ATTACHMENTS_DIALOG_FRAGMENT_TAG = "attachments_dialog_fragment";
    //暂时使用github地址
    public static final String APP_URL = "https://github.com/menglingpeng/shots";
    /*public static final String OPEN_RESOURCE_LICENSES_JSON = "{"\"name\"" : "\"OkHttp\"", "\"author\"" : "\"Square\"",
    "\"license\"" : "\"Apache License, Version 2.0\""}"*/
    /**
     * Dribbble API
     */
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String SCOPE = "scope";
    public static final String CODE = "code";
    public static final String CLIENT_ID_VALUE = "2824a0365fffaa9fe9b74dce83921d185e5a45ad6c879a06f83399a25ce128a0";
    public static final String CLIENT_SECRET_VALUE = "e23e3697dfa339210452f4e8b72fef47e8b1a5373b236f09a66fa6a892d1c5a2";
    public static final String SCOPE_VALUE = "public+write+comment+upload";
    public static final String REQUEST_AUTH_USER = "auth_user";
    public static final String REQUEST_SINGLE_USER = "single_user";
    public static final String REQUEST_LIST_DETAIL_FOR_AUTH_USER = "list_detail_for_auth_user";
    public static final String REQUEST_LIST_SHOTS_FOR_AUTH_USER = "list_shots_for_auth_user";
    public static final String REQUEST_LIST_BUCKETS_FOR_AUTH_USER = "list_buckets_for_auth_user";
    public static final String REQUEST_LIST_LIKES_FOR_AUTH_USER = "list_likess_for_auth_user";
    public static final String REQUEST_LIST_PROJECTS_FOR_AUTH_USER = "list_projects_for_auth_user";
    public static final String REQUEST_LIST_FOLLOWERS_FOR_AUTH_USER = "list_followers_for_auth_user";
    public static final String REQUEST_LIST_FOLLOWING_FOR_AUTH_USER = "list_following_for_auth_user";
    public static final String REQUEST_LIST_DETAIL_FOR_A_USER = "list_detail_of_a_user";
    public static final String REQUEST_LIST_SHOTS_FOR_A_USER = "list_shots_for_a_user";
    public static final String REQUEST_LIST_BUCKETS_FOR_A_USER = "list_buckets_for_a_user";
    public static final String REQUEST_LIST_LIKES_FOR_A_USER = "list_likess_for_a_user";
    public static final String REQUEST_LIST_PROJECTS_FOR_A_USER = "list_projects_for_a_user";
    public static final String REQUEST_LIST_FOLLOWERS_FOR_A_USER = "list_followers_for_a_user";
    public static final String REQUEST_LIST_FOLLOWING_FOR_A_USER = "list_following_for_a_user";
    public static final String SHOT_DETAIL = "shot_detail";
    public static final String USER_SHOT_DETAIL = "user_shot_detail";
    public static final String AUTH_USER_NAME = "user_name";
    public static final String AUTH_USER_AVATAR_URL = "avatar_url";
    public static final String AUTH_USER_ID = "user_id";
    public static final String SHOT_ID = "shot_id";
    public static final String BUCKET_ID = "bucket_id";
    public static final String COMMENT_ID = "comment_id";
    public static final String BODY = "body";
    public static final String COMMENTS_COUNT = "comments_count";
    public static final String USER_NAME = "user_name";
    public static final String LIST_SHOTS_FOR_USERS_FOLLEOED_BY_A_USER_URL = "https://api.dribbble" +
            ".com/v1/user/following/shots";
    public static final String LIST_SHOTS_FOR_AUTH_USER_LIKES_URL = "https://api.dribbble.com/v1/user/likes";
    public static final String LIST_SHOTS_FOR_AUTH_USER_URL = "https://api.dribbble.com/v1/user/shots";
    public static final String LIST_BUCKETS_FOR_AUTH_USER_URL = "https://api.dribbble.com/v1/user/buckets";
    public static final String LIST_PROJECTS_FOR_AUTH_USER_URL = "https://api.dribbble.com/v1/user/projects";
    public static final String LIST_FOLLOWERS_FOR_AUTH_USER_URL = "https://api.dribbble.com/v1/user/followers";
    public static final String LIST_FOLLOWING_FOR_AUTH_USER_URL = "https://api.dribbble.com/v1/user/following";
    public static final String BUCKETS_URL = "https://api.dribbble.com/v1/buckets";
    public static final String PROJECTS_URL = "https://api.dribbble.com/v1/projects";
    public static final String AUTHENTICATED_USER_URL = "https://api.dribbble.com/v1/user";
    public static final String SINGLE_USER_URL = "https://api.dribbble.com/v1/users";
    public static final String SHOTS_URL = "https://api.dribbble.com/v1/shots";
    public static final String REQUEST_AUTH_TOKEN_URL = "https://dribbble.com/oauth/token";
    public static final String REDIRECT_USERS_TO_REQUEST_DRIBBBLE_ACCESS_URL = "https://dribbble" +
            ".com/oauth/authorize?client_id=2824a0365fffaa9fe9b74dce83921d185e5a45ad6c879a06f83399a25ce128a0" +
            "&redirect_uri=shots://dribbble-login-callback/shotDetail&scope=public+write+comment+upload";
    public static final String AUTH_TOKEN = "auth_token";
    public static final String APP_ACCESS_TOKEN = "498b79c0b032215d0e1e1a2fa487a9f8e5637918fa373c63aa29e48528b2822c";
    public static final String MENU_HOME = "Home";
    public static final String MENU_EXPLORE = "Explore";
    public static final String MENU_MY_LIKES = "my_likes";
    public static final String MENU_MY_BUCKETS = "list_buckets_for_auth_user";
    public static final String MENU_MY_SHOTS = "my_shots";
    public static final String MENU_MY_FAVORITES = "my_favorites";
    public static final String MENU_SETTING = "setting";
    public static final String TAB_POPULAR = "Popular";
    public static final String TAB_RECENT = "Recent";
    public static final String TAB_FOLLOWING = "Following";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPLORE = "explore";
    public static final String LIST = "list";
    public static final String SORT = "sort";
    public static final String TIMEFRAME = "timeframe";
    public static final String DATE = "date";
    public static final String PAGE = "page";
    public static final String PER_PAGE = "per_page";
    //默认值1
    public static final int PAGE_VALUE = 1;
    //默认值12，最高到100
    public static final int PER_PAGE_VALUE = 12;
    public static final String SORT_POPULAR = "popular";
    public static final String SORT_RECENT = "recent";
    public static final String SORT_COMMENTS = "comments";
    public static final String SORT_VIEWS = "views";
    public static final String LIST_SHOTS = "shots";
    public static final String LIST_ANIMTED = "animated";
    public static final String LIST_ATTACHMENTS = "attachments";
    public static final String LIST_DEBUTS = "debuts";
    public static final String LIST_PLAYOFFS = "playoffs";
    public static final String LIST_REBOUNDS = "rebounds";
    public static final String LIST_TEAM = "team";
    public static final String TIMEFRAME_NOW = "now";
    public static final String TIMEFRAME_WEEK = "week";
    public static final String TIMEFRAME_MONTH = "month";
    public static final String TIMEFRAME_YEAR = "year";
    public static final String TIMEFRAME_EVER = "ever";
    public static final String REQUEST_SORT_POPULAR = "explore_sort_popular";
    public static final String REQUEST_SORT_RECENT = "explore_sort_recent";
    public static final String REQUEST_SORT_COMMENTS = "explore_sort_comments";
    public static final String REQUEST_SORT_VIEWS = "explore_sort_views";
    public static final String REQUEST_LIST_SHOTS = "explore_list_shots";
    public static final String REQUEST_LIST_ANIMTED = "explore_list_animated";
    public static final String REQUEST_LIST_ATTACHMENTS = "explore_list_attachments";
    public static final String REQUEST_LIST_DEBUTS = "explore_list_debuts";
    public static final String REQUEST_LIST_PLAYOFFS = "explore_list_playoffs";
    public static final String REQUEST_LIST_REBOUNDS = "explore_list_rebounds";
    public static final String REQUEST_LIST_TEAM = "explore_list_team";
    public static final String REQUEST_TIMEFRAME_NOW = "explore_timeframe_now";
    public static final String REQUEST_TIMEFRAME_WEEK = "explore_timeframe_week";
    public static final String REQUEST_TIMEFRAME_MONTH = "explore_timeframe_month";
    public static final String REQUEST_TIMEFRAME_YEAR = "explore_timeframe_year";
    public static final String REQUEST_TIMEFRAME_EVER = "explore_timeframe_ever";
    public static final String SHOTS = "shots";
    public static final String BUCKETS = "buckets";
    public static final String COMMENTS = "comments";
    public static final String ATTACHMENTS = "attachments";
    public static final String LIKES = "likes";
    public static final String PROJECTS = "projects";
    public static final String REQUEST_NORMAL = "normal";
    public static final String REQUEST_REFRESH = "refresh";
    public static final String REQUEST_LOAD_MORE = "loadmore";
    public static final String REQUEST_AUTH_TOKEN = "auth_token";
    public static final String REQUEST_LIST_COMMENTS_FOR_A_SHOT = "list_comments_for_a_shot";
    public static final String REQUEST_LIST_ATTACHMENTS_FOR_A_SHOT = "list_attachments_for_a_shot";
    public static final String REQUEST_CREATE_A_COMMENT = "create_a_comment";
    public static final String REQUEST_LIKE_A_COMMENT = "like_a_comment";
    public static final String REQUEST_LIST_SHOTS_FOR_A_BUCKET = "list_shots_for_a_bucket";
    public static final String REQUEST_ADD_A_SHOT_TO_BUCKET = "add_a_shot_to_a_bucket";
    public static final String REQUEST_LIST_SHOTS_FOR_A_PROJECT = "list_shots_for_a_project";
    public static final String LIKE = "like";
    public static final String USER = "user";
    public static final String REQUEST_LIKE_A_SHOT = "like_a_shot";
    public static final String REQUEST_UNLIKE_A_SHOT = "unlike_a_shot";
    public static final String REQUEST_CHECK_IF_LIKE_SHOT = "is_liked";
    public static final String REQUEST_CREATE_A_BUCKET = "create_a_bucket";
    public static final String REQUEST_CHOOSE_BUCKET = "choose_bucket";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FOLLOWERS = "followers";
    public static final String FOLLOWING = "following";
    public static final String FOLLOW = "follow";
    public static final String CHECK_IF_YOU_ARE_FOLLOWING_A_USER = "check_if_you_are_following_a_user";
    public static final String REQUEST_UNFOLLOW_A_USER = "unfollow_a_user";
    public static final String REQUEST_FOLLOW_A_USER = "follow_a_user";
    //联系我
    public static final String MAIL_TO_URL = "mailto:menglingpengoffice@gmail.com";
    public static final String EMAIL_CC = "menglingpengoffice@gmail.com";
    public static final String EMAIL_SUBJECT = "[SHOTS]Feedback";

    public static final String FAVORITES_DETAIL_BASE_TYPE = "favorites_detail_base_type";
    public static final String FAVORITES_DETAIL_BOOK_TYPE = "favorites_detail_book_type";
    public static final String FAVORITES_DETAIL_THIRD_TYPE = "favorites_detail_third_type";


}
