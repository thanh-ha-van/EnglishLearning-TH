package thanh.ha.english.activities.practiceActivity;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.TestComp;
import thanh.ha.english.models.TestTrans;
import thanh.ha.english.models.Word;

/**
 * Created by HaVan on 6/7/2017.
 */

class PracticePresenter implements PracticeInterface.PresenterOpt {
    private static final int MAX_CIRCLE_WORD = 3;
    private PracticeModel model;
    private PracticeInterface.ViewOpt mView;
    private List<Word> words;

    private List<List<Word>> listData;
    private int pCurrentData = 0, countCircle = 0;


    PracticePresenter(Context context, PracticeInterface.ViewOpt viewOpt) {
        this.mView = viewOpt;
        this.model = new PracticeModel(context, this);
        listData = new ArrayList<>();
    }


    void getWordsToLearn() {
        words = model.getWordsToday();
        if (words.size() <= 0) return;
        Collections.shuffle(words);
        for (int i = 0; i < words.size(); i++) {
            if (i % 4 == 0) {
                listData.add(new ArrayList<Word>());
            }
            listData.get(listData.size() - 1).add(words.get(i));
        }
        loadDataTest();
        if (Globals.getIns().getConfig().isPermissionWriteFile()) {
            for (int i = 0; i < words.size(); i++) {
                model.downloadAudio(words.get(i));
            }
        }
    }

    boolean resetDataTest() {
        if (countCircle >= MAX_CIRCLE_WORD || !loadDataTest()) {
            saveWordLearn();
            pCurrentData++;
            countCircle = 0;
            if (pCurrentData >= listData.size() || !loadDataTest()) {
                calResultTest();
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    private void calResultTest() {
        int countWordChallenge = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getStatus() == Constants.StatusWord.CHALLENGE) {
                countWordChallenge++;
            }
        }
        mView.showViewResultTest(words.size() - countWordChallenge, countWordChallenge);
    }


    private boolean loadDataTest() {
        countCircle++;
        List<TestTrans> testWordToMean = new ArrayList<>();
        List<TestTrans> testMeanToWord = new ArrayList<>();
        List<TestComp> testCompWords1 = new ArrayList<>();
        List<TestComp> testCompWords2 = new ArrayList<>();
        for (int i = 0; i < listData.get(pCurrentData).size(); i++) {
            Word word = listData.get(pCurrentData).get(i);
            if (!word.isCorrectWordToMean()) {
                testWordToMean.add(new TestTrans(word, true));
            }
            if (!word.isCorrectMeanToWord()) {
                testMeanToWord.add(new TestTrans(word, false));
            }
            if (!word.isCorrectComplete() || !word.isCorrectWordToMean() || !word.isCorrectMeanToWord()) {
                testCompWords1.add(new TestComp(word, true));
                testCompWords2.add(new TestComp(word, false));
            }
        }
        Collections.shuffle(testWordToMean);
        Collections.shuffle(testMeanToWord);
        Collections.shuffle(testCompWords1);
        Collections.shuffle(testCompWords2);
        if (countCircle == 1) {
            mView.updateViewWithData(listData.get(pCurrentData), testWordToMean,
                    testMeanToWord, testCompWords1, testCompWords2);
        } else {
            mView.updateViewWithData(null, testWordToMean,
                    testMeanToWord, testCompWords1, testCompWords2);
        }
        return !(testMeanToWord.size() == 0 && testWordToMean.size() == 0 &&
                testCompWords1.size() == 0 && testCompWords2.size() == 0);
    }

    private void saveWordLearn() {
        for (int i = 0; i < listData.get(pCurrentData).size(); i++) {
            listData.get(pCurrentData).get(i).updateBoxAndTimeReview();
            model.saveWord(listData.get(pCurrentData).get(i));
        }
    }
}
