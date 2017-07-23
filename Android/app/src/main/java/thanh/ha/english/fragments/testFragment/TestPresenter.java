package thanh.ha.english.fragments.testFragment;

import android.content.Context;

import java.util.List;

import thanh.ha.english.models.TestTrans;
import thanh.ha.english.models.Word;

/**
 * Created by HaVan on 6/5/2017.
 */

class TestPresenter {
    private TestInterface.RequiredViewOps mView;
    private TestModel mModel;

    TestPresenter(Context context, TestInterface.RequiredViewOps mView) {
        this.mView = mView;
        mModel = new TestModel(context);
    }


    void randomAnswers(TestTrans miniTest, int numAnswer) {
        List<Word> words = mModel.getAnswers(miniTest.getWord(), numAnswer);
        miniTest.setAnswers(words);
    }
}
