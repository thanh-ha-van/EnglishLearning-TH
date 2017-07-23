package thanh.ha.english.activities.wordActivity;

import android.content.Context;

import java.util.List;

import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;

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
