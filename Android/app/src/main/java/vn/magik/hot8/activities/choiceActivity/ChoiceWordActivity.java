package vn.magik.hot8.activities.choiceActivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.TextView;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.changeConfigActivity.ChangeConfigActivity;
import vn.magik.hot8.activities.mainActivity.MainActivity;
import vn.magik.hot8.adapters.ChoiceWordAdapter;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Word;

public class ChoiceWordActivity extends AppCompatActivity implements ChoiceWordInterface.ViewOpt {
    private ChoiceWordAdapter choiceWordAdapter;
    @BindView(R.id.rv_choice_word)
    RecyclerView rvChoiceWords;
    @BindView(R.id.tv_change_config)
    TextView tvChangeConfig;
    @BindView(R.id.tv_current_level)
    TextView tvCurrentLevel;

    @BindString(R.string.format_change_level)
    String formatChangeLevel;
    @BindArray(R.array.level_list)
    String[] levels;
    @BindArray(R.array.user_favorite_list)
    String[] userFavoriteList;


    private ChoiceWordPresenter mPresenter;
    private boolean isChanging, startFromMain, isBeginStartMain = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_word);
        boolean choiceWordToday = getIntent().getBooleanExtra(Constants.KEY_CHOICE_WORD, true);
        startFromMain = getIntent().getBooleanExtra(Constants.KEY_START_FROM_MAIN, false);
        mPresenter = new ChoiceWordPresenter(this, choiceWordToday, this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isChanging = false;
        int userLevel = Globals.getIns().getConfig().getLevel();
        tvCurrentLevel.setText(String.format(formatChangeLevel, levels[userLevel], mPresenter.getCountSelectTopic()));
    }

    private void initView() {
        ButterKnife.bind(this);
        List<Word> words = mPresenter.getAllWord();
        choiceWordAdapter = new ChoiceWordAdapter(this, words, onclickView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvChoiceWords.setLayoutManager(layoutManager);
        rvChoiceWords.setAdapter(choiceWordAdapter);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rvChoiceWords);
    }

    @OnClick(R.id.tv_change_config)
    public void changeConfig() {
        if (!isChanging) {
            isChanging = true;
            Intent intent = new Intent(this, ChangeConfigActivity.class);
            startActivity(intent);
        }
    }

    ChoiceWordAdapter.OnclickView onclickView = new ChoiceWordAdapter.OnclickView() {
        @Override
        public void onClick(int position) {
            if (!isBeginStartMain) {
                mPresenter.selectWord(position);
            }
        }
    };

    @Override
    public void updateView(int mCountSelected, int position) {
        choiceWordAdapter.setmCountSelected(mCountSelected);
        choiceWordAdapter.notifyDataSetChanged();
        if (mCountSelected < Globals.getIns().getConfig().getmWordOfDay())
            rvChoiceWords.smoothScrollToPosition(position);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void handleFinishChoiceWord() {
        if (!isBeginStartMain) {
            isBeginStartMain = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (startFromMain) {
                        onBackPressed();
                    } else {
                        Intent intent = new Intent(ChoiceWordActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 100);
        }
    }
}
