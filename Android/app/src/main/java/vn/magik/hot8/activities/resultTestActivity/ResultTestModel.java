package vn.magik.hot8.activities.resultTestActivity;

import android.content.Context;
import android.content.SharedPreferences;

import vn.magik.hot8.constants.Constants;

/**
 * Created by NGUYENHUONG on 7/2/17.
 */

class ResultTestModel {
    private SharedPreferences sPref;
    private ResultTestInterface.RequiredPresenterOps mPresenter;

    ResultTestModel(Context context, ResultTestInterface.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
        sPref = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
    }

    boolean isShowReview() {
        return sPref.getBoolean(Constants.KEY_REVIEW, false);
    }
}
