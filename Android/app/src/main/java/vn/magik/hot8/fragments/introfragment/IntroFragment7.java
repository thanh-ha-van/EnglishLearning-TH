package vn.magik.hot8.fragments.introfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.createPlanActivity.CreatePlanActivity;
import vn.magik.hot8.activities.testActivity.TestActivity;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class IntroFragment7 extends IntroFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_7, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void animationViewPage() {

    }

    @Override
    public void disableAnimationViewPage() {

    }

    @OnClick({R.id.tv_do_test, R.id.tv_skip_test})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_do_test:
                startTestActivity();
                break;
            case R.id.tv_skip_test:
                startMainActivity();
                break;
        }
    }

    void startTestActivity() {
        Intent intent = new Intent(getActivity(), TestActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        getActivity().finish();
    }

    void startMainActivity() {
        Intent intent = new Intent(getActivity(), CreatePlanActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        getActivity().finish();
    }
}
