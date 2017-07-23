package thanh.ha.english.activities.examActivity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.models.TestTrans;
import thanh.ha.english.models.Word;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

class ExamPresenter implements ExamInterface.RequiredPresenterOps {
    private List<TestTrans> miniTests;
    private ExamModel mModel;
    private ExamInterface.RequiredViewOps mView;
    private List<Word> words;

    ExamPresenter(Context context, ExamInterface.RequiredViewOps mView) {
        this.mView = mView;
        this.mModel = new ExamModel(context, this);
        miniTests = new ArrayList<>();
    }

    List<TestTrans> loadTestData(int typeTest) {
        switch (typeTest) {
            case Constants.TypeQuestPage.PAGE_TEST_WORD:
                words = mModel.getWordsTestToday();
                break;
            case Constants.TypeQuestPage.PAGE_CHALLENGE:
                words = mModel.getChallengeWords();
                break;
            case Constants.TypeQuestPage.PAGE_MUST_REVIEW:
                words = mModel.getReviewWord();
                break;
            case Constants.TypeQuestPage.PAGE_FINISH_TODAY:
                words = mModel.getWordsLearning();
                break;
        }
        if (words != null) {
            int maxTest = (words.size() > Constants.LIMIT_LEARN_A_PAGE) ? Constants.LIMIT_LEARN_A_PAGE : words.size();
            for (int i = 0; i < maxTest; i++) {
                miniTests.add(new TestTrans(words.get(i), true));
            }
        }
        return miniTests;
    }

    void calResultTest() {
        List<Word> wordsTested = new ArrayList<>();
        int countCorrectAnswer = 0;
        for (int i = 0; i < miniTests.size(); i++) {
            wordsTested.add(miniTests.get(i).getWord());
            countCorrectAnswer += miniTests.get(i).getScore();
        }
        mModel.saveWords(wordsTested);
        int mWordChallenge = miniTests.size() - countCorrectAnswer;
        if (words != null && words.size() > miniTests.size()) {
            int mWordContinue = words.size() - miniTests.size();
            boolean isFinal = (mWordContinue <= Constants.LIMIT_LEARN_A_PAGE);
            mView.showViewContinueTest(isFinal, mWordContinue, mWordChallenge);
        } else {
            mView.showViewResultTest(countCorrectAnswer, mWordChallenge);
        }
    }


}
