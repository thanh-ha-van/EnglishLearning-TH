package vn.magik.hot8.activities.testActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Word;
import vn.magik.hot8.models.WordSqlite;

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
