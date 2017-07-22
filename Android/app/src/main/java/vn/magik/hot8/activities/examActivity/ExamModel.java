package vn.magik.hot8.activities.examActivity;

import android.content.Context;

import java.util.List;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Word;
import vn.magik.hot8.models.WordSqlite;

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
