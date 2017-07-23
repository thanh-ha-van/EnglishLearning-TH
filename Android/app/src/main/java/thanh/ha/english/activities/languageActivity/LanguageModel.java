package thanh.ha.english.activities.languageActivity;

import thanh.ha.english.services.UserService;

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
