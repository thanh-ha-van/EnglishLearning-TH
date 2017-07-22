package vn.magik.hot8.activities.testActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.createPlanActivity.CreatePlanActivity;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.customviews.MarginAdapter;
import vn.magik.hot8.customviews.NonSwipeableViewPager;
import vn.magik.hot8.fragments.testFragment.TestFragment;
import vn.magik.hot8.models.TestTrans;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class TestActivity extends AppCompatActivity
        implements TestFragment.UserAnswerListener, TestInterface.RequiredViewOps {

    private List<TestTrans> miniTests;
    private List<Fragment> fragments = new ArrayList<>();
    private MarginAdapter adapter;
    public int currentPosition = 0;

    @BindView(R.id.view_main)
    View viewMain;
    @BindView(R.id.vp_mini_test)
    public NonSwipeableViewPager viewPager;
    @BindView(R.id.pre_test_view)
    public LinearLayout preTestView;
    @BindView(R.id.after_test_view)
    public LinearLayout afterTestView;
    @BindView(R.id.tv_test_result)
    public TextView tvTestResult;
    @BindView(R.id.tv_suggest_level)
    public TextView tvSuggestLevel;
    @BindView(R.id.tv_start_after_test)
    public TextView tvStartMain;
    @BindView(R.id.tv_time_countdown)
    public TextView tvTimerPreTest;
    @BindView(R.id.tv_keep_select_level)
    public TextView tvKeepSelectLevel;

    @BindString(R.string.msg_test_result)
    String msgTestResult;
    @BindString(R.string.msg_suggest_level)
    String msgSuggestLevel;
    @BindString(R.string.keep_level_select)
    String keepLevelSelect;

    @BindArray(R.array.level_list)
    String[] arrLevel;


    private TestPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        mPresenter = new TestPresenter(this, this);
        initList();
        setupViewPager();
        preTestView.setVisibility(View.VISIBLE);
        startPreTestTimer();
    }

    public void makeMainView() {
        slideExit(preTestView, viewMain);
        viewMain.animate().setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startTimer();
            }
        });

    }

    public void makeAfterView() {
        int currentLevel = Globals.getIns().getConfig().getLevel();
        String msgKeepLevel = String.format(keepLevelSelect, getStringLevel(currentLevel));
        tvKeepSelectLevel.setText(msgKeepLevel);
        mPresenter.calSuggestLevel();
        slideExit(viewMain, afterTestView);
    }

    private void slideExit(final View currentView, final View comingView) {
        comingView.setTranslationX(comingView.getWidth());
        currentView.animate().translationXBy(-currentView.getWidth()).
                setDuration(500).
                setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        slideFromRightToLeft(comingView);
                        comingView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        currentView.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void slideFromRightToLeft(View v) {
        v.animate().translationX(0).setDuration(500).
                setInterpolator(new LinearInterpolator());
    }


    private void startPreTestTimer() {
        new CountDownTimer(4 * 1000, 100) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                tvTimerPreTest.setText(String.valueOf(seconds));
            }

            @Override
            public void onFinish() {
                makeMainView();
            }
        }.start();
    }

    private void initList() {
        miniTests = mPresenter.loadTestData();
        for (int i = 0; i < miniTests.size(); i++) {
            TestFragment testFragment = new TestFragment();
            testFragment.setUserAnswerListener(this);
            testFragment.setData(miniTests.get(i), false, 2);
            fragments.add(testFragment);
        }
    }

    private void setupViewPager() {
        adapter = new MarginAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
        viewPager.setScrollDurationFactor(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                startTimer();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void startTimer() {
        TestFragment fr = (TestFragment) adapter.getItem(currentPosition);
        fr.startCountDown();
    }

    @Override
    public void onUserAnswer() {
        final Handler handler = new Handler();
        if (currentPosition < adapter.getCount()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentPosition++;
                    if (currentPosition < fragments.size()) {
                        viewPager.setCurrentItem(currentPosition);
                    } else {
                        makeAfterView();
                    }
                }
            }, 700);
        }
    }

    @OnClick(R.id.tv_keep_select_level)
    public void keepLevelConfig() {
        mPresenter.changeLevelConfig(false);
        startActivityCreatePlan();
    }

    @OnClick(R.id.tv_start_after_test)
    public void changeLevelConfig() {
        mPresenter.changeLevelConfig(true);
        startActivityCreatePlan();
    }

    public void startActivityCreatePlan() {
        Intent intent = new Intent(this, CreatePlanActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    @Override
    public void suggestLevel(int score, int level) {
        tvTestResult.setText(String.format(Locale.US, msgTestResult, score));
        if (level == Globals.getIns().getConfig().getLevel()) {
            tvSuggestLevel.setText(R.string.msg_match_level);
            tvKeepSelectLevel.setVisibility(View.INVISIBLE);
        } else {
            tvKeepSelectLevel.setVisibility(View.VISIBLE);
            tvSuggestLevel.setText(String.format(msgSuggestLevel, getStringLevel(level)));
        }
    }

    public String getStringLevel(int level) {
        if (level < 0 || level > arrLevel.length) {
            return "";
        } else {
            return arrLevel[level];
        }
    }

}
