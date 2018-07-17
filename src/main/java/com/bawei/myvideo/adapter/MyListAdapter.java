package com.bawei.myvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.bawei.myvideo.R;
import com.mikepenz.iconics.view.IconicsTextView;

import java.util.List;

/**
 * Created by 王利博 on 2018/6/21.
 */

public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MyListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listitem_layout, viewGroup, false);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.listitem_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

            holder.textView.setText(list.get(i));




        return view;
    }
    class ViewHolder {
        private IconicsTextView textView;
    }
}
