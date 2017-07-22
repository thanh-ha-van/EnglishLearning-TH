package vn.magik.hot8.activities.introActivity;


import retrofit.Response;
import vn.magik.hot8.models.response.WordResponse;

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
