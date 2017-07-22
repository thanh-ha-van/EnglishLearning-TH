package vn.magik.hot8.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;


/**
 * Created by HaVan on 5/25/2017.
 */

public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.ViewHolder> {


    private String dataSet;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public KeyAdapter(Context context, String dataShuffle) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.dataSet = dataShuffle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_key, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvKey.setText(String.valueOf(dataSet.charAt(position)));
    }


    @Override
    public int getItemCount() {
        return dataSet.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_key)
        TextView tvKey;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                boolean isCorrect = mClickListener.onItemClick(view, getAdapterPosition());
                if (isCorrect) {
                    tvKey.setVisibility(View.GONE);
                    itemView.setClickable(false);
                } else {
                    tvKey.setVisibility(View.VISIBLE);
                    itemView.setClickable(true);
                    doShowEffect(tvKey);
                }
            }
        }
    }

    private void doShowEffect(final TextView tv) {
        tv.setTextColor(context.getResources().getColor(R.color.white));
        tv.setBackgroundResource(R.drawable.plate_red);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setTextColor(context.getResources().getColor(R.color.colorBlackLight));
                tv.setBackgroundResource(R.drawable.bg_plate_strong);
            }
        }, 400);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        boolean onItemClick(View view, int position);
    }
}