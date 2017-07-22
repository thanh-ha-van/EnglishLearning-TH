package vn.magik.hot8.activities.choiceActivity;

/**
 * Created by HaVan on 5/24/2017.
 */

public interface ChoiceWordInterface {

    interface ViewOpt {

        void updateView(int mCountSelected, int position);

        void handleFinishChoiceWord();
    }

    interface PresenterOpt {

    }
}
