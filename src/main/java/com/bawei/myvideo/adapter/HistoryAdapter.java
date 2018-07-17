package com.bawei.myvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.greenbean.Person;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 王利博 on 2018/7/4.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Myviewholder> {
    private List<Person> list;
    private Context context;

    public HistoryAdapter(List<Person> list, Context context) {
        this.list = list;
        this.context = context;
    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public DataListAdapter.OnRecyclerViewListener mOnRecyclerViewListener;



    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position);


    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(DataListAdapter.OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }


    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.img_list_item, parent, false);
        return new Myviewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder holder, int position) {
        Person db = list.get(position);
        holder.mPosition=position;
        holder.tv.setText(db.getName());
        Glide.with(context).load(db.getStrurl()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(holder.mPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        public int mPosition;
        public Myviewholder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
