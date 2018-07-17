package com.bawei.myvideo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.LinearLayout;

import cn.carbs.android.library.AutoZoomInImageView;

public class MainActivity extends AppCompatActivity {

    private AutoZoomInImageView mImg;
    private LinearLayout mLldd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);
        initView();


        //设置支持开场动画放大效果
        mImg.post(new Runnable() {//iv即AutoZoomInImageView
            @Override
            public void run() {
                //简单方式启动放大动画
//                iv.init()
//                  .startZoomInByScaleDeltaAndDuration(0.3f, 1000, 1000);//放大增量是0.3，放大时间是1000毫秒，放大开始时间是1000毫秒以后
                //使用较为具体的方式启动放大动画
                mImg.init()
                        .setScaleDelta(0.2f)//放大的系数是原来的（1 + 0.2）倍
                        .setDurationMillis(1500)//动画的执行时间为1500毫秒
                        .setOnZoomListener(new AutoZoomInImageView.OnZoomListener() {
                            @Override
                            public void onStart(View view) {
                                //放大动画开始时的回调
                            }

                            @Override
                            public void onUpdate(View view, float progress) {
                                //放大动画进行过程中的回调 progress取值范围是[0,1]
                            }

                            @Override
                            public void onEnd(View view) {
                                //Toast.makeText(MainActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
                                //放大动画结束时的回调 跳转页面
                                startMainActivity();

                            }
                        })
                        .start(1000);//延迟1000毫秒启动
            }
        });
    }

    private void startMainActivity() {
        if (isNetworkAvailable(this)) {
            startActivity(new Intent(MainActivity.this, ShowActivity.class));

        }else{
            Toast.makeText(this, "当前网络不可用！", Toast.LENGTH_SHORT).show();
        }
     finish();

    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }


    private void initView() {

        mImg = (AutoZoomInImageView) findViewById(R.id.img);
        mLldd = (LinearLayout) findViewById(R.id.lldd);
    }
}
