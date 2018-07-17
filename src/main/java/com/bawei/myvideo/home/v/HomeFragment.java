package com.bawei.myvideo.home.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.VideoAdapter;
import com.bawei.myvideo.home.bean.BannerBean;
import com.bawei.myvideo.home.p.BannerPer;
import com.bawei.myvideo.net.GlideImageLoader;
import com.bawei.myvideo.sousuo.FindmoreActivity;
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class HomeFragment extends Fragment implements BannerView, View.OnClickListener {
    private View view;
    private Banner mBanner;
    //存放图片的集合
    private List<String> list = new ArrayList<>();

    /**
     * 一念天堂
     */
    private TextView mItemTitleText;
    private RecyclerView mRv;
  private  VideoAdapter videoAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);
        new BannerPer(this).login();
        initView(view);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void getSuccess(BannerBean body) {
        //Log.e("aaaa", body.getRet().getList().size() + "'");
        //Banner 数据
        final List<BannerBean.RetBean.ListBean.ChildListBean> data = body.getRet().getList().get(0).getChildList();
        for (int i = 0; i < data.size(); i++) {
            String pic = data.get(i).getPic();
            list.add(pic);//循环添加到图片集合里面
        }
        mBanner.setImages(list);
        mBanner.start();
        //banner 的事件
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                startActivity(new Intent(getActivity(), XqingActivity.class).putExtra("url",data.get(position).getDataId()));
            }

        });
        //Banner 下面的recy
        List<BannerBean.RetBean.ListBean> list = body.getRet().getList();
          videoAdapter = new VideoAdapter(list, getContext());
        mRv.setAdapter(videoAdapter);
        //把数据传递到发现页面
        //Activity 向推荐fragment 传值 list
        EventBus.getDefault().postSticky(list);

    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.bann);
        mBanner.setImageLoader(new GlideImageLoader());

        mItemTitleText = (TextView) view.findViewById(R.id.item_title_text);
        mItemTitleText.setOnClickListener(this);
        mRv = (RecyclerView) view.findViewById(R.id.rv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.item_title_text:
                //搜索框  跳转搜索页面
                startActivity(new Intent(getActivity(), FindmoreActivity.class));
                break;
        }
    }
}
