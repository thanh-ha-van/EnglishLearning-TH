package vn.magik.hot8.activities.choiceActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Word;
import vn.magik.hot8.models.WordSqlite;

/**
 * Created by HaVan on 6/12/2017.
 */

class ChoiceWordModel {
    private SharedPreferences sPref;
    private ChoiceWordInterface.PresenterOpt presenterOpt;
    private WordSqlite wordSqlite;

    ChoiceWordModel(Context context, ChoiceWordInterface.PresenterOpt presenterOpt) {
        sPref = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        this.presenterOpt = presenterOpt;
        this.wordSqlite = new WordSqlite(context);
    }

    List<Word> getAllWord() {
        String hobbies = Globals.getIns().getConfig().getHobbies();
        int level = Globals.getIns().getConfig().getLevel();
        return wordSqlite.getWords("0," + hobbies, level);
    }


    int getCountWordSelected() {
        return wordSqlite.getCountWordSelected();
    }

    void updateWords(List<Word> words) {
        wordSqlite.updateWords(words);
    }

}
