package vn.magik.hot8.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.models.response.LanguageResponse;
import vn.magik.hot8.models.response.WordResponse;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

public class UserService {
    private Api service;

    private <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson));
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public UserService() {
        service = createService(Api.class);
    }

    public void getLanguage(String token, Callback<LanguageResponse> languageResponse) {
        Call<LanguageResponse> call = service.getLanguages(token);
        call.enqueue(languageResponse);
    }

    public void getAllWordFromServer(String token, String languageCode, Callback<WordResponse> responseWord) {
        Call<WordResponse> call = service.getWords(token, languageCode);
        call.enqueue(responseWord);
    }
}
