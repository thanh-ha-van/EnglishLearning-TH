package thanh.ha.english.activities.introActivity;

import android.content.Context;

import java.util.List;

import retrofit.Response;
import thanh.ha.english.models.Word;
import thanh.ha.english.models.response.WordResponse;

/**
 * Created by HaVan on 5/22/2017.
 */

class IntroPresenter implements IntroInterface.RequiredPresenterOps {
    private IntroInterface.RequiredViewOps mView;
    private IntroModel mModel;

    IntroPresenter(Context context, IntroInterface.RequiredViewOps mView) {
        this.mView = mView;
        mModel = new IntroModel(context, this);
    }

    void saveLevelConfig() {
        mModel.saveLevelConfig();
    }

    void loadAllWordFromServer() {
        mModel.loadAllWordFromServer();
    }

    @Override
    public void loadWordFinish(Response<WordResponse> response) {
        if (response != null && response.body() != null) {
            if (response.body().getErrorCode() == 0) {
                List<Word> words = response.body().getWords();
                mModel.saveWordToDatabase(words);
                mView.getWordFinish(false);
            }
        } else {
            mView.getWordFinish(false);
        }
    }
}
