package thanh.ha.english.activities.termOfUseActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;

public class TermOfUseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_use);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_back)
    public void backClicked() {
        onBackPressed();
    }
}
