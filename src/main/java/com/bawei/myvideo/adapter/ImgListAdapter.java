package com.bawei.myvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.home.bean.BannerBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 王利博 on 2018/6/22.
 */

public class ImgListAdapter extends RecyclerView.Adapter<ImgListAdapter.Myviewholder> {
    private List<BannerBean.RetBean.ListBean> list;
    private Context context;

    public ImgListAdapter(List<BannerBean.RetBean.ListBean> list, Context context) {
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
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.img_list_item, parent, false);
        return new Myviewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder holder, int position) {
        BannerBean.RetBean.ListBean db = list.get(position);
        String title = db.getTitle();
        String pic = db.getChildList().get(0).getPic();
        holder.mPosition=position;
      if(!"".equals(title)&&!"".equals(pic)){
          //图片
          Glide.with(context).load(pic).into(holder.img);
          //问题
          holder.tv.setText(title);


          holder.img.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  if (mOnRecyclerViewListener != null) {
                      mOnRecyclerViewListener.onItemClick(holder.mPosition);
                  }
              }
          });
      }
    }

    @Override
    public int getItemCount() {
        int num=0;

        for (int i=0;i<list.size();i++){
          //  String pic = list.get(i).getChildList().get(0).getPic();
            if(!"".equals(list.get(i).getTitle())){
                num++;
            }
        }


        return num;
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
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
