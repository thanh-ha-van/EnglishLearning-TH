package vn.magik.hot8.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import vn.magik.hot8.models.Language;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

public class LanguageResponse {
    @SerializedName("error_code")
    int errorCode;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<Language> languages;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

}
