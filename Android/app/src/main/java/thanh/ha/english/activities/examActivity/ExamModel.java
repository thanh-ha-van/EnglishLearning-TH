package thanh.ha.english.activities.examActivity;

import android.content.Context;

import java.util.List;

import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.Word;
import thanh.ha.english.models.WordSqlite;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

class ExamModel {
    private static int CURRENT_TIME_REVIEW = 0;

    private ExamInterface.RequiredPresenterOps presenterOpt;
    private WordSqlite wordSqlite;

    ExamModel(Context context, ExamInterface.RequiredPresenterOps presenterOpt) {
        this.presenterOpt = presenterOpt;
        wordSqlite = new WordSqlite(context);
    }

    List<Word> getWordsTestToday() {
        return wordSqlite.getWordsTestToday();
    }

    List<Word> getWordsLearning() {
        int timeReview = Globals.getIns().getConfig().getCountTimeReview();
        if (timeReview != CURRENT_TIME_REVIEW) {
            CURRENT_TIME_REVIEW = timeReview;
            wordSqlite.updateCountTimeReview(timeReview);
        }
        return wordSqlite.getWordsLearning(timeReview);
    }

    List<Word> getChallengeWords() {
        return wordSqlite.getChallengeWord();
    }

    List<Word> getReviewWord() {
        return wordSqlite.getReviewWords();
    }

    void saveWords(List<Word> words) {
        wordSqlite.updateWords(words);
    }
}
