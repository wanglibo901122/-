package com.bawei.myvideo.xiangqiang.fragment.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.ImgAdapter;
import com.bawei.myvideo.adapter.XingqingAdapter;
import com.bawei.myvideo.home.bean.Xiangqingbean;
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.bawei.myvideo.xiangqiang.fragment.p.TuiPer;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 王利博 on 2018/6/24.
 */

public class TuiFragment extends Fragment   {
    private View view;
    private RecyclerView mTuijianrv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_tuijian, container, false);

        //注册
        EventBus.getDefault().register(this);

        initView(view);
         mTuijianrv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        return view;
    }

    /**
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void getSuccess(final List<Xiangqingbean.RetBean.ListBean.ChildListBean> list) {
        //适配器
        XingqingAdapter xingqingAdapter = new XingqingAdapter(list, getContext());
        mTuijianrv.setAdapter(xingqingAdapter);
        //监听回调
        xingqingAdapter.setOnRecyclerViewListener(new ImgAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
              //  Log.e("zzzzzzzzzzz'",list.get(position).getTitle());

                //把对象传值过去
                  startActivity(new Intent(getActivity(), XqingActivity.class).putExtra("url", list.get(position).getDataId()));


            }
        });
    }

    private void initView(View view) {
        mTuijianrv = (RecyclerView) view.findViewById(R.id.tuijianrv);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

}
