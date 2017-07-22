package vn.magik.hot8.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.magik.hot8.models.Word;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

public class WordResponse {
    @SerializedName("error_code")
    int errorCode;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Word> words;

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public List<Word> getWords() {
        return words;
    }
}
