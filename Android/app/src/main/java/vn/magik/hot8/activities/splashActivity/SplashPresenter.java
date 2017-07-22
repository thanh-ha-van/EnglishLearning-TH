package vn.magik.hot8.activities.splashActivity;

import android.content.Context;

import retrofit.Response;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.response.LanguageResponse;
import vn.magik.hot8.models.response.WordResponse;

/**
 * Created by HaVan on 5/24/2017.
 */

class SplashPresenter implements SplashInterface.RequiredPresenterOps {
    private SplashInterface.RequiredViewOps mView;
    private SplashModel mModel;


    SplashPresenter(Context context, SplashInterface.RequiredViewOps mView) {
        this.mView = mView;
        mModel = new SplashModel(context, this);
    }

    boolean isFirstRun() {
        String currentLanguageCode = Globals.getIns().getConfig().getCurrentLanguageCode();
        if (currentLanguageCode == null) {
            mModel.getLanguagesEnable();
            return false;
        }
        return true;
    }

    int getCountWordSelected() {
        return mModel.getCountWordSelected();
    }

    void checkInAppServer() {
        //todo check inapp with server
    }


    @Override
    public void getLanguageEnable(Response<LanguageResponse> response) {
        if (response != null && response.isSuccess() && response.body() != null) {
            mView.getLanguageFinish(response.body().getLanguages());
        } else {
            mView.getLanguageFail();
        }
    }


}
