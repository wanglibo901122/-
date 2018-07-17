package com.bawei.myvideo.sousuo.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.WindowManager;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.FindMoreAdapter;
import com.bawei.myvideo.adapter.ImgAdapter;
import com.bawei.myvideo.home.bean.Findmorebean;
import com.bawei.myvideo.sousuo.p.FindPer;
import com.bawei.myvideo.xiangqiang.XqingActivity;

import java.util.List;

public class ShowfindmoreActivity extends AppCompatActivity implements FindView {

    private RecyclerView mFindrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfindmore);
        initView();
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        String uid = getIntent().getStringExtra("uid");

        new FindPer(this).login(uid);

    }

    @Override
    public void getSuccess(Findmorebean body) {
        final List<Findmorebean.RetBean.ListBean> list = body.getRet().getList();
        FindMoreAdapter findMoreAdapter = new FindMoreAdapter(list, this);
        mFindrv.setAdapter(findMoreAdapter);
        mFindrv.setLayoutManager( new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //mFindrv.setLayoutManager(new GridLayoutManager(this,3));
        findMoreAdapter.setOnRecyclerViewListener(new ImgAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(ShowfindmoreActivity.this, XqingActivity.class).putExtra("url",list.get(position).getDataId()));
                finish();
            }
        });
    }

    private void initView() {
        mFindrv = (RecyclerView) findViewById(R.id.findrv);
    }
}
