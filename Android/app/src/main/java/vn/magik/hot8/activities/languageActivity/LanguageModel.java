package vn.magik.hot8.activities.languageActivity;

import retrofit.Callback;
import retrofit.Response;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.response.LanguageResponse;
import vn.magik.hot8.models.response.WordResponse;
import vn.magik.hot8.services.UserService;

/**
 * Created by HaVan on 5/22/2017.
 */

class LanguageModel {
    private UserService userService;
    private LanguageInterface.RequiredPresenterOps mPresenter;

    LanguageModel(LanguageInterface.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
        userService = new UserService();
    }

}
