package vn.magik.hot8.fragments.completeWordFragment;

/**
 * Created by HaVan on 6/7/2017.
 */

public interface CompleteWordInterface {
    interface UserAnswerListener {
        void onCompleteAnswer();
    }

    interface ViewOpt {
        void checkAnswerFinish();

        void checkAnswer(String currentAnswer, boolean isCorrect);

    }

    interface PresenterOpt {

    }
}
