package vn.magik.hot8.activities.changeConfigActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.adapters.FavoriteAdapter;
import vn.magik.hot8.adapters.LevelAdapter;

public class ChangeConfigActivity extends AppCompatActivity {

    private ChangeConfigPresenter presenter;
    @BindView(R.id.rv_level)
    public RecyclerView recyclerViewLevel;
    @BindView(R.id.rv_favorite)
    public RecyclerView recyclerViewTopic;
    @BindView(R.id.tv_topic)
    public TextView tvTopic;
    @BindView(R.id.tv_level)
    public TextView tvLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_config);
        ButterKnife.bind(this);
        presenter = new ChangeConfigPresenter(this);
        initFavView();
        initLevelView();
    }

    private void initFavView() {
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this, presenter.loadFavoriteData(), 1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTopic.setLayoutManager(layoutManager);
        recyclerViewTopic.setAdapter(favoriteAdapter);
    }

    private void initLevelView() {
        LevelAdapter levelAdapter = new LevelAdapter(this, presenter.loadLevelData(), 1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewLevel.setLayoutManager(layoutManager);
        recyclerViewLevel.setAdapter(levelAdapter);
    }

    @OnClick(R.id.tv_level)
    public void tvLevelClicked() {
        makeLevelView();
    }

    @OnClick(R.id.tv_topic)
    public void tvTopicClicked() {
        makeTopicView();
    }

    @OnClick(R.id.img_check)
    public void imgCheckClicked() {
        onBackPressed();
    }

    private void makeTopicView() {
        recyclerViewTopic.setVisibility(View.VISIBLE);
        recyclerViewLevel.setVisibility(View.GONE);
        tvLevel.setTextColor(getResources().getColor(R.color.gray_2));
        tvTopic.setTextColor(getResources().getColor(R.color.white));
    }

    private void makeLevelView() {
        recyclerViewTopic.setVisibility(View.GONE);
        recyclerViewLevel.setVisibility(View.VISIBLE);
        tvLevel.setTextColor(getResources().getColor(R.color.white));
        tvTopic.setTextColor(getResources().getColor(R.color.gray_2));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.saveConfig();
    }
}
