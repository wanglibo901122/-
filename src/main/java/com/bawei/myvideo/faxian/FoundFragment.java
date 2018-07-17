package com.bawei.myvideo.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.ImgListAdapter;
import com.bawei.myvideo.adapter.RvAdapter;
import com.bawei.myvideo.home.bean.BannerBean;
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class FoundFragment extends Fragment   {
    private RecyclerView mRv;


    private List<BannerBean.RetBean.ListBean.ChildListBean> db;
    private View view;
    /**
     * 换一批
     */
   // private Button mNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_faxian, container, false);
        //注册
        EventBus.getDefault().register(this);


        return view;
    }


    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void dream(final List<BannerBean.RetBean.ListBean> list) {
        //吐司接收到的数据
        mRv = (RecyclerView) view.findViewById(R.id.rv);
       // mNext = (Button) view.findViewById(R.id.next);

            db = list.get(4).getChildList();

            ////
             RvAdapter adapter = new RvAdapter(getActivity(), db);
            mRv.setLayoutManager(new OverLayCardLayoutManager());
            CardConfig.initConfig(getActivity());
            ItemTouchHelper.Callback callback = new RenRenCallback(mRv, adapter, db);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(mRv);
            mRv.setAdapter(adapter);
        adapter.setOnRecyclerViewListener(new ImgListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                 startActivity(new Intent(getActivity(), XqingActivity.class).putExtra("url",db.get(position).getDataId()));

            }
        });

          /*  mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击切换视图
                }
            });*/




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }



}
