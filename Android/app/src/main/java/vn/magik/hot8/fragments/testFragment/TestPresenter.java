package vn.magik.hot8.fragments.testFragment;

import android.content.Context;

import java.util.List;

import vn.magik.hot8.models.TestTrans;
import vn.magik.hot8.models.Word;

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
