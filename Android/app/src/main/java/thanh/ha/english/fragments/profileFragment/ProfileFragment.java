package thanh.ha.english.fragments.profileFragment;

/**
 * Created by HaVan on 5/23/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.adapters.WeekAdapter;
import thanh.ha.english.models.Day;


public class ProfileFragment extends Fragment implements ProfileInterface.RequiredViewOps {
    @BindView(R.id.tv_learning_word)
    TextView tvLearningWord;
    @BindView(R.id.tv_challenge_word)
    TextView tvChallengeWord;
    @BindView(R.id.view_challenge)
    View viewChallenge;
    @BindView(R.id.tv_challenge)
    TextView tvChallenge;

    @BindView(R.id.rv_date_history)
    RecyclerView rvDateHistory;
    @BindView(R.id.tv_word_per_week)
    TextView tvWordPerWeek;
    @BindView(R.id.tv_word_per_month)
    TextView tvWordPerMonth;
    @BindView(R.id.donut_progress_level)
    DonutProgress progressLevel;
    @BindView(R.id.arc_progress_speed)
    ArcProgress progressSpeed;
    @BindView(R.id.tv_level_content)
    TextView tvLevelContent;
    @BindView(R.id.tv_speed_content)
    TextView tvSpeedContent;
    @BindString(R.string.msg_detect_challenge)
    String msgDetectChallenge;

    private ProfilePresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new ProfilePresenter(getActivity(), this);
        initView();
        refreshUI();
        return view;
    }

    private void initView() {
        List<Day> daysHistory = mPresenter.getDateHistory();
        WeekAdapter weekAdapter = new WeekAdapter(getContext(), daysHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDateHistory.setLayoutManager(layoutManager);
        rvDateHistory.setAdapter(weekAdapter);
    }

    private void refreshUI() {
        int mLearningWord = mPresenter.getCountLearningWord();
        int mChallengeWord = mPresenter.getCountChallengeWord();
        int mMasteredWordWeek = mPresenter.getCountMasteredWordWeek();
        if (mMasteredWordWeek > 0) {
            tvWordPerWeek.setText(String.valueOf(mMasteredWordWeek));
        }
        int mMasteredWordMonth = mPresenter.getCountMasteredWordMonth();
        if (mMasteredWordMonth > 0) {
            tvWordPerMonth.setText(String.valueOf(mMasteredWordMonth));
        }
        tvLearningWord.setText(String.valueOf(mLearningWord));
        tvLevelContent.setText(String.valueOf(mMasteredWordWeek) + " " + getResources().getString(R.string.word_has_learn));
        tvSpeedContent.setText(String.valueOf(mLearningWord) + "" + getResources().getString(R.string.speed_unit));
        progressLevel.setProgress(35); // ?
        if (mChallengeWord > 0) {
            tvChallengeWord.setText(String.format(Locale.US, msgDetectChallenge, mChallengeWord));
            viewChallenge.setVisibility(View.VISIBLE);
        } else {
            viewChallenge.setVisibility(View.GONE);
        }
        if (mLearningWord == 0)
            tvLevelContent.setText(getResources().getString(R.string.complete_quest));

    }

}