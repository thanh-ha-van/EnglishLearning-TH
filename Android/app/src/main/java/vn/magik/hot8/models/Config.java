package vn.magik.hot8.models;

import vn.magik.hot8.constants.Constants;

/**
 * Created by HaVan on 5/24/2017.
 */

public class Config {
    private static final int DEFAULT_WORD_OF_DATE = 8;

    private int mWordOfDay;
    private String token;
    private String currentLanguageCode = null;
    private int level;
    private String hobbies;
    private boolean isNotify;
    private boolean isBuyVip;
    private int learnStatus;
    private int countDate, countTimeReview;
    private boolean isReviewApp;
    private boolean isPermissionWriteFile = false;

    public Config() {
        mWordOfDay = DEFAULT_WORD_OF_DATE;
        hobbies = "'0','1','2','3','4'"; // is this right?
        level = 1;
        countDate = 1;
    }

    public int getLearnStatus() {
        return learnStatus;
    }

    public void setLearnStatus(int learnStatus) {
        this.learnStatus = learnStatus;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        if (hobbies != null && hobbies.length() > 0) {
            this.hobbies = hobbies;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level >= Constants.LevelType.BEGINNER && level <= Constants.LevelType.SUPER) {
            this.level = level;
        } else {
            this.level = 1;
        }
    }

    public int getmWordOfDay() {
        return mWordOfDay;
    }

    public void setmWordOfDay(int mWordOfDay) {
        this.mWordOfDay = mWordOfDay;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCurrentLanguageCode() {
        return currentLanguageCode;
    }

    public void setCurrentLanguageCode(String currentLanguageCode) {
        this.currentLanguageCode = currentLanguageCode;
    }

    public boolean isNotify() {
        return isNotify;
    }

    public void setNotify(boolean notify) {
        isNotify = notify;
    }

    public int getCountDate() {
        return countDate;
    }

    public void setCountDate(int countDate) {
        this.countDate = countDate;
    }

    public int inCreaseTimeReview() {
        return ++countTimeReview;
    }

    public int getCountTimeReview() {
        return countTimeReview;
    }

    public boolean isBuyVip() {
        return isBuyVip;
    }

    public void setBuyVip(boolean buyVip) {
        isBuyVip = buyVip;
    }

    public boolean isReviewApp() {
        return isReviewApp;
    }

    public void setReviewApp(boolean reviewApp) {
        isReviewApp = reviewApp;
    }

    public boolean isPermissionWriteFile() {
        return isPermissionWriteFile;
    }

    public void setPermissionWriteFile(boolean permissionWriteFile) {
        isPermissionWriteFile = permissionWriteFile;
    }
}

