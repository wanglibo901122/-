package com.bawei.myvideo.zhuanti.m;

import com.bawei.myvideo.home.bean.DataListBean;
import com.bawei.myvideo.net.Api;
import com.bawei.myvideo.zhuanti.Inet.OokhttpUntils;
import com.bawei.myvideo.zhuanti.p.ZhuanPerIm;
import com.bawei.myvideo.zhuanti.v.VideolistView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王利博 on 2018/6/22.
 */

public class VideoListMod implements VideoListModIm {
    private VideolistView videolistView;

     public VideoListMod(VideolistView videolistView) {
        this.videolistView = videolistView;
    }

    @Override
    public void login(String s) {
        OokhttpUntils.getmInstance().getRequestInterface(Api.URL_).getDataList(s).enqueue(new Callback<DataListBean>() {
            @Override
            public void onResponse(Call<DataListBean> call, Response<DataListBean> response) {
               if(response!=null){
                   videolistView.getSuccess(response.body());
               }
            }

            @Override
            public void onFailure(Call<DataListBean> call, Throwable t) {

            }
        });
    }
}
