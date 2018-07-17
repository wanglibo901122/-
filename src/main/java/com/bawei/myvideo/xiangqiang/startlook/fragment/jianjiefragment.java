package com.bawei.myvideo.xiangqiang.startlook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.ImgAdapter;
import com.bawei.myvideo.adapter.VideoJianAdapter;
import com.bawei.myvideo.home.bean.Xiangqingbean;
import com.bawei.myvideo.xiangqiang.XqingActivity;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class jianjiefragment extends Fragment {
    private View view;
    private TextView mJianname;
    private TextView mJianppeole;
    private TextView mJianbody;
    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_videojianjie, container, false);
        //注册
        EventBus.getDefault().register(this);



        return view;
    }

    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void getdata(Xiangqingbean.RetBean db) {
        //吐司接收到的数据
        mJianname = (TextView) view.findViewById(R.id.jianname1);
        mJianbody = (TextView) view.findViewById(R.id.jianbody1);
        mJianppeole = (TextView) view.findViewById(R.id.jianppeole1);
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        //赋值
        mJianbody.setText("\t\t\t\t" + db.getDescription());
        mJianppeole.setText(db.getActors());
        mJianname.setText(db.getDirector());
        //获取数据 进行数据适配
        final List<Xiangqingbean.RetBean.ListBean.ChildListBean> list = db.getList().get(0).getChildList();
        VideoJianAdapter videoJianAdapter = new VideoJianAdapter(list, getContext());
        mRv.setAdapter(videoJianAdapter);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        videoJianAdapter.setOnRecyclerViewListener(new ImgAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), XqingActivity.class).putExtra("url",list.get(position).getDataId()));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }


}
