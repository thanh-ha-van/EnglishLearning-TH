package vn.magik.hot8.fragments.introfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.hot8.R;
import vn.magik.hot8.adapters.LevelAdapter;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

public class IntroFragment0 extends IntroFragment {
    @BindView(R.id.rc_choose_level)
    RecyclerView recyclerViewLevel;
    @BindArray(R.array.level_list)
    String[] levels;
    private LevelAdapter levelAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnClickNext onClickNext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro_0, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void animationViewPage() {

    }

    @Override
    public void disableAnimationViewPage() {

    }

    public void setOnClickNext(OnClickNext onClickNext) {
        this.onClickNext = onClickNext;
    }

    private void initView() {
        levelAdapter = new LevelAdapter(getActivity(), levels, 1);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewLevel.setLayoutManager(layoutManager);
        recyclerViewLevel.setAdapter(levelAdapter);
    }


    @OnClick(R.id.tv_next)
    void OnclickButton() {
        if (onClickNext != null) {
            onClickNext.onNextPage();
        }
    }

    public interface OnClickNext {
        void onNextPage();
    }
}
