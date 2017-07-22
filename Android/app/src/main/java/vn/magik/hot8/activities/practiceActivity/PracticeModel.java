package vn.magik.hot8.activities.practiceActivity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.models.Word;
import vn.magik.hot8.models.WordSqlite;
import vn.magik.hot8.otherHandle.DownloadManager;
import vn.magik.hot8.otherHandle.FileManager;

/**
 * Created by HaVan on 6/7/2017.
 */

class PracticeModel {
    private PracticeInterface.PresenterOpt presenterOpt;
    private WordSqlite wordSqlite;

    PracticeModel(Context context, PracticeInterface.PresenterOpt presenterOpt) {
        this.presenterOpt = presenterOpt;
        this.wordSqlite = new WordSqlite(context);
    }

    List<Word> getWordsToday() {
        return wordSqlite.getWordsToday();
    }

    void saveWord(Word word) {
        wordSqlite.updateWord(word);
    }


    void downloadAudio(Word word) {
        if (!FileManager.checkFileExists(word.getAudio())) {
            new DownloadManager().downloadFile(word.getAudio());
        }
    }
}
