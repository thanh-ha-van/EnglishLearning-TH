package vn.magik.hot8.services;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;
import vn.magik.hot8.models.response.LanguageResponse;
import vn.magik.hot8.models.response.WordResponse;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

interface Api {
    @GET("/get_languages")
    Call<LanguageResponse> getLanguages(@Header("Authorization") String token);

    @GET("/get_words")
    Call<WordResponse> getWords(@Header("Authorization") String token, @Query("language_code") String languageCode);
}
