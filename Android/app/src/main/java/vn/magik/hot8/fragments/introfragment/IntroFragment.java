package vn.magik.hot8.fragments.introfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.mainActivity.MainActivity;
import vn.magik.hot8.activities.testActivity.TestActivity;
import vn.magik.hot8.adapters.FavoriteAdapter;
import vn.magik.hot8.constants.Constants;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public abstract class IntroFragment extends Fragment {

    public void startAnimation() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationViewPage();
            }
        }, 200);

    }

    public abstract void animationViewPage();

    public abstract void disableAnimationViewPage();

}
