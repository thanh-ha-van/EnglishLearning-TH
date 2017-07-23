package thanh.ha.english.adapters;

/**
 * Created by NGUYEN HUONG on 5/27/2017.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.models.TestTrans;


/**
 * Created by HaVan on 5/24/2017.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {

    private TestTrans miniTest;
    private boolean isShowWord;
    private OnClickAnswer onClickAnswer;

    public AnswerAdapter(TestTrans miniTest, boolean isShowWord, OnClickAnswer onClickAnswer) {
        this.miniTest = miniTest;
        this.isShowWord = isShowWord;
        this.onClickAnswer = onClickAnswer;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.view_contain)
        View viewContain;
        @BindView(R.id.tv_answer)
        TextView tvAnswer;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            miniTest.setSelectAnswer(getAdapterPosition());
            notifyDataSetChanged();
            viewContain.setClickable(false);
            onClickAnswer.onClick();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_answer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (isShowWord) {
            holder.tvAnswer.setText(miniTest.getAnswers().get(position).getContent());
        } else {
            holder.tvAnswer.setText(miniTest.getAnswers().get(position).getMean());
        }
        if (position == miniTest.getAnswers().size() - 1) {
            holder.viewContain.setBackgroundResource(R.drawable.bg_plate_white_smoke);
        } else {
            holder.viewContain.setBackgroundResource(R.color.colorWhiteSmoke);
        }
        if (miniTest.isShowAnswer()) {
            if (position == miniTest.getCorrectAnswer()) {
                holder.tvAnswer.setBackgroundResource(R.color.green);
                holder.tvAnswer.setTextColor(Color.WHITE);
            } else if (position == miniTest.getSelectAnswer()) {
                holder.tvAnswer.setBackgroundResource(R.color.redLight);
                holder.tvAnswer.setTextColor(Color.RED);
            }
        }
    }

    @Override
    public int getItemCount() {
        return miniTest.getAnswers().size();
    }

    public interface OnClickAnswer {
        void onClick();
    }
}

