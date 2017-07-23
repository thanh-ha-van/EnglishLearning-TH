package thanh.ha.english.adapters;

/**
 * Created by HaVan on 5/27/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.constants.Globals;

/**
 * Created by HaVan on 5/24/2017.
 */
public class WordConfigAdapter extends RecyclerView.Adapter<WordConfigAdapter.MyViewHolder> {

    private String[] dataSet;
    protected Context context;

    public WordConfigAdapter(Context context, String[] dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.num_word_per_day)
        TextView tvWordConfig;
        @BindView(R.id.img_check)
        ImageView imageViewCheck;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvWordConfig.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Globals.getIns().getConfig().setmWordOfDay((getAdapterPosition()+ 1)*4);
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word_per_day, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvWordConfig.setText(dataSet[position] + " " + context.getResources().getString(R.string.word));
        if ((position+1)*4 == Globals.getIns().getConfig().getmWordOfDay()) {
            holder.imageViewCheck.setVisibility(View.VISIBLE);
        } else {
            holder.imageViewCheck.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

}

