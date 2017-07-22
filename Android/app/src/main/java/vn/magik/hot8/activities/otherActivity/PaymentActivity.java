package vn.magik.hot8.activities.otherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.customviews.HandleAnimationView;
import vn.magik.hot8.otherHandle.InAppProducts;
import vn.magik.hot8.utils.Utils;

/**
 * Created by NGUYENHUONG on 7/1/17.
 */

public class PaymentActivity extends AppCompatActivity {
    @BindView(R.id.view_buy_vip)
    View viewBuyVip;
    @BindView(R.id.view_msg_buy_vip_1)
    View viewMsgBuyVip1;
    @BindView(R.id.view_msg_buy_vip_2)
    View viewMsgBuyVip2;
    @BindView(R.id.tv_message_buy_vip)
    TextView tvMessageBuyVip;
    @BindView(R.id.view_count_down)
    View viewCountDown;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    private HandleAnimationView animationView;
    private InAppProducts inAppProducts;
    private Handler handler;
    private boolean isRunCountDown = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        animationView = new HandleAnimationView();
        inAppProducts = new InAppProducts(this);
        tvMessageBuyVip.setText(R.string.msg_buy_inapp);
        handler = new Handler();
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int time = Utils.getTimeNextDay(Utils.getCurrentTimestamp()) - Utils.getCurrentTimestamp();
            if (time > 0) {
                isRunCountDown = true;
                tvCountDown.setText(Utils.getFormatTime(time));
                handler.postDelayed(this, 1000);
            } else {
                viewCountDown.setVisibility(View.GONE);
            }

        }
    };

    @Override
    protected void onDestroy() {
        if (!isRunCountDown) {
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }

    @OnClick(R.id.tv_skip)
    void onClickSkip() {
        onBackPressed();
    }


    @OnClick({R.id.tv_buy_vip_1, R.id.tv_buy_vip_2, R.id.tv_buy_vip_3})
    void onClickBuyVIP(View view) {
        String packageVip = Constants.PackageVip.BUY_1_MONTH;
        switch (view.getId()) {
            case R.id.tv_buy_vip_2:
                packageVip = Constants.PackageVip.BUY_6_MONTH;
                break;
            case R.id.tv_buy_vip_3:
                packageVip = Constants.PackageVip.BUY_FOREVER;
                break;
        }
        inAppProducts.handleBuyItemFromGoogle(packageVip);
        tvSkip.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_great)
    void onClickGreat() {
        animationView.animationFromXToY(viewMsgBuyVip1, viewMsgBuyVip2);
    }

    @OnClick(R.id.tv_remember)
    void onClickRemember() {
        animationView.animationFromXToY(viewMsgBuyVip2, viewBuyVip);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InAppProducts.REQUEST_CODE) {
            inAppProducts.handleOnActivityResult(requestCode, resultCode, data);
        }
    }
}
