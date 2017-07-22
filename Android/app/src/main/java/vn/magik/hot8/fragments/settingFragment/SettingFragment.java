package vn.magik.hot8.fragments.settingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.changeConfigActivity.ChangeConfigActivity;
import vn.magik.hot8.activities.contactDevActivity.ContactDevActivity;
import vn.magik.hot8.activities.termOfUseActivity.TermOfUseActivity;
import vn.magik.hot8.activities.wordActivity.WordActivity;
import vn.magik.hot8.activities.wordPerDayConfigActivity.WordPerDayConfigActivity;
import vn.magik.hot8.constants.Globals;


/**
 * Created by HaVan on 5/23/2017.
 */

public class SettingFragment extends Fragment {


    @BindView(R.id.num_word_of_day_config)
    TextView tvConfigNum;

    @BindView(R.id.switch_show_notification)
    Switch notifySwitch;
    @BindView(R.id.tv_day_left_of_trial)
    TextView tvTrialNum;
    @BindView(R.id.tv_the_trial)
    TextView tvTrialText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        initView();
        reFreshUI();
        return view;
    }

    public void initView() {
        notifySwitch.setChecked(Globals.getIns().getConfig().isNotify());
    }

    public void reFreshUI() {
        tvConfigNum.setText(String.valueOf(Globals.getIns().getConfig().getmWordOfDay()));
        getDayTrial();
    }

    @OnClick(R.id.word_of_day_config)
    public void startWordPerDay() {
        Intent intent = new Intent(this.getContext(), WordPerDayConfigActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @OnClick(R.id.all_word_view)
    public void startWordActivity() {
        Intent intent = new Intent(getActivity(), WordActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @OnClick(R.id.hobby_config)
    public void starConfigActivity() {
        Intent intent = new Intent(getActivity(), ChangeConfigActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @OnClick(R.id.contact_dev)
    public void startContactActivity() {
        Intent intent = new Intent(getActivity(), ContactDevActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @OnClick(R.id.term_of_use)
    public void startTermOfUse() {
        Intent intent = new Intent(getActivity(), TermOfUseActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @OnClick(R.id.switch_show_notification)
    public void changeNotificationConfig() {
        Globals.getIns().getConfig().setNotify(notifySwitch.isChecked());
    }

    @Override
    public void onResume() {
        super.onResume();
        reFreshUI();
    }

    public void getDayTrial() {
        long activeTime = Globals.getIns().getConfig().getCountDate();
        long currentTime = new Date().getTime() / 1000;
        long dayGone = (currentTime - activeTime) / 86400;
        if (dayGone >= 7) {
            tvTrialNum.setText("");
            tvTrialText.setText(getResources().getString(R.string.the_trial_gone));
        } else
            tvTrialNum.setText(String.valueOf(7 - dayGone) + " " + getResources().getString(R.string.day));
    }
}