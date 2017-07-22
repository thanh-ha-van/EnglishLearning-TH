package vn.magik.hot8.activities.languageActivity;

import retrofit.Response;
import vn.magik.hot8.models.response.LanguageResponse;

/**
 * Created by HaVan on 5/22/2017.
 */

class LanguagePresenter implements LanguageInterface.RequiredPresenterOps {
    private LanguageInterface.RequiredViewOps mView;
    private LanguageModel mModel;

    LanguagePresenter(LanguageInterface.RequiredViewOps mView) {
        this.mView = mView;
        mModel = new LanguageModel(this);
    }
}
