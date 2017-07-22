package vn.magik.hot8.fragments.introfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class IntroFragment6 extends IntroFragment {
    @BindView(R.id.view_gift_1)
    View viewGift1;
    @BindView(R.id.view_gift_2)
    View viewGift2;
    @BindView(R.id.view_gift_3)
    View viewGift3;
    @BindView(R.id.view_contain_gift)
    View viewContainGift;
    @BindView(R.id.tv_active)
    TextView tvActive;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_6, container, false);
        ButterKnife.bind(this, view);
        handler = new Handler();
        return view;
    }

    @Override
    public void animationViewPage() {
        animationViewContainGift();
        animationButtonPayment();
    }


    @Override
    public void disableAnimationViewPage() {
        handler.removeCallbacks(animationBtnPayment);
        viewContainGift.clearAnimation();
        viewGift1.clearAnimation();
        viewGift2.clearAnimation();
        viewGift3.clearAnimation();
        viewContainGift.setVisibility(View.GONE);
        viewGift1.setVisibility(View.GONE);
        viewGift2.setVisibility(View.GONE);
        viewGift3.setVisibility(View.GONE);
    }

    private void animationButtonPayment() {
        handler.post(animationBtnPayment);
    }

    Runnable animationBtnPayment = new Runnable() {
        @Override
        public void run() {
            if (getContext() != null) {
                Animation mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.swinging);
                tvActive.startAnimation(mAnimation);
                handler.postDelayed(this, 3000);
            }
        }
    };

    private void animationViewContainGift() {
        viewContainGift.setVisibility(View.VISIBLE);
        Animation zoom = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
        zoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationGift();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewContainGift.startAnimation(zoom);
    }

    private void animationGift() {
        viewGift1.setVisibility(View.VISIBLE);
        TranslateAnimation animateGift = new TranslateAnimation(0, 0, viewGift1.getHeight(), 0);
        animateGift.setDuration(400);
        animateGift.setFillAfter(true);
        viewGift1.startAnimation(animateGift);
        animateGift.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationGitf2();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animationGitf2() {
        viewGift2.setVisibility(View.VISIBLE);
        TranslateAnimation animateGift2 = new TranslateAnimation(0, 0, viewGift2.getHeight(), 0);
        animateGift2.setDuration(500);
        animateGift2.setFillAfter(true);
        viewGift2.startAnimation(animateGift2);


        viewGift3.setVisibility(View.VISIBLE);
        Animation an = new RotateAnimation(100.0f, 360.0f, viewGift3.getPivotX() - 150, viewGift3.getPivotY() - 100);
        an.setDuration(500);
        an.setFillAfter(true);
        viewGift3.startAnimation(an);
    }


}
