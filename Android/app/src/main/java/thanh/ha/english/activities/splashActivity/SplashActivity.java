package thanh.ha.english.activities.splashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.activities.createPlanActivity.CreatePlanActivity;
import thanh.ha.english.activities.languageActivity.LanguageActivity;
import thanh.ha.english.activities.mainActivity.MainActivity;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.models.Language;

public class SplashActivity extends AppCompatActivity implements SplashInterface.RequiredViewOps {
    private static final int LOAD_SERVER_SUCCESS = 1;
    private static final int LOAD_SERVER_ERROR = 2;
    private SplashPresenter mPresenter;
    private Handler handler = new Handler();
    private ArrayList<Language> languages = new ArrayList<>();
    private boolean isFirstRun = false;
    private int statusLoadLanguage;

    @BindView(R.id.tv_network_error)
    TextView tvNetworkError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter = new SplashPresenter(SplashActivity.this, this);
        isFirstRun = mPresenter.isFirstRun();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFirstRun) {
                if (statusLoadLanguage == LOAD_SERVER_SUCCESS) {
                    startLanguageActivity();
                } else if (statusLoadLanguage == LOAD_SERVER_ERROR) {
                    tvNetworkError.setText(getResources().getString(R.string.network_error));
                } else {
                    handler.postDelayed(this, 100);
                }
            } else {
                if (mPresenter.getCountWordSelected() == 0) {
                    startCreatePlanActivity();
                } else {
                    startMainActivity();
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkInAppServer();
        handler.postDelayed(runnable, 1000);
    }

    private void startLanguageActivity() {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra(Constants.KEY_LANGUAGE, languages);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    private void startCreatePlanActivity() {
        Intent intent = new Intent(this, CreatePlanActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    @Override
    public void getLanguageFinish(ArrayList<Language> languages) {
        this.languages = languages;
        statusLoadLanguage = LOAD_SERVER_SUCCESS;
    }

    @Override
    public void getLanguageFail() {
        statusLoadLanguage = LOAD_SERVER_ERROR;
    }
}
