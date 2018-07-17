package com.bawei.myvideo.fuli;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bawei.myvideo.R;
import com.bumptech.glide.Glide;

public class ShowimgActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimg);
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
        String url = getIntent().getStringExtra("url");
        if(url!=null){
            Glide.with(this).load(url).into(mImg);
        }

    }

    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img:
                finish();
                break;
        }
    }
}
