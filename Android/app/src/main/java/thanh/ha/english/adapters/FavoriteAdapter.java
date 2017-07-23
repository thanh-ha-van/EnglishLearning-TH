package thanh.ha.english.adapters;

/**
 * Created by HaVan on 5/27/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thanh.ha.english.R;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.Favorite.Favorite;

/**
 * Created by HaVan on 5/24/2017.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private List<Favorite> mList = new ArrayList<>();
    private Context context;
    private SharedPreferences sharedPreferences;
    int colorCode = 1;

    public FavoriteAdapter(Context context, List<Favorite> list, int colorCode) {
        this.context = context;
        this.mList = list;
        sharedPreferences = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        this.colorCode = colorCode;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int currentPosition = -1;
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
            currentPosition = getAdapterPosition();
            changeSelectionStatus(currentPosition);
            saveUserFavorite();
            notifyDataSetChanged();
        }

    }

    private void changeSelectionStatus(int position) {
        if (mList.get(position).isSelected()) {
            mList.get(position).setSelected(false);
        } else {
            mList.get(position).setSelected(true);
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
        holder.tvLevel.setText(mList.get(position).getFavorite());
        if (mList.get(position).isSelected()) {
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

    public void setColor(final MyViewHolder holder) {
        if (colorCode == 2) {
            holder.tvLevel.setTextColor(context.getResources().getColor(R.color.black));
            holder.imageViewCheck.setImageResource(R.drawable.ic_checked_black);
        } else {
            holder.tvLevel.setTextColor(context.getResources().getColor(R.color.white));
            holder.imageViewCheck.setImageResource(R.drawable.ic_checked_white);
        }
    }


    private void saveUserFavorite() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isSelected())
                sb.append(String.valueOf(i)).append(",");
        }
        if (sb.length() != 0) {
            String sc = sb.substring(0, sb.length() - 1);
            Globals.getIns().getConfig().setHobbies(sc);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constants.KEY_FAVORITE, sc);
            editor.apply();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

