package thanh.ha.english.activities.wordActivity;

import android.content.Context;

import java.util.List;

import thanh.ha.english.models.Word;

/**
 * Created by HaVan on 5/24/2017.
 */

public class WordPresenter implements WordInterface.PresenterOpt {
    private WordModel model;
    private Context context;
    private WordInterface.ViewOpt viewOpt;

    public WordPresenter(Context context, WordInterface.ViewOpt viewOpt) {
        this.context = context;
        this.viewOpt = viewOpt;
        this.model = new WordModel(context, this);
    }

    public List<Word> loadAllMyWord() {
        return model.loadAllMyWord();
    }

}
