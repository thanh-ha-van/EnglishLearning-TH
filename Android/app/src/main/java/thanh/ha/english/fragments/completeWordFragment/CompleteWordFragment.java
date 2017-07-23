package thanh.ha.english.fragments.completeWordFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;
import thanh.ha.english.adapters.KeyAdapter;
import thanh.ha.english.models.TestComp;


public class CompleteWordFragment extends Fragment implements KeyAdapter.ItemClickListener,
        CompleteWordInterface.ViewOpt {

    @BindView(R.id.tv_word)
    public TextView tvWord;
    @BindView(R.id.iv_play_audio)
    ImageView ivPlayAudio;
    @BindView(R.id.tv_your_answer)
    public TextView tvYourAnswer;
    @BindView(R.id.tv_unknown)
    public TextView tvUnKnown;
    @BindView(R.id.rv_key)
    RecyclerView recyclerView;

    private CompleteWordPresenter mPresenter;
    private TestComp testComp;
    private CompleteWordInterface.UserAnswerListener userAnswerListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_practice_word, container, false);
        ButterKnife.bind(this, rootView);
        mPresenter = new CompleteWordPresenter(this);
        initView();
        return rootView;
    }

    public void setData(TestComp testComp, CompleteWordInterface.UserAnswerListener completeAnswerListener) {
        this.testComp = testComp;
        this.userAnswerListener = completeAnswerListener;
    }

    private void initView() {
        String data = mPresenter.createData(testComp.getWord());
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 4));
        KeyAdapter keyAdapter = new KeyAdapter(this.getContext(), data);
        keyAdapter.setClickListener(this);
        recyclerView.setAdapter(keyAdapter);
        if (testComp.isShowMean()) {
            tvWord.setText(testComp.getWord().getMean());
            tvWord.setVisibility(View.VISIBLE);
            ivPlayAudio.setVisibility(View.GONE);
        } else {
            tvWord.setVisibility(View.GONE);
            ivPlayAudio.setVisibility(View.VISIBLE);
            clickPlayAudio();
        }
    }

    @OnClick(R.id.iv_play_audio)
    void clickPlayAudio() {
        //todo handle play audio
    }


    @Override
    public boolean onItemClick(View view, int position) {
        return mPresenter.checkAnswer(position);
    }


    @OnClick(R.id.tv_unknown)
    public void onUnKnowClicked() {
        tvYourAnswer.setText(testComp.getWord().getContent());
        tvYourAnswer.setTextColor(getResources().getColor(R.color.green));
        testComp.getWord().setCorrectComplete(false);
        userAnswerListener.onCompleteAnswer();
    }

    @Override
    public void checkAnswerFinish() {
        tvUnKnown.setEnabled(false);
        tvYourAnswer.setText(testComp.getWord().getContent());
        userAnswerListener.onCompleteAnswer();
    }

    @Override
    public void checkAnswer(String currentAnswer, boolean isCorrect) {
        tvYourAnswer.setText(currentAnswer);
        tvYourAnswer.setTextColor(getResources().getColor(R.color.green));

    }
}

