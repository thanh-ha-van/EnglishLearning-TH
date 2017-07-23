package thanh.ha.english.activities.practiceActivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import thanh.ha.english.fragments.completeWordFragment.CompleteWordFragment;
import thanh.ha.english.fragments.completeWordFragment.CompleteWordInterface;
import thanh.ha.english.fragments.learnFragment.LearnFragment;
import thanh.ha.english.fragments.testFragment.TestFragment;
import thanh.ha.english.models.TestComp;
import thanh.ha.english.models.TestTrans;
import thanh.ha.english.models.Word;

public class PracticeActivity extends AppCompatActivity implements PracticeInterface.ViewOpt,
        LearnFragment.OnClickNext, TestFragment.UserAnswerListener, CompleteWordInterface.UserAnswerListener {

    @BindView(R.id.view_practice)
    View viewPractice;
    @BindView(R.id.vp_practice)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.view_result_test)
    View viewResultTest;

    private Handler handler;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentPosition = 0;
    private PracticePresenter mPresenter;


    @BindString(R.string.title_master_a_part_words)
    String titleMasterWord;
    @BindString(R.string.title_master_all_word)
    String titleMasterAllWord;

    @BindString(R.string.msg_detect_challenge_word)
    String msgDetectChallengeWord;
    @BindString(R.string.confirm_practice)
    String confirmPractice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        ButterKnife.bind(this);
        mPresenter = new PracticePresenter(this, this);
        handler = new Handler();
        mPresenter.getWordsToLearn();
    }


    @Override
    public void updateViewWithData(List<Word> words, List<TestTrans> testWordToMean, List<TestTrans> testMeanToWord, List<TestComp> testCompWords1, List<TestComp> testCompWords2) {
        fragments.clear();
        if (words != null) {
            LearnFragment learnFragment = new LearnFragment();
            learnFragment.setDataTest(this, words);
            fragments.add(learnFragment);
        }
        for (int i = 0; i < testWordToMean.size(); i++) {
            TestFragment testFragment = new TestFragment();
            testFragment.setData(testWordToMean.get(i), true, 3);
            testFragment.setUserAnswerListener(this);
            fragments.add(testFragment);
        }
        for (int i = 0; i < testMeanToWord.size(); i++) {
            TestFragment testFragment = new TestFragment();
            testFragment.setData(testMeanToWord.get(i), true, 3);
            testFragment.setUserAnswerListener(this);
            fragments.add(testFragment);
        }
        for (int i = 0; i < testCompWords1.size(); i++) {
            CompleteWordFragment completeWordFragment = new CompleteWordFragment();
            completeWordFragment.setData(testCompWords1.get(i), this);
            fragments.add(completeWordFragment);
        }
        for (int i = 0; i < testCompWords2.size(); i++) {
            CompleteWordFragment completeWordFragment = new CompleteWordFragment();
            completeWordFragment.setData(testCompWords2.get(i), this);
            fragments.add(completeWordFragment);
        }
        setupViewPager();
    }


    @Override
    public void showViewResultTest(int mWordMaster, int mWordChallenge) {
        int mWordOfDay = Globals.getIns().getConfig().getmWordOfDay();
        String title;
        String message;
        int nextActivity = ResultTestActivity.EXAM_ACTIVITY;
        if (mWordChallenge < mWordOfDay / 2) {
            title = String.format(Locale.US, titleMasterAllWord, mWordMaster);
            message = confirmPractice;
        } else {
            title = String.format(Locale.US, titleMasterWord, mWordMaster);
            message = String.format(Locale.US, msgDetectChallengeWord, mWordChallenge);
            nextActivity = ResultTestActivity.NEXT_ACTION;
        }
        Intent intent = new Intent(this, ResultTestActivity.class);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        intent.putExtra(Constants.KEY_ACTIVITY_NEXT, nextActivity);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    private void setupViewPager() {
        MarginAdapter adapter = new MarginAdapter(getSupportFragmentManager(), fragments);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onNextPage() {
        currentPosition = 1;
        if (currentPosition < fragments.size()) {
            viewPager.setCurrentItem(currentPosition);
        }
    }

    @Override
    public void onUserAnswer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (++currentPosition < fragments.size()) {
                    viewPager.setCurrentItem(currentPosition);
                } else {
                    currentPosition = 0;
                    mPresenter.resetDataTest();
                }
            }
        }, 700);
    }

    @Override
    public void onCompleteAnswer() {
        onUserAnswer();
    }

    @OnClick(R.id.img_back)
    public void backClicked() {
        onBackPressed();
    }
}
