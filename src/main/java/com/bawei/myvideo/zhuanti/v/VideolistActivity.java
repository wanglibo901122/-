package com.bawei.myvideo.zhuanti.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.DataListAdapter;
import com.bawei.myvideo.home.bean.DataListBean;
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.bawei.myvideo.zhuanti.p.VideoListPer;

import java.util.List;

public class VideolistActivity extends AppCompatActivity implements VideolistView, View.OnClickListener {

    private ImageView mImgback;
    /**
     * miaoshu
     */
    private TextView mImgname;
    private RecyclerView mRv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);

        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initView();

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        mImgname.setText(title);
        // 获取catalogId  值
        String[] split = url.split("catalogId=");
        String[] split1 = split[1].split("&");
        //请求
        new VideoListPer(this).login(split1[0]);
        mRv2.setLayoutManager(new GridLayoutManager(this,3));
       // mRv2.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    /**
     * 成功的数据
     * @param body
     */
    @Override
    public void getSuccess(DataListBean body) {


        final List<DataListBean.RetBean.ListBean> list = body.getRet().getList();
        DataListAdapter adapter = new DataListAdapter(list, this);
        mRv2.setAdapter(adapter);
        //接口回调
        adapter.setOnRecyclerViewListener(new DataListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {

                //把对象传值过去
                String dataId = list.get(position).getDataId();
                    if(dataId!=null){
                        Intent intent = new Intent(VideolistActivity.this, XqingActivity.class);
                        intent.putExtra("url",dataId);
                        startActivity(intent);
                    }

            }
        });
    }

    private void initView() {
        mImgback = (ImageView) findViewById(R.id.imgback);
        mImgback.setOnClickListener(this);
        mImgname = (TextView) findViewById(R.id.imgname);
        mRv2 = (RecyclerView) findViewById(R.id.rv2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.imgback:
                finish();
                break;
        }
    }
}
