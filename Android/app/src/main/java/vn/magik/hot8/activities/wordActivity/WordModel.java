package vn.magik.hot8.activities.wordActivity;

import android.content.Context;

import java.util.List;

import vn.magik.hot8.models.Word;
import vn.magik.hot8.models.WordSqlite;

/**
 * Created by HaVan on 5/24/2017.
 */

public class WordModel {
    public Context context;
    public WordInterface.PresenterOpt presenterOpt;
    public WordSqlite wordSqlite;

    public WordModel(Context context, WordInterface.PresenterOpt presenterOpt) {
        this.context = context;
        this.presenterOpt = presenterOpt;
        this.wordSqlite = new WordSqlite(this.context);
    }

    public List<Word> loadAllMyWord() {
        return wordSqlite.getReviewWords();
    }
}
