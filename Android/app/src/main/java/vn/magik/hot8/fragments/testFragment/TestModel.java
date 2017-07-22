package vn.magik.hot8.fragments.testFragment;

import android.content.Context;

import java.util.List;

import vn.magik.hot8.models.Word;
import vn.magik.hot8.models.WordSqlite;

/**
 * Created by HaVan on 6/5/2017.
 */

class TestModel {
    private WordSqlite wordSqlite;

    TestModel(Context context) {
        this.wordSqlite = new WordSqlite(context);
    }

    List<Word> getAnswers(Word word, int numAnswer) {
        return wordSqlite.randomAnswerForWord(word, numAnswer);
    }

}
