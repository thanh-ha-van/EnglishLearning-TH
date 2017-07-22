package vn.magik.hot8.fragments.introfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class IntroFragment1 extends IntroFragment {
    @BindView(R.id.iv_character)
    View viewCharacter;
    @BindView(R.id.iv_hand_character)
    View viewHandCharacter;
    @BindView(R.id.view_message_1)
    View viewMessage1;
    @BindView(R.id.view_message_2)
    View viewMessage2;
    @BindView(R.id.view_message_3)
    View viewMessage3;
    @BindView(R.id.tv_word_challenge)
    TextView tvMessage1;
    @BindView(R.id.tv_message_2)
    TextView tvMessage2;
    @BindView(R.id.tv_message_3)
    TextView tvMessage3;
    @BindView(R.id.tv_message_intro)
    TextView tvMessageIntro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    public void setupUI() {
        switch (Globals.getIns().getConfig().getLevel()) {
            case Constants.LevelType.BEGINNER:
            case Constants.LevelType.EASY:
                tvMessageIntro.setText(R.string.msg_intro_1_0);
                tvMessage1.setText(R.string.easy);
                tvMessage2.setText(R.string.curious);
                tvMessage3.setText(R.string.smart);
                break;
            case Constants.LevelType.MEDIUM:
                tvMessageIntro.setText(R.string.msg_intro_1_1);
                tvMessage1.setText(R.string.clever);
                tvMessage2.setText(R.string.reliable);
                tvMessage3.setText(R.string.passion);
                break;
            case Constants.LevelType.HARD:
            case Constants.LevelType.SUPER:
                tvMessageIntro.setText(R.string.msg_intro_1_2);
                tvMessage1.setText(R.string.clever);
                tvMessage2.setText(R.string.reliable);
                tvMessage3.setText(R.string.passion);
                break;
        }
    }


    @Override
    public void animationViewPage() {
        if (viewCharacter != null && viewHandCharacter != null) {
            viewHandCharacter.clearAnimation();
            viewHandCharacter.setVisibility(View.GONE);
            animationCharacter();
            setupUI();
        }
    }


    @Override
    public void disableAnimationViewPage() {
        if (viewCharacter != null && viewHandCharacter != null) {
            viewCharacter.clearAnimation();
            viewHandCharacter.clearAnimation();
            viewMessage1.clearAnimation();
            viewMessage2.clearAnimation();
            viewMessage3.clearAnimation();
            viewCharacter.setVisibility(View.GONE);
            viewHandCharacter.setVisibility(View.GONE);
            viewMessage1.setAlpha(0);
            viewMessage2.setAlpha(0);
            viewMessage3.setAlpha(0);
        }
    }

    private void animationCharacter() {
        viewCharacter.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(-viewCharacter.getWidth(), 0, 0, 0);
        animate.setDuration(800);
        animate.setFillAfter(true);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationHandCharacter();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewCharacter.startAnimation(animate);
    }

    private void animationHandCharacter() {
        viewHandCharacter.setVisibility(View.VISIBLE);
        TranslateAnimation animateHand = new TranslateAnimation(0, 0, viewHandCharacter.getHeight(), 0);
        animateHand.setDuration(700);
        animateHand.setFillAfter(true);
        animateHand.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationMessage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewHandCharacter.startAnimation(animateHand);
    }

    private void animationMessage() {
        viewMessage1.animate().alpha(1).setDuration(1000);
        viewMessage2.animate().alpha(1).setStartDelay(300).setDuration(1000);
        viewMessage3.animate().alpha(1).setStartDelay(500).setDuration(1000);
    }


}
