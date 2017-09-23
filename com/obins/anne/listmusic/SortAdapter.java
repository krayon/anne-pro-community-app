package com.obins.anne.listmusic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.obins.anne.C0182R;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.Music;
import java.util.List;

public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<Music> list = null;
    private Context mContext;
    private int selectedPosition = -1;

    static final class ViewHolder {
        LinearLayout list_LinearLayout;
        TextView tvLetter;
        TextView tvTitle;

        ViewHolder() {
        }
    }

    public SortAdapter(Context mContext, List<Music> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void setPosition(int position) {
        this.selectedPosition = position;
    }

    public void updateListView(List<Music> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        Music mContent = (Music) this.list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(this.mContext).inflate(C0182R.layout.item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(C0182R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(C0182R.id.catalog);
            viewHolder.list_LinearLayout = (LinearLayout) view.findViewById(C0182R.id.list_LinearLayout);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (position == getPositionForSection(getSectionForPosition(position))) {
            viewHolder.tvLetter.setVisibility(0);
            viewHolder.tvLetter.setText(mContent.getSortletters());
        } else {
            viewHolder.tvLetter.setVisibility(8);
        }
        if (this.selectedPosition == position) {
            viewHolder.tvTitle.setBackgroundColor(this.mContext.getResources().getColor(C0182R.color.main_font_green));
        } else {
            viewHolder.tvTitle.setBackgroundColor(this.mContext.getResources().getColor(C0182R.color.main_bk_gray));
        }
        viewHolder.tvTitle.setText(((Music) this.list.get(position)).getTitle());
        return view;
    }

    public int getSectionForPosition(int position) {
        if (this.list == null) {
            Log.i(AppConfig.SERVER_IP, "list == null");
        }
        if (this.list.get(position) == null) {
            Log.i(AppConfig.SERVER_IP, "list.get(position) == null");
        }
        if (((Music) this.list.get(position)).getSortletters() == null) {
            Log.i(AppConfig.SERVER_IP, "list.get(position).getSortletters() == null");
        }
        return ((Music) this.list.get(position)).getSortletters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            if (((Music) this.list.get(i)).getSortletters().toUpperCase().charAt(0) == section) {
                return i;
            }
        }
        return -1;
    }

    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        return sortStr.matches("[A-Z]") ? sortStr : "#";
    }

    public Object[] getSections() {
        return null;
    }
}
