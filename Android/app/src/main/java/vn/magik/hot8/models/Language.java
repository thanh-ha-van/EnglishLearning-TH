package vn.magik.hot8.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import vn.magik.hot8.adapters.LanguageAdapter;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

public class Language implements Serializable  {
    @SerializedName("id")
    int id;
    @SerializedName("language")
    String language;
    @SerializedName("language_code")
    String language_code;
    @SerializedName("image")
    String image;
    @SerializedName("enable")
    int enable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
