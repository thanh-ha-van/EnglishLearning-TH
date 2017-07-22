package vn.magik.hot8.activities.languageActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.introActivity.IntroActivity;
import vn.magik.hot8.adapters.LanguageAdapter;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Language;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

public class LanguageActivity extends AppCompatActivity
        implements LanguageInterface.RequiredViewOps,
        LanguageAdapter.ItemClickListener {

    @BindView(R.id.rv_language)
    public RecyclerView rvLanguage;
    public LanguageAdapter languageAdapter;
    private LinearLayoutManager layoutManager;
    private LanguagePresenter mPresenter;
    private ArrayList<Language> languages = new ArrayList<>();
    private SharedPreferences sPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
        getDataFromIntent();
        initView();
        mPresenter = new LanguagePresenter(this);
    }

    @SuppressWarnings("unchecked")
    private void getDataFromIntent() {

        languages = (ArrayList<Language>) getIntent().getSerializableExtra(Constants.KEY_LANGUAGE);

    }

    private void initView() {

        languageAdapter = new LanguageAdapter(this, languages);
        languageAdapter.setClickListener(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvLanguage.setLayoutManager(layoutManager);
        rvLanguage.setAdapter(languageAdapter);
    }


    @Override
    public void onItemClick(int position) {
        long activationTime  =  new Date().getTime()/1000;
        String chosenLanguage = languages.get(position).getLanguage_code();
        Globals.getIns().getConfig().setCurrentLanguageCode(chosenLanguage);
        sPref = getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(Constants.KEY_LANGUAGE, chosenLanguage);
        editor.putLong(Constants.KEY_ACTIVE_TIME, activationTime);
        editor.apply();
        startIntroActivity();
    }

    public void startIntroActivity() {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }
}
