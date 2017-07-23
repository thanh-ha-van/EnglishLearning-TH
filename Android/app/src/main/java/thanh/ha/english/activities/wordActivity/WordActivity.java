package thanh.ha.english.activities.wordActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import thanh.ha.english.R;
import thanh.ha.english.adapters.WordListAdapter;
import thanh.ha.english.models.Word;

public class WordActivity extends AppCompatActivity implements WordInterface.ViewOpt {

    List<Word> listWord;
    WordPresenter wordPresenter;
    private WordListAdapter mAdapter;

    @BindView(R.id.list_my_word)
    public StickyListHeadersListView stickyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        ButterKnife.bind(this);
        wordPresenter = new WordPresenter(this, this);
        initList();
        initView();
    }

    private void initView() {
        mAdapter = new WordListAdapter(this, listWord);
        stickyList.addHeaderView(getLayoutInflater().inflate(R.layout.item_day_header, null));
        stickyList.setDrawingListUnderStickyHeader(false);
        stickyList.setAreHeadersSticky(true);
        stickyList.setAdapter(mAdapter);
    }

    private void initList() {
        listWord = wordPresenter.loadAllMyWord();
    }

    @OnClick(R.id.img_back)
    public void backPressed() {
        onBackPressed();
    }

}

