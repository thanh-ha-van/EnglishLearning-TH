package thanh.ha.english.fragments.learnFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.adapters.LearnAdapter;
import thanh.ha.english.models.Word;

/**
 * Created by HaVan on 6/8/2017.
 */

public class LearnFragment extends Fragment {
    @BindView(R.id.rv_list_word)
    RecyclerView rvListWord;
    private List<Word> words;
    private OnClickNext onClickNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void setDataTest(OnClickNext onClickNext, List<Word> words) {
        this.onClickNext = onClickNext;
        this.words = words;
    }

    private void initView() {
        if (words != null) {
            LearnAdapter levelAdapter = new LearnAdapter(words, onClickNext);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvListWord.setLayoutManager(layoutManager);
            rvListWord.setAdapter(levelAdapter);
        }
    }

    public interface OnClickNext {
        void onNextPage();
    }
}
