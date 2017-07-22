package vn.magik.hot8.otherHandle;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.util.IabHelper;
import vn.magik.hot8.util.IabResult;
import vn.magik.hot8.util.Inventory;
import vn.magik.hot8.util.Purchase;

/**
 * Created by ADMIN on 4/14/2016.
 */
public class InAppProducts {
    public static final int REQUEST_CODE = 10001;
    private IabHelper mHelper;
    private Activity activity;
    private String currentPackageVip;

    public InAppProducts(Activity activity) {
        this.activity = activity;
        mHelper = new IabHelper(activity, Constants.PUBLIC_KEY);
        mHelper.enableDebugLogging(true);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (result.isSuccess() && mHelper != null) {
                    mHelper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() {
                        @Override
                        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                            if (result.isSuccess()) {

                            }
                        }
                    });
                }
            }
        });
    }

    //handler buy item from google
    public void handleBuyItemFromGoogle(String currentPackageVip) {
        if (mHelper != null) {
            this.currentPackageVip = currentPackageVip;
            mHelper.launchPurchaseFlow(activity, currentPackageVip, REQUEST_CODE, finishedListener);
        }
    }

    //callback will called when buy item finish
    public IabHelper.OnIabPurchaseFinishedListener finishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            if (result.isSuccess()) {
                if (info.getSku().equals(currentPackageVip)) {
                    Globals.getIns().getConfig().setBuyVip(true);
                }
                saveBuyVipConfig();
            }
        }
    };

    //save data current boox
    public void saveBuyVipConfig() {
//        SharedPreferences preferences = activity.getSharedPreferences(Constant.FILE_CONFIG, Context.MODE_WORLD_WRITEABLE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(Constant.TOTAL_WORDS, Util.endCode(Constant.COUNT_CURRENT_GROUP));
//        editor.apply();
    }


    //handle result when buy vip
    public boolean handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper == null) return false;
        return mHelper.handleActivityResult(requestCode, resultCode, data);

    }
}
