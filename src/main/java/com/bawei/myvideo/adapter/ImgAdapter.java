package com.bawei.myvideo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.myvideo.R;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 王利博 on 2018/6/21.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.Myviewholer>   {
   private   List<String> list;
   private Context context;

    public ImgAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;



    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position);


    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }

    @NonNull
    @Override
    public Myviewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_item, parent, false);
        return new Myviewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholer holder, final int position) {
        String s = list.get(position);
        Glide.with(context).load(s).into(holder.sdv);
        holder.mPosition=position;
        holder.sdv.setOnClickListener(new View.OnClickListener() {
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

    public class Myviewholer  extends RecyclerView.ViewHolder   {
        public ImageView sdv;
        public int mPosition;
        public Myviewholer(View itemView) {
            super(itemView);
            sdv=itemView.findViewById(R.id.sdv);

        }


    }
}
