package com.bawei.myvideo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.home.bean.Pinglunbean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class PingLunAdapter extends RecyclerView.Adapter<PingLunAdapter.Myviewholder>{
    private List<Pinglunbean.RetBean.ListBean> list;
    private Context context;

    public PingLunAdapter(List<Pinglunbean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pinglun_item, parent, false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        Pinglunbean.RetBean.ListBean db = list.get(position);
        holder.time.setText(db.getTime());
        holder.name.setText(db.getPhoneNumber());
        holder.msg.setText("\t\t\t\t"+db.getMsg());
       // Glide.with(context).load(db.getUserPic()).into(holder.imgsdv);
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                //图片地址
                .setUri(db.getUserPic())
                //播放gif 图片
                .setAutoPlayAnimations(true)
                //点击重新加载时 可以重新加载4 次
                .setTapToRetryEnabled(true)
                .build();

        holder.imgsdv.setController(controller);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder  extends RecyclerView.ViewHolder{
        private SimpleDraweeView imgsdv;
        private TextView name;
        private TextView msg;
        private TextView time;


        public Myviewholder(View itemView) {
            super(itemView);
            imgsdv=itemView.findViewById(R.id.imgsdv);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            msg=itemView.findViewById(R.id.msg);
        }
    }
}
