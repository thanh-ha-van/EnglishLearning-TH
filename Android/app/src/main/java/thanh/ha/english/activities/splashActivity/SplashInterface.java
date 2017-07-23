package thanh.ha.english.activities.splashActivity;

import java.util.ArrayList;

import retrofit.Response;
import thanh.ha.english.models.Language;
import thanh.ha.english.models.response.LanguageResponse;

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
