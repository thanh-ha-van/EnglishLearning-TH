package thanh.ha.english.activities.examActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;
import thanh.ha.english.activities.resultTestActivity.ResultTestActivity;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.customviews.MarginAdapter;
import thanh.ha.english.customviews.NonSwipeableViewPager;
import thanh.ha.english.fragments.testFragment.TestFragment;
import thanh.ha.english.models.TestTrans;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class ExamActivity extends AppCompatActivity
        implements TestFragment.UserAnswerListener, ExamInterface.RequiredViewOps {

    private List<Fragment> fragments = new ArrayList<>();
    private MarginAdapter adapter;
    public int currentPosition = 0;

    @BindView(R.id.view_main)
    View viewMain;
    @BindView(R.id.vp_mini_test)
    public NonSwipeableViewPager viewPager;


    @BindString(R.string.title_master_a_part_words)
    String titleMasterWord;
    @BindString(R.string.title_master_a_part_words_1)
    String titleMasterAPartWords1;
    @BindString(R.string.title_master_a_part_words_2)
    String titleMasterAPartWords2;
    @BindString(R.string.title_master_a_part_words_3)
    String titleMasterAPartWords3;
    @BindString(R.string.title_master_all_word)
    String titleMasterAllWord;

    @BindString(R.string.msg_detect_challenge_word)
    String msgDetectChallengeWord;

    private ExamPresenter mPresenter;
    private int typeTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        typeTest = getIntent().getIntExtra(Constants.KEY_TYPE_TEST, Constants.TypeQuestPage.PAGE_TEST_WORD);
        ButterKnife.bind(this);
        mPresenter = new ExamPresenter(this, this);
        initList();
        setupViewPager();
    }

    private void initList() {
        List<TestTrans> miniTests = mPresenter.loadTestData(typeTest);
        for (int i = 0; i < miniTests.size(); i++) {
            TestFragment testFragment = new TestFragment();
            testFragment.setUserAnswerListener(this);
            testFragment.setData(miniTests.get(i), false, 1);
            fragments.add(testFragment);
        }
    }

    @OnClick(R.id.tv_unknown)
    void onClickViewUnKnow() {
        ((TestFragment) fragments.get(viewPager.getCurrentItem())).onClickUnKnow();
    }


    private void setupViewPager() {
        viewMain.setVisibility(View.VISIBLE);
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
        startTimer();
    }

    private void startTimer() {
        if (currentPosition < adapter.getCount()) {
            TestFragment fr = (TestFragment) adapter.getItem(currentPosition);
            fr.startCountDown();
        }
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
                        mPresenter.calResultTest();
                    }
                }
            }, 700);
        }
    }

    @Override
    public void showViewResultTest(int mWordMaster, int mWordChallenge) {
        String title;
        String message = "";
        int mWordOfDay = Globals.getIns().getConfig().getmWordOfDay();
        if (mWordChallenge == 0) {
            title = String.format(Locale.US, titleMasterAllWord, mWordMaster);
        } else if (mWordChallenge < mWordOfDay / 2) {
            title = String.format(Locale.US, titleMasterAPartWords1, mWordMaster, mWordMaster + mWordChallenge);
            message = String.format(Locale.US, msgDetectChallengeWord, mWordChallenge);
        } else {
            title = String.format(Locale.US, titleMasterWord, mWordMaster);
            message = String.format(Locale.US, msgDetectChallengeWord, mWordChallenge);
        }
        Intent intent = new Intent(this, ResultTestActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        intent.putExtra(Constants.KEY_ACTIVITY_NEXT, ResultTestActivity.NEXT_ACTION);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    @Override
    public void showViewContinueTest(boolean isFinal, int mWordContinue, int mWordChallenge) {
        String title;
        if (isFinal) {
            title = String.format(Locale.US, titleMasterAPartWords2, Constants.LIMIT_LEARN_A_PAGE);
        } else {
            title = String.format(Locale.US, titleMasterAPartWords3, mWordContinue);
        }
        String message = String.format(Locale.US, msgDetectChallengeWord, mWordChallenge);
        Intent intent = new Intent(this, ResultTestActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        intent.putExtra(Constants.KEY_ACTIVITY_NEXT, ResultTestActivity.EXAM_ACTIVITY);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
