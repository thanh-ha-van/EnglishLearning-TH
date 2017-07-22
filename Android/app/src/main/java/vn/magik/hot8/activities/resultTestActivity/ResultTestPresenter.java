package vn.magik.hot8.activities.resultTestActivity;

import android.content.Context;

import vn.magik.hot8.constants.Globals;

/**
 * Created by NGUYENHUONG on 7/2/17.
 */

class ResultTestPresenter implements ResultTestInterface.RequiredPresenterOps {
    private ResultTestInterface.RequiredViewOps mView;
    private ResultTestModel mModel;

    ResultTestPresenter(Context context, ResultTestInterface.RequiredViewOps mView) {
        this.mView = mView;
        mModel = new ResultTestModel(context, this);
    }

    void handleNextAction() {
        int currentDate = Globals.getIns().getConfig().getCountDate();
        if (currentDate == 1 || currentDate % 2 == 0) {
            if (!Globals.getIns().getConfig().isBuyVip()) {
                mView.startActivityBuyVip();
            } else if (!mModel.isShowReview()) {
                mView.startActivityReview();
            }
        } else {
            mView.finishActivity();
        }

    }
}