package com.bawei.myvideo.xiangqiang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myvideo.R;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


/**
 * Created by 王利博 on 2018/6/24.
 */

public class JianFragment extends Fragment {
    private View view;
    private TextView mJianname;
    private TextView mJianppeole;
    private TextView mJianbody;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.layout_jianjie, container, false);
        //http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=223_c2c3e2d1ddd948048d4186c4c6684248
        //注册
        EventBus.getDefault().register(this);
        initView(view);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void dream(String str) {
        //吐司接收到的数据
       if(str!=null){
           String[] split = str.split(",");
           mJianbody.setText("\t\t\t\t" +split[0] );
           mJianppeole.setText(split[2]);
           mJianname.setText(split[1]);
       }
    }

    private void initView(View view) {
        mJianname = (TextView) view.findViewById(R.id.jianname);
        mJianppeole = (TextView) view.findViewById(R.id.jianppeole);
        mJianbody = (TextView) view.findViewById(R.id.jianbody);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

}
