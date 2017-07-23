package thanh.ha.english.activities.choiceActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;

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
