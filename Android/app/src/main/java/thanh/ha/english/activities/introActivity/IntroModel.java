package thanh.ha.english.activities.introActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;
import thanh.ha.english.models.response.WordResponse;
import thanh.ha.english.services.UserService;

/**
 * Created by HaVan on 5/22/2017.
 */

class IntroModel {
    private UserService userService;
    private IntroInterface.RequiredPresenterOps mPresenter;
    private WordSqlite wordSqlite;
    private SharedPreferences sharedPreferences;

    IntroModel(Context context, IntroInterface.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
        userService = new UserService();
        wordSqlite = new WordSqlite(context);
        sharedPreferences = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
    }


    void saveLevelConfig() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.KEY_LEVEL, Globals.getIns().getConfig().getLevel());
        editor.apply();
    }

    void saveWordToDatabase(List<Word> words) {
        wordSqlite.insertMultiWord(words);
    }

    void loadAllWordFromServer() {
        userService.getAllWordFromServer(
                Globals.getIns().getConfig().getToken(),
                Globals.getIns().getConfig().getCurrentLanguageCode(),
                new Callback<WordResponse>() {
                    @Override
                    public void onResponse(Response<WordResponse> response) {
                        mPresenter.loadWordFinish(response);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mPresenter.loadWordFinish(null);
                    }
                });
    }
}
