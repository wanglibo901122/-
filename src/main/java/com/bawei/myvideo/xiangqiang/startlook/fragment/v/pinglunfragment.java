package com.bawei.myvideo.xiangqiang.startlook.fragment.v;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.PingLunAdapter;
import com.bawei.myvideo.home.bean.Pinglunbean;
import com.bawei.myvideo.xiangqiang.startlook.fragment.p.PingPer;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class pinglunfragment extends Fragment implements PingView {
    private View view;
    private RecyclerView mPingrv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.layout_videopinglun, container, false);
        //注册
        EventBus.getDefault().register(this);


        return view;
    }

    /**
     * eventbus
     *
     * @param string
     */
    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void getpinglun(String string) {

            new PingPer(this).longin(string);


    }

    /**
     * 数据请求成功
     *
     * @param body
     */
    @Override
    public void getSuccess(Pinglunbean body) {

        mPingrv = (RecyclerView) view.findViewById(R.id.pingrv);
        List<Pinglunbean.RetBean.ListBean> list = body.getRet().getList();
        //数据适配
        Log.e(";;;;;;;;;;;;;;",list.size()+"");
        PingLunAdapter adapter = new PingLunAdapter(list, getContext());
        mPingrv.setAdapter(adapter);
        mPingrv.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }




}
