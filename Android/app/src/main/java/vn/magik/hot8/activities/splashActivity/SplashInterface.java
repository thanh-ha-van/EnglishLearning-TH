package vn.magik.hot8.activities.splashActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Response;
import vn.magik.hot8.models.Language;
import vn.magik.hot8.models.response.LanguageResponse;
import vn.magik.hot8.models.response.WordResponse;

/**
 * Created by HaVan on 5/24/2017.
 */

interface SplashInterface {
    interface RequiredViewOps {

        void getLanguageFinish(ArrayList<Language> languages);

        void getLanguageFail();
    }

    interface RequiredPresenterOps {

        void getLanguageEnable(Response<LanguageResponse> response);
    }
}
