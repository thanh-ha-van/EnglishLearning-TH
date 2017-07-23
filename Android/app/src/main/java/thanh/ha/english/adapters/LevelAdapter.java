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
public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.MyViewHolder> {

    private String[] dataSet;
    protected Context context;
    private int colorCode;

    public LevelAdapter(Context context, String[] dataSet, int colorCode) {
        this.context = context;
        this.dataSet = dataSet;
        this.colorCode = colorCode;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_level)
        TextView tvLevel;
        @BindView(R.id.img_check)
        ImageView imageViewCheck;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvLevel.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Globals.getIns().getConfig().setLevel(getAdapterPosition() + 1);
            notifyDataSetChanged();
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_level, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        setColor(holder);
        holder.tvLevel.setText(dataSet[position]);
        if (position == Globals.getIns().getConfig().getLevel() - 1) {
            holder.imageViewCheck.setVisibility(View.VISIBLE);
            if (colorCode == 1) {
                holder.tvLevel.setTextColor(context.getResources().getColor(R.color.white));
            } else holder.tvLevel.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            holder.imageViewCheck.setVisibility(View.INVISIBLE);
            if (colorCode == 1) {
                holder.tvLevel.setTextColor(context.getResources().getColor(R.color.gray));
            } else
                holder.tvLevel.setTextColor(context.getResources().getColor(R.color.colorBlackLight));
        }
    }

    public void setColor(final LevelAdapter.MyViewHolder holder) {
        if (colorCode == 2) {
            holder.tvLevel.setTextColor(context.getResources().getColor(R.color.black));
            holder.imageViewCheck.setImageResource(R.drawable.ic_checked_black);
        } else {
            holder.tvLevel.setTextColor(context.getResources().getColor(R.color.white));
            holder.imageViewCheck.setImageResource(R.drawable.ic_checked_white);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

}

