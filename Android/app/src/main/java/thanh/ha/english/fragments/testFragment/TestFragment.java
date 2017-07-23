package thanh.ha.english.fragments.testFragment;

/**
 * Created by HaVan on 5/25/2017.
 */

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;


import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.adapters.AnswerAdapter;
import thanh.ha.english.models.TestTrans;


public class TestFragment extends Fragment implements TestInterface.RequiredViewOps,
        AnswerAdapter.OnClickAnswer {
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.rv_answer)
    RecyclerView rvAnswer;
    @BindView(R.id.pg_timer_answer)
    public DonutProgress pgTimeAnswer;
    private boolean isPractice;
    private int numAnswer;

    private UserAnswerListener userAnswerListener;
    private boolean isStartCountdown = false, isAnswer = false, isInitView = false;
    private TestTrans miniTest;
    private CountDownTimer cdTimeAnswer;
    private TestPresenter mPresenter;
    private AnswerAdapter answerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new TestPresenter(getActivity(), this);
        initView();
        return view;
    }

    public void setData(TestTrans miniTest, boolean isPractice, int numAnswer) {
        this.miniTest = miniTest;
        this.isPractice = isPractice;
        this.numAnswer = numAnswer;
    }

    public void onClickUnKnow() {
        if (!isAnswer) {
            isAnswer = true;
            if (cdTimeAnswer != null) {
                cdTimeAnswer.cancel();
            }
            miniTest.setShowAnswer(true);
            answerAdapter.notifyDataSetChanged();
            userAnswerListener.onUserAnswer();
        }

    }

    public void initView() {
        mPresenter.randomAnswers(miniTest, numAnswer);
        tvWord.setText(miniTest.getQuestion());
        answerAdapter = new AnswerAdapter(miniTest, !miniTest.isShowWord(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvAnswer.setLayoutManager(layoutManager);
        rvAnswer.setAdapter(answerAdapter);
        isInitView = true;
        startCountDownTimer();
        if (isPractice) {
            pgTimeAnswer.setVisibility(View.GONE);
        }
    }

    private void setTimerColor() {
        switch (Math.round(pgTimeAnswer.getProgress())) {
            case 80:
                pgTimeAnswer.setFinishedStrokeColor(getResources().getColor(R.color.green));
                break;
            case 60:
                pgTimeAnswer.setFinishedStrokeColor(getResources().getColor(R.color.yellow));
                break;
            case 40:
                pgTimeAnswer.setFinishedStrokeColor(getResources().getColor(R.color.orange));
                break;
            case 20:
                pgTimeAnswer.setFinishedStrokeColor(getResources().getColor(R.color.red));
                break;
            default:
                break;
        }
    }

    public void startCountDown() {
        isStartCountdown = true;
        if (!isPractice) startCountDownTimer();
    }


    public void startCountDownTimer() {
        if (isStartCountdown && isInitView) {
            cdTimeAnswer = new CountDownTimer(5 * 1000, 50) {
                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    if (TestFragment.this.isAdded()) {
                        long seconds = leftTimeInMilliseconds / 1000;
                        long percent = leftTimeInMilliseconds / 50;
                        pgTimeAnswer.setProgress(percent);
                        pgTimeAnswer.setText(String.valueOf(seconds + 1));
                        setTimerColor();
                    }
                }

                @Override
                public void onFinish() {
                    if (TestFragment.this.isAdded()) {
                        pgTimeAnswer.setProgress(0);
                        pgTimeAnswer.setText("0");
                        pgTimeAnswer.setTextColor(getResources().getColor(R.color.red));
                        if (!isAnswer) {
                            isAnswer = true;
                            userAnswerListener.onUserAnswer();
                        }
                    }
                }
            };
            cdTimeAnswer.start();
        }
    }

    @Override
    public void onClick() {
        if (!isAnswer) {
            isAnswer = true;
            if (cdTimeAnswer != null) {
                cdTimeAnswer.cancel();
            }
            userAnswerListener.onUserAnswer();
        }
    }

    public interface UserAnswerListener {
        void onUserAnswer();
    }

    public void setUserAnswerListener(UserAnswerListener userAnswerListener) {
        this.userAnswerListener = userAnswerListener;
    }

}

