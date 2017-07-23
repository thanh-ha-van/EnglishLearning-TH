package thanh.ha.english.fragments.questFragment;

/**
 * Created by HaVan on 5/25/2017.
 */

interface QuestInterface {
    interface RequiredViewOps {
        void showViewLearn(int mWordToLearn);

        void showViewChallengeWords(int mWordChallenge);

        void showViewMustReviewWords(int mWordReview);

        void showViewChoiceWord(boolean choiceWordTomorrow);

        void showViewFinishToday(int mWordLearning);

        void showLayoutBuyVip();
    }

    interface RequiredPresenterOps {

    }

}
