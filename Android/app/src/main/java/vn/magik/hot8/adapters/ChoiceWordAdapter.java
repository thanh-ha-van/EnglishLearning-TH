package vn.magik.hot8.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;
import vn.magik.hot8.activities.choiceActivity.ChoiceWordInterface;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Word;

/**
 * Created by HaVan on 5/24/2017.
 */
public class ChoiceWordAdapter extends RecyclerView.Adapter<ChoiceWordAdapter.MyViewHolder> {


    private List<Word> mList;
    protected Context context;
    private OnclickView onclickView;
    private int mCountSelected;

    public ChoiceWordAdapter(Context context, List<Word> list, OnclickView onclickView) {
        this.mList = list;
        this.context = context;
        this.onclickView = onclickView;
    }

    public void setmCountSelected(int mCountSelected) {
        this.mCountSelected = mCountSelected;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_count_choice)
        TextView tvCountChoice;
        @BindView(R.id.img_btn_listen)
        ImageButton btnListen;
        @BindView(R.id.tv_choice)
        TextView tvChoice;
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_mean)
        TextView tvMean;

        @BindString(R.string.format_choice_word)
        String formatChoiceWord;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btnListen.setOnClickListener(this);
            tvChoice.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_choice:
                    onclickView.onClick(getAdapterPosition());
                    break;
                case R.id.img_btn_listen:
                    btnListen();
                    break;
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choice_word, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        int totalWord = Globals.getIns().getConfig().getmWordOfDay();
        String formatChoice = String.format(Locale.US, holder.formatChoiceWord, mCountSelected, totalWord);
        holder.tvCountChoice.setText(formatChoice);
        holder.tvMean.setText(mList.get(position).getMean());
        holder.tvWord.setText(mList.get(position).getContent());
        if (mList.get(position).getStatus() == Constants.StatusWord.SELECTED) {
            holder.tvChoice.setText(R.string.unchoice);
            holder.tvChoice.setTextColor(holder.tvChoice.getResources().getColor(R.color.colorGamboge));
            holder.tvChoice.setBackgroundResource(R.drawable.bg_rec_stroke_yellow);
        } else {
            holder.tvChoice.setBackgroundResource(R.drawable.bg_rec_fill_yellow);
            holder.tvChoice.setTextColor(holder.tvChoice.getResources().getColor(R.color.white));
            holder.tvChoice.setText(R.string.choice_word);
        }
    }

    private void btnListen() {
        // do listening stuffs
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnclickView {
        void onClick(int position);
    }

}

