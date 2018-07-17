package com.bawei.myvideo.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王利博 on 2018/6/20.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Myviewholder>{
   private List<BannerBean.RetBean.ListBean> list;
   private Context context;

    public VideoAdapter(List<BannerBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        Myviewholder myviewholder = new Myviewholder(inflate);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder holder, final int position) {
        holder.mPosition=position;
        final List<String> stringList=new ArrayList<>();
        final List<String> urllist=new ArrayList<>();
        List<String> imgList=new ArrayList<>();
        for (int i=1;i<list.size();i++){
            for (int j=0;j<list.get(i).getChildList().size();j++){

                String pic = list.get(i).getChildList().get(j).getPic();
                  String title = list.get(i).getChildList().get(j).getTitle();
                String dataId = list.get(i).getChildList().get(j).getDataId();
                if(!"".equals(pic)){
                 if(!title.contains("推啊")&&!title.contains("红包")){
                     stringList.add(title);
                     imgList.add(pic);
                     urllist.add(dataId);
                 }
             }


            }
        }
        //赋值
        holder.title.setText(stringList.get(position));
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                //图片地址
                .setUri(imgList.get(position))
                //播放gif 图片
                .setAutoPlayAnimations(true)
                //点击重新加载时 可以重新加载4 次
                .setTapToRetryEnabled(true)
                .build();

        holder.img.setController(controller);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(mOnRecyclerViewListener != null){
                     mOnRecyclerViewListener.onItemClick(holder.mPosition);

                 }
                Log.e("aaaaaa'",urllist.toString());
                context.startActivity(new Intent(context, XqingActivity.class).putExtra("url",urllist.get(position)));
            }
        });


    }

    /**
     * 去掉广告和图片为空
     * @return
     */
    @Override
    public int getItemCount() {
        int num=0;
        for (int i=1;i<list.size();i++){
            for (int j=0;j<list.get(i).getChildList().size();j++){
               if(!list.get(i).getChildList().get(j).getTitle().contains("推啊")&&!list.get(i).getChildList().get(j).getTitle().contains("红包")&&!list.get(i).getChildList().get(j).getPic().equals("")){
                   num++;
               }
            }
        }

        return num;
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        public SimpleDraweeView img;
        public TextView title;
        public int mPosition;
        public Myviewholder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
        }
    }
}
