package vn.magik.hot8.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import vn.magik.hot8.R;
import vn.magik.hot8.models.Word;

/**
 * Created by HaVan on 5/23/2017.
 */
public class WordListAdapter extends BaseAdapter implements
        StickyListHeadersAdapter, SectionIndexer {

    private final Context mContext;
    private List<Word> wordList;
    public int[] mSectionIndices;
    public Character[] mSectionLetters;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, List<Word> dataSet) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        wordList = dataSet;
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_word, parent, false);
            holder.textContent = (TextView) convertView.findViewById(R.id.tv_word);
            holder.textMean = (TextView) convertView.findViewById(R.id.tv_mean);
            holder.imgBox = (ImageView) convertView.findViewById(R.id.img_view_flower);
            holder.btnListen = (ImageButton) convertView.findViewById(R.id.img_btn_listen);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textContent.setText(wordList.get(position).getContent());
        holder.textMean.setText(wordList.get(position).getMean());
        setImageBox(holder.imgBox, position);
        holder.btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do listening stuffs
            }
        });
        return convertView;
    }

    public void setImageBox(ImageView imageView, int position) {
        switch (wordList.get(position).getBox()) {
            case 0:
                imageView.setImageResource(R.drawable.ic_box_0);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ic_box_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_box_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_box_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_box_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_box_5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ic_box_6);
                break;

        }
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.item_day_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text_day);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        holder.text.setText(getDayText(position));

        return convertView;
    }

    public String getDayText(int position) {
        String[] day = mContext.getResources().getStringArray(R.array.day_list);
        return day[wordList.get(position).getBox()];
    }

    /**
     * Remember that these have to be static, postion=1 should always return
     * the same Id that is.
     */
    @Override
    public long getHeaderId(int position) {
        // return the first character of the country as ID because this is what
        // headers are based upon
        return wordList.get(position).getBox();
    }

    @Override
    public int getPositionForSection(int section) {
        if (mSectionIndices.length == 0) {
            return 0;
        }

        if (section >= mSectionIndices.length) {
            section = mSectionIndices.length - 1;
        } else if (section < 0) {
            section = 0;
        }
        return mSectionIndices[section];
    }

    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < mSectionIndices.length; i++) {
            if (position < mSectionIndices[i]) {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
    }

    @Override
    public Object[] getSections() {
        return mSectionLetters;
    }


    public class HeaderViewHolder {
        TextView text;
    }

    public class ViewHolder {
        TextView textContent;
        TextView textMean;
        ImageView imgBox;
        ImageButton btnListen;

    }

}