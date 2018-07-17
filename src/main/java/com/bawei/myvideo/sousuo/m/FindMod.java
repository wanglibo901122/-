package com.bawei.myvideo.sousuo.m;

import com.bawei.myvideo.home.bean.Findmorebean;
import com.bawei.myvideo.net.Api;
import com.bawei.myvideo.net.OkhttpUntils;
import com.bawei.myvideo.sousuo.v.FindView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class FindMod  implements FindModIm {
    private FindView findView;

    public FindMod(FindView findView) {
        this.findView = findView;
    }

    @Override
    public void login(String s) {
        OkhttpUntils.getmInstance().getRequestInterface(Api.URL_).getfindmore(s).enqueue(new Callback<Findmorebean>() {
            @Override
            public void onResponse(Call<Findmorebean> call, Response<Findmorebean> response) {
                findView.getSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Findmorebean> call, Throwable t) {

            }
        });
    }
}
