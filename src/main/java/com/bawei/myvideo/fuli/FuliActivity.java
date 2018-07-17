package com.bawei.myvideo.fuli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

public class FuliActivity extends AppCompatActivity  {

    private RecyclerView mRv;
    List<String> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli);
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
         list.add("http://a3.topitme.com/1/21/79/1128833621e7779211o.jpg");
        list.add("http://f2.topitme.com/2/6a/bc/113109954583dbc6a2o.jpg");
        list.add("http://fd.topitme.com/d/a8/1d/11315383988791da8do.jpg");
        list.add("http://img.51ztzj.com/upload/image/20140924/sj201409241003_279x419.jpg");
        list.add("http://pic9.nipic.com/20100905/2531170_095210291877_2.jpg");
        list.add("http://ff.topitme.com/f/c1/7a/11277298189537ac1fo.jpg");
        list.add("http://f0.topitme.com/0/05/be/11269838091bfbe050o.jpg");
        list.add("http://a3.topitme.com/0/d0/f1/1128126520d81f1d00o.jpg");
        list.add("http://pic.iask.cn/fimg/354526356_605.jpg");
        list.add("http://photocdn.sohu.com/20150327/mp8283643_1427449218479_3.jpeg");
        list.add("http://m.360buyimg.com/n12/jfs/t2134/320/1636289389/161300/a1f8dd5f/566d2f8aNeac8fd45.jpg%21q70.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3741330424,1956600302&fm=214&gp=0.jpg");
        list.add("http://img009.hc360.cn/y3/M06/82/57/wKhQh1T8e5-EAMGTAAAAAATcF8g099.jpg");


        ImgAdapter imgAdapter = new ImgAdapter(list, this);
        mRv.setAdapter(imgAdapter);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //条目监听
        imgAdapter.setOnRecyclerViewListener(new ImgAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FuliActivity.this, ShowimgActivity.class);
                intent.putExtra("url",list.get(position));
                startActivity(intent);
            }
        });


    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
    }


}
