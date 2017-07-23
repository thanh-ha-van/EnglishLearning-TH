package thanh.ha.english.activities.practiceActivity;

import android.content.Context;

import java.util.List;

import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;
import thanh.ha.english.otherHandle.DownloadManager;
import thanh.ha.english.otherHandle.FileManager;

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
