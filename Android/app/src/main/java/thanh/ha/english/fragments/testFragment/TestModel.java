package thanh.ha.english.fragments.testFragment;

import android.content.Context;

import java.util.List;

import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;

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
