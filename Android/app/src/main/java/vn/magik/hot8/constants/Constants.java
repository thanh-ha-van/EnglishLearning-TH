package vn.magik.hot8.constants;

/**
 * Created by HaVan on 5/24/2017.
 */

public class Constants {
    public static final String SERVER = "http://hot8.hellolingoapp.com/";
    //    public static final String SERVER_AUDIO = SERVER + "data/";
    public static final String SERVER_AUDIO = SERVER + "http://hot8.hellolingoapp.com/";
    public static final String SERVER_IMAGE = SERVER + "data/language/";
    public static final String FILE_CONFIG = "ice5.config";

    public static final String KEY_START_APP = "start_app";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_WORD_OF_DAY = "word_of_day";
    public static final String KEY_FAVORITE = "favorite";
    public static final String KEY_LEVEL = "user_level";
    public static final String KEY_NOTIFICATION = "is_notify";
    public static final String KEY_COUNT_DATE = "count_date";
    public static final String KEY_DATA_TEST = "data_test";
    public static final String KEY_TITLE = "master";
    public static final String KEY_MESSAGE = "challenge";
    public static final String KEY_ACTIVITY_NEXT = "activity_next";
    public static final String KEY_TYPE_TEST = "type_practice";
    public static final String KEY_COUNT_TIME_REVIEW = "time_review";
    public static final String KEY_CHOICE_WORD = "count_word";
    public static final String KEY_START_FROM_MAIN = "start_from_main";
    public static final String KEY_REVIEW = "review_app";
    public static final String KEY_ACTIVE_TIME = "active_time";

    public static final String PUBLIC_KEY = "review_app";


    public static final int LIMIT_LEARN_A_PAGE = 20;
    public static final int LIMIT_DATE_FREE = 3;


    public static class LevelType {
        public static final int BEGINNER = 1;
        public static final int EASY = 2;
        public static final int MEDIUM = 3;
        public static final int HARD = 4;
        public static final int SUPER = 5;
    }

    public static int[] TIME_REVIEW = {1, 3, 5, 7, 14, 100};


    public class StatusWord {
        public static final int NONE = 0;
        public static final int SELECTED = 1;
        public static final int LEARNING = 2;
        public static final int TEST = 3;
        public static final int CHALLENGE = 4;
        public static final int MASTER = 5;
    }

    public class TypeQuestPage {
        public static final int PAGE_LEARN = 1;
        public static final int PAGE_CHALLENGE = 2;
        public static final int PAGE_MUST_REVIEW = 3;
        public static final int PAGE_CHOICE_WORD = 4;
        public static final int PAGE_FINISH_TODAY = 5;
        public static final int PAGE_TEST_WORD = 6;
    }

    public class PackageVip {
        public static final String BUY_1_MONTH = "hot8_a_month";
        public static final String BUY_6_MONTH = "hot8_6_month";
        public static final String BUY_FOREVER = "ice_hot8";
    }
}
