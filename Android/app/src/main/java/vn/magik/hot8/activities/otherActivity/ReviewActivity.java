package vn.magik.hot8.activities.otherActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.customviews.HandleAnimationView;

/**
 * Created by NGUYENHUONG on 7/1/17.
 */

public class ReviewActivity extends AppCompatActivity {
    @BindView(R.id.view_review)
    View viewReview;
    @BindView(R.id.view_thanks)
    View viewThanks;

    @BindView(R.id.tv_like_app)
    TextView tvLikeApp;
    @BindView(R.id.tv_unlike)
    TextView tvUnlike;
    @BindView(R.id.tv_negative)
    TextView tvNegative;
    @BindView(R.id.tv_positive)
    TextView tvPositive;

    HandleAnimationView animationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        animationView = new HandleAnimationView();
    }

    @OnClick(R.id.tv_like_app)
    void onClickLikeApp() {
        animationView.animationFromXToY(viewThanks, viewReview);
    }


    @OnClick({R.id.tv_negative, R.id.tv_unlike})
    void onClickSkip() {
        onBackPressed();
    }


    @OnClick(R.id.tv_positive)
    void onClickReview() {
        startActivityReviewApplication();
        onBackPressed();
    }

    private void startActivityReviewApplication() {
        SharedPreferences prfs = getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putBoolean(Constants.KEY_REVIEW, true);
        editor.apply();
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
