package thanh.ha.english.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import thanh.ha.english.R;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.Day;

/**
 * Created by HaVan on 5/23/2017.
 */
public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.MyViewHolder> {

    List<Day> dayList;
    Context context;

    public WeekAdapter(Context context, List<Day> dayList) {
        this.dayList = dayList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        Button button;
        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            button = (Button) view.findViewById(R.id.button_day);
            textView = (TextView) view.findViewById(R.id.text_day);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        // default not learn yet : getHasLearnYet() == -1
        holder.textView.setText(dayList.get(position).getText());
        holder.button.setText("");
        holder.button.setBackgroundResource(R.drawable.circle_gray);

        // on learning
        if (dayList.get(position).getHasLearnYet() == 0) {
            holder.button.setText(dayList.get(position).getProgress() + "");
            holder.button.setBackgroundResource(R.drawable.circle_green);
        } else if (dayList.get(position).getHasLearnYet() == 1)
            if (dayList.get(position).getProgress() == Globals.getIns().getConfig().getmWordOfDay()) {
                holder.button.setText("");
                holder.button.setBackgroundResource(R.drawable.ic_checked);
            } else if (dayList.get(position).getProgress() == 0) {
                holder.button.setText("");
                holder.button.setBackgroundResource(R.drawable.ic_cancel);
            } else {
                holder.button.setText(dayList.get(position).getProgress() + "");
                holder.button.setBackgroundResource(R.drawable.circle_done);
            }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
            }

        });

    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

}