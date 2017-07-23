package thanh.ha.english.fragments.introfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.activities.changeConfigActivity.ChangeConfigPresenter;
import thanh.ha.english.adapters.FavoriteAdapter;
import thanh.ha.english.models.Favorite.Favorite;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class IntroFragment5 extends IntroFragment {
    @BindView(R.id.rv_favorite)
    RecyclerView recyclerView;
    List<Favorite> favorites;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_5, container, false);
        ButterKnife.bind(this, view);
        getList();
        initViewPage();
        return view;
    }

    @Override
    public void animationViewPage() {

    }

    @Override
    public void disableAnimationViewPage() {

    }

    public void initViewPage() {
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this.getContext(), favorites, 1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favoriteAdapter);
    }

    public void getList() {
        ChangeConfigPresenter presenter = new ChangeConfigPresenter(this.getContext());
        favorites = presenter.loadFavoriteData();
    }
}
