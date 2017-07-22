package vn.magik.hot8.fragments.profileFragment;

import android.content.Context;

import java.util.List;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.models.Day;

/**
 * Created by HaVan on 5/23/2017.
 */

class ProfilePresenter implements ProfileInterface.RequiredPresenterOps {
    private ProfileInterface.RequiredViewOps mView;
    private ProfileModel mModel;

    ProfilePresenter(Context context, ProfileInterface.RequiredViewOps mView) {
        mModel = new ProfileModel(context);
    }

    int getCountLearningWord() {
        return mModel.getCountLearningWord();
    }

    int getCountChallengeWord() {
        return 3;
        //return mModel.getCountWordWithStatus(Constants.StatusWord.CHALLENGE);
    }

    int getCountMasteredWord() {
        return mModel.getCountWordWithStatus(Constants.StatusWord.MASTER);
    }

    //// TODO: 7/5/2017  must change to get real words of week and month
    int getCountMasteredWordWeek() {
        return 8;
        //return mModel.getCountWordWithStatus(Constants.StatusWord.MASTER);
    }

    int getCountMasteredWordMonth() {
        return 32;
        //return mModel.getCountWordWithStatus(Constants.StatusWord.MASTER);
    }


    List<Day> getDateHistory() {
        return mModel.getDateHistory();
    }
}
