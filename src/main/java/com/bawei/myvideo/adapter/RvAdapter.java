package com.bawei.myvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.home.bean.BannerBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 王利博 on 2018/7/3.
 */


public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private  List<BannerBean.RetBean.ListBean.ChildListBean> list;
    private final LayoutInflater inflater;

    public RvAdapter(Context context,  List<BannerBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public ImgListAdapter.OnRecyclerViewListener mOnRecyclerViewListener;



    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position);


    }

    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(ImgListAdapter.OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mPosition=position;
        BannerBean.RetBean.ListBean.ChildListBean db = list.get(position);
        viewHolder.tv_name.setText(db.getTitle());
        Glide.with(context).load(db.getPic()).into( viewHolder.imgview);
        viewHolder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(  viewHolder.mPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public int mPosition;
        private final TextView tv_name;
        private SimpleDraweeView imgview;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            imgview=itemView.findViewById(R.id.imgview);
        }
    }
}
