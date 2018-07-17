package com.bawei.myvideo.zhuanti.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.myvideo.R;
import com.bawei.myvideo.ShowActivity;
import com.bawei.myvideo.adapter.ImgListAdapter;
import com.bawei.myvideo.home.bean.BannerBean;
import com.bawei.myvideo.zhuanti.p.ZhuanPer;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class ZhuantiFragment extends Fragment implements ZhuanView {
    private View view;
    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.layout_zhuanti, container, false);

        initView(view);
        new ZhuanPer(this).login();
        //瀑布流
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        return view;
    }




    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv1);
    }

    /**
     * 获取成功
     * @param body
     */
    @Override
    public void getSuccess(BannerBean body) {
        //吐司接收到的数据
        //Toast.makeText(getActivity(), body.getMsg(), Toast.LENGTH_SHORT).show();
        List<BannerBean.RetBean.ListBean> list = body.getRet().getList();
        //去掉 banner 轮播
        final List<BannerBean.RetBean.ListBean> newlist=new ArrayList<>();
        for (int i=1;i<list.size();i++){
            newlist.add(list.get(i));
        }

        //适配
        ImgListAdapter imgListAdapter = new ImgListAdapter(newlist, getContext());
        mRv.setAdapter(imgListAdapter);
        //接口回调 点击跳转
        imgListAdapter.setOnRecyclerViewListener(new ImgListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                String moreURL = newlist.get(position).getMoreURL();
                //判断地址不为空
                if(!"".equals(moreURL)){
                    //新建activity  并且跳转
                    Intent intent = new Intent(getActivity(), VideolistActivity.class);
                    intent.putExtra("url", moreURL);
                    intent.putExtra("title",newlist.get(position).getTitle());
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"暂无数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
