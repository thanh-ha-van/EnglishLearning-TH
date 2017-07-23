package thanh.ha.english.activities.introActivity;


import retrofit.Response;
import thanh.ha.english.models.response.WordResponse;

/**
 * Created by HaVan on 5/22/2017.
 */

class IntroInterface {
    interface RequiredViewOps {
        void getWordFinish(boolean isSuccess);
    }

    interface RequiredPresenterOps {

        void loadWordFinish(Response<WordResponse> response);
    }
}
