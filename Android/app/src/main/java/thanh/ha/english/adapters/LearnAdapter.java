package thanh.ha.english.adapters;

/**
 * Created by NGUYEN HUONG on 5/27/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.fragments.learnFragment.LearnFragment;
import thanh.ha.english.models.Word;


/**
 * Created by HaVan on 5/24/2017.
 */
public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.MyViewHolder> {

    private List<Word> dataSet;
    private LearnFragment.OnClickNext onClickNext;

    public LearnAdapter(List<Word> dataSet, LearnFragment.OnClickNext onClickNext) {
        this.dataSet = dataSet;
        this.onClickNext = onClickNext;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_word)
        View viewWord;
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_mean)
        TextView tvMean;
        @BindView(R.id.tv_next)
        TextView tvNext;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() == dataSet.size()) {
                        onClickNext.onNextPage();
                    }
                }
            });
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word_learn, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position < dataSet.size()) {
            holder.tvWord.setText(dataSet.get(position).getContent());
            holder.tvMean.setText(dataSet.get(position).getMean());
            holder.tvNext.setVisibility(View.GONE);
            holder.viewWord.setVisibility(View.VISIBLE);
            holder.viewWord.setBackgroundResource(R.color.colorWhiteSmoke);
        } else {
            holder.tvNext.setVisibility(View.VISIBLE);
            holder.viewWord.setBackgroundResource(R.drawable.bg_plate_white_smoke);
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size() + 1;
    }

}

