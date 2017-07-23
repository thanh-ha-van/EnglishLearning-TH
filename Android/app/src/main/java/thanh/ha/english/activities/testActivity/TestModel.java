package thanh.ha.english.activities.testActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

class TestModel {
    private TestInterface.RequiredPresenterOps presenterOpt;
    private WordSqlite wordSqlite;
    private SharedPreferences sPref;

    TestModel(Context context, TestInterface.RequiredPresenterOps presenterOpt) {
        this.presenterOpt = presenterOpt;
        wordSqlite = new WordSqlite(context);
        sPref = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
    }


    void saveConfig() {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(Constants.KEY_LEVEL, Globals.getIns().getConfig().getLevel());
        editor.apply();
    }
    

    List<Word> loadWordsTest() {
        return wordSqlite.getTest();

    }
}
