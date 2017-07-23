package thanh.ha.english.activities.languageActivity;

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
