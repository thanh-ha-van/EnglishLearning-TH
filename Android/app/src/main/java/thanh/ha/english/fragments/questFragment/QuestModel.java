package thanh.ha.english.fragments.questFragment;

import android.content.Context;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.models.WordSqlite;

/**
 * Created by HaVan on 6/7/2017.
 */

class QuestModel {
    private QuestInterface.RequiredPresenterOps mPresenter;
    private WordSqlite wordSqlite;

    QuestModel(Context context, QuestInterface.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
        this.wordSqlite = new WordSqlite(context);
    }

    int getCountWordToday() {
        return wordSqlite.getCountWordToday();
    }

    int getCountWordSelectedToday() {
        return wordSqlite.getCountWordSelectedToday();
    }

    int getCountWordSelectedTomorrow() {
        return wordSqlite.getCountWordWithStatus(Constants.StatusWord.SELECTED);
    }

    int getCountRemindWords() {
        return wordSqlite.getCountRemindWords();
    }


    int getCountWordLearning() {
        return wordSqlite.getCountWordLearning();
    }


    int getCountChallengeWords() {
        return wordSqlite.getCountWordWithStatus(Constants.StatusWord.CHALLENGE);
    }

}
