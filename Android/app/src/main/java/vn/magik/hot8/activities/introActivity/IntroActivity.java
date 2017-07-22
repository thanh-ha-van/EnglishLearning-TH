package vn.magik.hot8.activities.introActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.adapters.ViewPagerAdapter;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.fragments.introfragment.IntroFragment;
import vn.magik.hot8.fragments.introfragment.IntroFragment0;
import vn.magik.hot8.fragments.introfragment.IntroFragment1;
import vn.magik.hot8.fragments.introfragment.IntroFragment2;
import vn.magik.hot8.fragments.introfragment.IntroFragment3;
import vn.magik.hot8.fragments.introfragment.IntroFragment4;
import vn.magik.hot8.fragments.introfragment.IntroFragment5;
import vn.magik.hot8.fragments.introfragment.IntroFragment6;
import vn.magik.hot8.fragments.introfragment.IntroFragment7;


public class IntroActivity extends AppCompatActivity implements IntroInterface.RequiredViewOps, IntroFragment0.OnClickNext {
    @BindView(R.id.vp_intro)
    public ViewPager vpIntro;
    private List<Fragment> introFragments;
    @BindView(R.id.layout_count_dot)
    public LinearLayout pager_indicator;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.tv_next)
    public TextView tvNext;
    @BindView(R.id.img_back)
    public ImageView imgBack;

    private ImageView[] dots;
    private int currentPosition = 0;
    private IntroPresenter mPresenter;
    private boolean isLoadWordFromServer = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
        createData();
        initView();
        mPresenter = new IntroPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAllWordFromServer();
    }

    private void loadAllWordFromServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!isLoadWordFromServer) {
                    isLoadWordFromServer = true;
                    mPresenter.loadAllWordFromServer();
                }
            }
        }).start();
    }

    private void createData() {
        introFragments = new ArrayList<>();
        IntroFragment0 introFragment0 = new IntroFragment0();
        IntroFragment1 introFragment1 = new IntroFragment1();
        IntroFragment2 introFragment2 = new IntroFragment2();
        IntroFragment3 introFragment3 = new IntroFragment3();
        IntroFragment4 introFragment4 = new IntroFragment4();
        IntroFragment5 introFragment5 = new IntroFragment5();
        IntroFragment6 introFragment6 = new IntroFragment6();
        IntroFragment7 introFragment7 = new IntroFragment7();
        introFragment0.setOnClickNext(this);
        introFragments.add(introFragment0);
        introFragments.add(introFragment1);
        introFragments.add(introFragment2);
        introFragments.add(introFragment3);
        introFragments.add(introFragment4);
        introFragments.add(introFragment5);
        introFragments.add(introFragment6);
        introFragments.add(introFragment7);
    }

    private void initView() {
        setUiPageViewController();
        final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), introFragments);
        vpIntro.setAdapter(pagerAdapter);
        vpIntro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void pageSelected(int position) {
        currentPosition = position;
        switchDot();
        animationIntroFragment();
        if (position == 0) {
            viewTitle.setVisibility(View.GONE);
        } else if (position == introFragments.size() - 1) {
            tvNext.setVisibility(View.INVISIBLE);
            viewTitle.setVisibility(View.VISIBLE);
        } else {
            tvNext.setVisibility(View.VISIBLE);
            viewTitle.setVisibility(View.VISIBLE);
        }
    }


    private void animationIntroFragment() {
        for (int i = 0; i < introFragments.size(); i++) {
            if (i == currentPosition) {
                ((IntroFragment) introFragments.get(currentPosition)).startAnimation();
            } else {
                ((IntroFragment) introFragments.get(currentPosition)).disableAnimationViewPage();
            }
        }


    }

    private void switchDot() {
        for (int i = 1; i < introFragments.size(); i++) {
            if (i == currentPosition)
                dots[i - 1].setImageResource(R.drawable.seclected_dot);
            else
                dots[i - 1].setImageResource(R.drawable.none_seclected_dot);
        }

    }

    private void setUiPageViewController() {
        dots = new ImageView[introFragments.size() - 1];
        for (int i = 0; i < introFragments.size() - 1; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.none_seclected_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(6, 0, 6, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageResource(R.drawable.seclected_dot);
    }

    @Override
    public void getWordFinish(boolean isSuccess) {


    }


    @OnClick(R.id.tv_next)
    public void btnNextClicked() {
        if (currentPosition < introFragments.size() - 1)
            vpIntro.setCurrentItem(currentPosition + 1);
    }

    @OnClick(R.id.img_back)
    public void backClicked() {
        if (currentPosition > 0)
            vpIntro.setCurrentItem(currentPosition - 1);
    }

    @Override
    public void onNextPage() {
        saveLevelConfig();
        btnNextClicked();
    }

    public void saveLevelConfig() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.FILE_CONFIG, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.KEY_LEVEL, Globals.getIns().getConfig().getLevel());
        editor.apply();
    }
}
