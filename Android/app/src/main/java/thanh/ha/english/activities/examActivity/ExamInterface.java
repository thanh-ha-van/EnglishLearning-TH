package thanh.ha.english.activities.examActivity;

interface ExamInterface {
    interface RequiredViewOps {
        void showViewResultTest(int mWordMaster, int mWordChallenge);

        void showViewContinueTest(boolean isFinal, int mWordContinue, int mWordChallenge);

    }

    interface RequiredPresenterOps {

    }
}
