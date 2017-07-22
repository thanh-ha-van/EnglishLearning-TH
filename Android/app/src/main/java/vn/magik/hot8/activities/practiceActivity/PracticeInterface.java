package vn.magik.hot8.activities.practiceActivity;

import java.util.List;

import vn.magik.hot8.models.TestComp;
import vn.magik.hot8.models.TestTrans;
import vn.magik.hot8.models.Word;

/**
 * Created by HaVan on 5/24/2017.
 */

public interface PracticeInterface {

    interface ViewOpt {

        void updateViewWithData(List<Word> words, List<TestTrans> testWordToMean, List<TestTrans> testMeanToWord,
                                List<TestComp> testCompWords1, List<TestComp> testCompWords2);

        void showViewResultTest(int mWordMaster, int mWordChallenge);
    }

    interface PresenterOpt {

    }
}
