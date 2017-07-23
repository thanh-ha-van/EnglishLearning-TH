package thanh.ha.english.fragments.questFragment;

import android.content.Context;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;


/**
 * Created by HaVan on 6/7/2017.
 */

class QuestPresenter implements QuestInterface.RequiredPresenterOps {
    QuestInterface.RequiredViewOps mView;
    QuestModel mModel;

    QuestPresenter(Context context, QuestInterface.RequiredViewOps mView) {
        this.mView = mView;
        mModel = new QuestModel(context, this);
    }

    void getFlowUser() {
        if (Globals.getIns().getConfig().getCountDate() < Constants.LIMIT_DATE_FREE
                || Globals.getIns().getConfig().isBuyVip()) {
            int mWordToLearn = mModel.getCountWordToday();
            if (mWordToLearn > 0) {
                mView.showViewLearn(mWordToLearn);
            } else {
                int mWordChallenge = mModel.getCountChallengeWords();
                if (mWordChallenge > Globals.getIns().getConfig().getmWordOfDay() / 2) {
                    mView.showViewChallengeWords(mWordChallenge);
                } else {
                    if (mModel.getCountWordSelectedToday() == 0) {
                        mView.showViewChoiceWord(true);
                    } else if (mModel.getCountWordSelectedTomorrow() == 0) {
                        mView.showViewChoiceWord(false);
                    } else {
                        int mWordReview = mModel.getCountRemindWords();
                        if (mWordReview > 0) {
                            mView.showViewMustReviewWords(mWordReview);
                        } else {
                            int mWordLearning = mModel.getCountWordLearning();
                            mView.showViewFinishToday(mWordLearning);
                        }
                    }
                }
            }
        } else {
            mView.showLayoutBuyVip();
        }
    }
}
