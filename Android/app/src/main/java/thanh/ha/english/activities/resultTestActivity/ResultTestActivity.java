package thanh.ha.english.activities.resultTestActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;
import thanh.ha.english.activities.examActivity.ExamActivity;
import thanh.ha.english.activities.otherActivity.PaymentActivity;
import thanh.ha.english.activities.otherActivity.ReviewActivity;
import thanh.ha.english.constants.Constants;

/**
 * Created by NGUYENHUONG on 7/1/17.
 */

public class ResultTestActivity extends AppCompatActivity implements ResultTestInterface.RequiredViewOps {

    public static final int NEXT_ACTION = 0;
    public static final int EXAM_ACTIVITY = 1;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_positive)
    TextView tvPositive;

    private String title, message;
    private int startNextActivity;
    private ResultTestPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra(Constants.KEY_TITLE);
        message = getIntent().getStringExtra(Constants.KEY_MESSAGE);
        startNextActivity = getIntent().getIntExtra(Constants.KEY_ACTIVITY_NEXT, 0);
        setContentView(R.layout.view_result_test);
        ButterKnife.bind(this);
        mPresenter = new ResultTestPresenter(this, this);
        bindUI();
    }


    public void bindUI() {
        tvPositive.setText(R.string.next);
        if (title != null) {
            tvTitle.setText(title);
        }
        if (message != null) {
            tvMessage.setText(message);
        }
    }

    @OnClick(R.id.tv_positive)
    void onClickPositive() {
        switch (startNextActivity) {
            case NEXT_ACTION:
                mPresenter.handleNextAction();
                break;
            case EXAM_ACTIVITY:
                startExamActivity();
                break;
        }
    }

    private void startExamActivity() {
        Intent intent = new Intent(this, ExamActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void startActivityReview() {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    @Override
    public void startActivityBuyVip() {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }
}
