package com.bawei.myvideo.zhuanti.m;

import com.bawei.myvideo.home.bean.BannerBean;
import com.bawei.myvideo.net.Api;
import com.bawei.myvideo.net.OkhttpUntils;
import com.bawei.myvideo.zhuanti.v.ZhuanView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王利博 on 2018/6/22.
 */

public class ZhuanMod implements ZhuanModIm {
    private ZhuanView zhuanView;

    public ZhuanMod(ZhuanView zhuanView) {
        this.zhuanView = zhuanView;
    }

    @Override
    public void login() {
        OkhttpUntils.getmInstance().getRequestInterface(Api.URL_).getBanner().enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                if(response!=null){
                    zhuanView.getSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }
}
