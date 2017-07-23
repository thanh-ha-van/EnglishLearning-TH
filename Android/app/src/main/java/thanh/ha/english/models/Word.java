package thanh.ha.english.models;

import com.google.gson.annotations.SerializedName;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.utils.Utils;

/**
 * Created by HaVan on 5/24/2017.
 */

public class Word {
    @SerializedName("id")
    private int id;
    @SerializedName("word")
    private String content;
    @SerializedName("group")
    private int group;
    @SerializedName("trans")
    private String trans;
    @SerializedName("category")
    private String category;
    @SerializedName("audio")
    private String audio;
    @SerializedName("level")
    private int level;
    @SerializedName("hobby")
    private int hobby;
    @SerializedName("enable")
    private int isEnable;
    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("mean")
    private String mean;

    private transient int box;
    private transient int status;
    private transient int timeStart;
    private transient int timeReview;
    private transient int timeModify;
    private boolean isCorrectMeanToWord;
    private boolean isCorrectWordToMean;
    private boolean isCorrectComplete;

    public Word(String word, String mean) {
        this.content = word;
        this.mean = mean;
        this.timeStart = 0;
        this.level = 2;
    }

    public Word(String word, String mean, int box) {
        this.content = word;
        this.mean = mean;
        this.box = box;
    }

    public Word(int id, String word, int group, String trans, String category, String audio, int level,
                int hobby, int isEnable, String languageCode, String mean, int box, int status,
                int timeStart, int timeReview, int timeModify) {
        this.id = id;
        this.content = word;
        this.group = group;
        this.trans = trans;
        this.category = category;
        this.audio = audio;
        this.level = level;
        this.hobby = hobby;
        this.isEnable = isEnable;
        this.languageCode = languageCode;
        this.mean = mean;
        this.box = box;
        this.status = status;
        this.timeStart = timeStart;
        this.timeReview = timeReview;
        this.timeModify = timeModify;
    }

    public void updateBoxAndTimeReview() {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        if (isCorrectMeanToWord && isCorrectWordToMean && isCorrectComplete) {
            if (timeReview == 0 || timeReview < currentTime) {
                box++;
            }
            status = Constants.StatusWord.TEST;
        } else {
            status = Constants.StatusWord.CHALLENGE;
        }
        timeReview = Utils.getTimestampReview(box);
        timeModify = currentTime;
    }


    public boolean isCorrectMeanToWord() {
        return isCorrectMeanToWord;
    }

    public void setCorrectMeanToWord(boolean correctMeanToWord) {
        isCorrectMeanToWord = correctMeanToWord;
    }

    public boolean isCorrectWordToMean() {
        return isCorrectWordToMean;
    }

    public void setCorrectWordToMean(boolean correctWordToMean) {
        isCorrectWordToMean = correctWordToMean;
    }

    public boolean isCorrectComplete() {
        return isCorrectComplete;
    }

    public void setCorrectComplete(boolean correctComplete) {
        isCorrectComplete = correctComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserLevel() {
        return box;
    }

    public void setUserLevel(int userLevel) {
        this.box = userLevel;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = (int) (timeStart / 1000);
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }


    public int getHobby() {
        return hobby;
    }

    public int getBox() {
        return box;
    }

    public int getStatus() {
        return status;
    }

    public long getTimeReview() {
        return timeReview;
    }

    public void setModify() {
        timeModify = (int) (System.currentTimeMillis() / 1000);
    }

    public int getTimeModify() {

        return timeModify;
    }

    public void setTimeReview(int timeReview) {
        this.timeReview = timeReview;
    }
}
