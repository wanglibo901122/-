package com.bawei.myvideo.xiangqiang.startlook.fragment.m;

import android.util.Log;

import com.bawei.myvideo.home.bean.Pinglunbean;
import com.bawei.myvideo.net.Api;
import com.bawei.myvideo.net.OkhttpUntils;
import com.bawei.myvideo.xiangqiang.startlook.fragment.v.PingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class PingMod implements PingModIm{
    private PingView pingView;

    public PingMod(PingView pingView) {
        this.pingView = pingView;
    }

    @Override
    public void login(String string) {
        OkhttpUntils.getmInstance().getRequestInterface(Api.URL_).getPinglun(string)
                .enqueue(new Callback<Pinglunbean>() {
                    @Override
                    public void onResponse(Call<Pinglunbean> call, Response<Pinglunbean> response) {

                            pingView.getSuccess(response.body());

                    }

                    @Override
                    public void onFailure(Call<Pinglunbean> call, Throwable t) {
                        Log.e("Tag",t.getMessage());
                    }
                });
    }
}
