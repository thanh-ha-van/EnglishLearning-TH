package thanh.ha.english.activities.createPlanActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;
import thanh.ha.english.activities.choiceActivity.ChoiceWordActivity;

/**
 * Created by NGUYENHUONG on 6/16/17.
 */

public class CreatePlanActivity extends AppCompatActivity {

    @BindView(R.id.pg_create_plan)
    DonutProgress pgCreatePlan;
    @BindView(R.id.tv_message_create_plan)
    TextView tvMessageCreatePlan;
    @BindView(R.id.tv_choice_word)
    TextView tvChoiceWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        ButterKnife.bind(this);
        createPlanTimer();
    }

    private void createPlanTimer() {
        new CountDownTimer(2000, 20) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 10;
                pgCreatePlan.setProgress(100 - seconds / 2);
                pgCreatePlan.setText(String.format(Locale.US, "%d%%", 100 - seconds / 2));
            }

            @Override
            public void onFinish() {
                pgCreatePlan.setText("100%");
                pgCreatePlan.setProgress(100);
                tvMessageCreatePlan.setText(R.string.msg_finish_create_plan);
                tvChoiceWord.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @OnClick(R.id.tv_choice_word)
    void onClick() {
        Intent intent = new Intent(CreatePlanActivity.this, ChoiceWordActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }

}
