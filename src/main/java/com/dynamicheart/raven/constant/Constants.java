package com.dynamicheart.raven.constant;

/**
 * Created by dynamicheart on 6/8/2017.
 */
public class Constants {
    public static final Integer USER_STATUS_OK = 0;
    public static final Integer USER_STATUS_DISABLE = 1;

    public static final Integer RAVEN_TYPE_ORDINARY = 0;
    public static final Integer RAVEN_TYPE_POLLS = 1;

    public static final Integer WHISTLE_BLOWING_STATUS_HANDLING = 0;
    public static final Integer WHISTLE_BLOWING_STATUS_FINISHED = 1;

    // 其它
    public static final Integer WHISTLE_BLOWING_TYPE_OTHERS = 0;
    // 广告
    public static final Integer WHISTLE_BLOWING_TYPE_ADVERTISEMENT = 1;
    // 淫秽色情
    public static final Integer WHISTLE_BLOWING_TYPE_PORN = 2;
    // 有害信息
    public static final Integer WHISTLE_BLOWING_TYPE_HARM = 3;
    // 违法信息
    public static final Integer WHISTLE_BLOWING_TYPE_ILLEGAL = 4;
    // 虚假信息
    public static final Integer WHISTLE_BLOWING_TYPE_FALSE = 5;

    public static final Integer HOUSE_STATUS_NORMAL = 0;
    public static final Integer HOUSE_STATUS_DISABLE = 1;

    public static final Integer SERVE_STATUS_HANDLING = 0;
    public static final Integer SERVE_STATUS_ACCEPTED = 1;
    public static final Integer SERVE_STATUS_REFUSED = 2;

    public static final Integer SERVE_TYPE_ORDINARY = 0;
    public static final Integer SERVE_TYPE_MAESTER = 1;
    public static final Integer SERVE_TYPE_LORD = 2;

    public static final Integer MEMBER_ROLE_ORDINARY = 0;
    public static final Integer MEMBER_ROLE_MAESTER = 1;
    public static final Integer MEMBER_ROLE_LORD = 2;

    // string templates
    public static final String REDIS_USER_KEY_TEMPLATE="user:%s:token";
    public static final String REDIS_ADMIN_KEY_TEMPLATE="admin:%s:token";
    public static final String REDIS_INSTALLATION_KEY_TEMPLATE="user:%s:installation-id";
    public static final String AUTHORIZATION_TEMPLATE="%s-%s";

    // a field that store current user id
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    public static final int TOKEN_EXPIRES_HOURS = 720;

    //Request header field
    public static final String HEADER_X_AUTH = "X-AUTH";

    //LeanCloud
    public static final String LEAN_CLOUD_API_BASE_URL = "https://api.leancloud.cn/1.1%s";
    public static final String LEAN_CLOUD_RAVEN_APP_ID = "TFJOwzoaKwEFSMyIMIV4XPHY-gzGzoHsz";
    public static final String LEAN_CLOUD_RAVEN_APP_KEY = "InFOgxkqvOfz5Bl9YaXijdHd";
    public static final String LEAN_CLOUD_RAVEN_MASTER_KEY = "VpaIyTbOtE3qGvl8STU750ob";
    public static final String LEAN_CLOUD_PUSH_ACTION = "com.dynamicheart.raven.push";
}

