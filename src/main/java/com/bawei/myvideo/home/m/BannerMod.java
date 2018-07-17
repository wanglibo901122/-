package com.bawei.myvideo.home.m;

import android.util.Log;

import com.bawei.myvideo.home.bean.BannerBean;
import com.bawei.myvideo.home.v.BannerView;
import com.bawei.myvideo.net.Api;
import com.bawei.myvideo.net.OkhttpUntils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class BannerMod implements  BannerModIm {
    private BannerView bannerView;

    public BannerMod(BannerView bannerView) {
        this.bannerView = bannerView;
    }

    @Override
    public void login() {
        OkhttpUntils.getmInstance().getRequestInterface(Api.URL_).getBanner().enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean body = response.body();
               if(body!=null){
                   bannerView.getSuccess(body);
               }

            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                Log.e("sssssssssss",t.getMessage());
            }
        });
    }
}
