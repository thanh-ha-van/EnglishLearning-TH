package thanh.ha.english.fragments.questFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;
import thanh.ha.english.activities.choiceActivity.ChoiceWordActivity;
import thanh.ha.english.activities.otherActivity.PaymentActivity;
import thanh.ha.english.activities.practiceActivity.PracticeActivity;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.customviews.HandleAnimationView;
import thanh.ha.english.utils.Utils;


/**
 * Created by HaVan on 5/23/2017.
 */

public class QuestFragment extends Fragment implements QuestInterface.RequiredViewOps {

    @BindView(R.id.view_practive_word)
    View viewPractiveWord;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.view_negative)
    View viewNegative;
    @BindView(R.id.tv_positive)
    TextView tvPositive;
    @BindView(R.id.view_finish_today)
    View viewFinishToday;
    @BindView(R.id.view_buy_vip)
    View viewBuyVip;
    @BindView(R.id.tv_buy_vip_1)
    TextView tvBuyVip1;
    @BindView(R.id.tv_buy_vip_2)
    TextView tvBuyVip2;
    @BindView(R.id.tv_buy_vip_3)
    TextView tvBuyVip3;

    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.view_count_down)
    View viewCountDown;


    @BindString(R.string.format_word_to_learn)
    String formatWordToLearn;
    @BindString(R.string.title_master_all_word)
    String formatWordToLearned;
    @BindString(R.string.msg_detect_challenge_word)
    String formatWordToChallenge;
    @BindString(R.string.choose_word_for_tomorrow)
    String chooseWordForTomorrow;
    @BindString(R.string.choose_word_for_to_day)
    String chooseWordForToDay;
    @BindString(R.string.word_is_ready_to_repeat)
    String wordIsReadyToRepeat;
    @BindString(R.string.format_more_practice_word)
    String formatMorePracticeWord;

    private QuestPresenter mPresenter;
    private int statusPage = 0;
    private HandleAnimationView animationView;
    private OnClickBuyVip onClickBuyVip;
    private boolean isChoiceWordToday;

    private Handler handler;
    private boolean isRunCountDown = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest, container, false);
        ButterKnife.bind(this, view);
        animationView = new HandleAnimationView();
        mPresenter = new QuestPresenter(getContext(), this);
        handler = new Handler();
        mPresenter.getFlowUser();
        return view;
    }

    public void setOnClickBuyVip(OnClickBuyVip onClickBuyVip) {
        this.onClickBuyVip = onClickBuyVip;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getFlowUser();
    }

    @OnClick({R.id.tv_buy_vip_1, R.id.tv_buy_vip_2, R.id.tv_buy_vip_3})
    void onClickBuyVIP(View view) {
        String buyVip = Constants.PackageVip.BUY_1_MONTH;
        switch (view.getId()) {
            case R.id.tv_buy_vip_1:
                buyVip = Constants.PackageVip.BUY_1_MONTH;
                break;
            case R.id.tv_buy_vip_2:
                buyVip = Constants.PackageVip.BUY_6_MONTH;
                break;
            case R.id.tv_buy_vip_3:
                buyVip = Constants.PackageVip.BUY_FOREVER;
                break;
        }
        onClickBuyVip.onBuyVip(buyVip);
    }

    @OnClick(R.id.tv_negative)
    public void onClickViewNegative() {
        animationView.animationFromXToY(viewPractiveWord, viewFinishToday);
    }

    @OnClick(R.id.tv_positive)
    public void onClickViewPositive() {
        switch (statusPage) {
            case Constants.TypeQuestPage.PAGE_LEARN:
                startPracticeActivity();
                break;
            case Constants.TypeQuestPage.PAGE_CHALLENGE:
            case Constants.TypeQuestPage.PAGE_MUST_REVIEW:
                startExamActivity(statusPage);
                break;
            case Constants.TypeQuestPage.PAGE_FINISH_TODAY:
                Globals.getIns().getConfig().inCreaseTimeReview();
                startExamActivity(statusPage);
                break;
            case Constants.TypeQuestPage.PAGE_CHOICE_WORD:
                startChoiceActivity();
                break;
        }
    }

    @OnClick(R.id.tv_more_practice)
    public void onClickMorePractice() {
        animationView.animationFromXToY(viewFinishToday, viewPractiveWord);
    }

    private void startPracticeActivity() {
        Intent intent = new Intent(getActivity(), PracticeActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void startChoiceActivity() {
        Intent intent = new Intent(getActivity(), ChoiceWordActivity.class);
        intent.putExtra(Constants.KEY_CHOICE_WORD, isChoiceWordToday);
        intent.putExtra(Constants.KEY_START_FROM_MAIN, true);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void startExamActivity(int typePractice) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(Constants.KEY_TYPE_TEST, typePractice);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void showViewLearn(int mWordToLearn) {
        viewBuyVip.setVisibility(View.GONE);
        viewPractiveWord.setVisibility(View.VISIBLE);
        statusPage = Constants.TypeQuestPage.PAGE_LEARN;
        tvMessage.setText(String.format(Locale.US, formatWordToLearn, mWordToLearn));
    }


    @Override
    public void showViewChallengeWords(int mWordChallenge) {
        viewBuyVip.setVisibility(View.GONE);
        viewPractiveWord.setVisibility(View.VISIBLE);
        statusPage = Constants.TypeQuestPage.PAGE_CHALLENGE;
        tvMessage.setText(String.format(Locale.US, formatWordToLearned, mWordChallenge));
    }

    @Override
    public void showViewMustReviewWords(int mWordReview) {
        viewBuyVip.setVisibility(View.GONE);
        viewPractiveWord.setVisibility(View.VISIBLE);
        statusPage = Constants.TypeQuestPage.PAGE_MUST_REVIEW;
        tvMessage.setText(String.format(Locale.US, wordIsReadyToRepeat, mWordReview));
    }

    @Override
    public void showViewChoiceWord(boolean isChoiceWordToday) {
        viewBuyVip.setVisibility(View.GONE);
        viewPractiveWord.setVisibility(View.VISIBLE);
        this.isChoiceWordToday = isChoiceWordToday;
        if (isChoiceWordToday) {
            tvMessage.setText(String.format(Locale.US, chooseWordForToDay, Globals.getIns().getConfig().getmWordOfDay()));
        } else {
            tvMessage.setText(String.format(Locale.US, chooseWordForTomorrow, Globals.getIns().getConfig().getmWordOfDay()));
        }
        statusPage = Constants.TypeQuestPage.PAGE_CHOICE_WORD;
    }

    @Override
    public void showViewFinishToday(int mWordLearning) {
        statusPage = Constants.TypeQuestPage.PAGE_FINISH_TODAY;

        viewNegative.setVisibility(View.VISIBLE);
        tvMessage.setText(String.format(Locale.US, formatMorePracticeWord, mWordLearning));
        viewFinishToday.setVisibility(View.VISIBLE);
        viewPractiveWord.setVisibility(View.VISIBLE);
        viewBuyVip.setVisibility(View.GONE);
    }

    @Override
    public void showLayoutBuyVip() {
        viewBuyVip.setVisibility(View.VISIBLE);
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        if (isRunCountDown) handler.removeCallbacks(runnable);
        super.onDestroy();

    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int time = Utils.getTimeNextDay(Utils.getCurrentTimestamp()) - Utils.getCurrentTimestamp();
            if (time > 0) {
                isRunCountDown = true;
                tvCountDown.setText(Utils.getFormatTime(time));
                handler.postDelayed(this, 1000);
            } else {
                viewCountDown.setVisibility(View.GONE);
            }

        }
    };

    public interface OnClickBuyVip {
        void onBuyVip(String packageVip);
    }


}