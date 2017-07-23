package thanh.ha.english.activities.wordPerDayConfigActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thanh.ha.english.R;
import thanh.ha.english.adapters.WordConfigAdapter;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;

public class WordPerDayConfigActivity extends AppCompatActivity {

    @BindView(R.id.rv_config_word_per_day)
    public RecyclerView recyclerViewLevel;
    private LinearLayoutManager layoutManager;
    private WordConfigAdapter adapter;
    private String[] configList = {"4", "8", "12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_per_day);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        adapter = new WordConfigAdapter(this, configList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewLevel.setLayoutManager(layoutManager);
        recyclerViewLevel.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.KEY_WORD_OF_DAY, Globals.getIns().getConfig().getmWordOfDay());
        editor.apply();
    }

    @OnClick(R.id.img_back)
    public void backClicked() {
        onBackPressed();
    }
}
