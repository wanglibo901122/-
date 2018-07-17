package com.bawei.myvideo.xiangqiang.fragment.m;

import android.util.Log;

import com.bawei.myvideo.home.bean.Xiangqingbean;
import com.bawei.myvideo.net.Api;
import com.bawei.myvideo.net.OkhttpUntils;
import com.bawei.myvideo.xiangqiang.fragment.v.TuiView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王利博 on 2018/6/27.
 */

public class TuiMod implements TuiModIm {
    private TuiView tuiView;

    public TuiMod(TuiView tuiView) {
        this.tuiView = tuiView;
    }

    @Override
    public void login(String string) {
        OkhttpUntils.getmInstance().getRequestInterface(Api.URL_).getXiangData(string).enqueue(new Callback<Xiangqingbean>() {
            @Override
            public void onResponse(Call<Xiangqingbean> call, Response<Xiangqingbean> response) {
                if(response!=null){

                    tuiView.getSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Xiangqingbean> call, Throwable t) {
                Log.e("'aaat",t.getMessage());
            }
        });
    }
}
