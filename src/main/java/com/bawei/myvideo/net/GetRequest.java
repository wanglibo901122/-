package com.bawei.myvideo.net;

import com.bawei.myvideo.home.bean.BannerBean;
import com.bawei.myvideo.home.bean.DataListBean;
import com.bawei.myvideo.home.bean.Findmorebean;
import com.bawei.myvideo.home.bean.Pinglunbean;
import com.bawei.myvideo.home.bean.Xiangbean;
import com.bawei.myvideo.home.bean.Xiangqingbean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 王利博 on 2018/6/19.
 */

public interface GetRequest {
   @GET("front/homePageApi/homePage.do")
   Call<BannerBean> getBanner();
   // ?catalogId=402834815584e463015584e539700019&information=null
   @GET("front/columns/getNewsList.do")
   Call<DataListBean> getDataList(@Query("catalogId") String catalogId);
   // ?mediaId=223_c2c3e2d1ddd948048d4186c4c6684248
   @GET("front/videoDetailApi/videoDetail.do")
   Call<Xiangqingbean> getXiangData(@Query("mediaId") String mediaId);
   // ?mediaId=1f7948116a4b4d16afd1d67484c4756e
   @GET("front/Commentary/getCommentList.do")
   Call<Pinglunbean> getPinglun(@Query("mediaId") String mediaId);
   // ?keyword=%E4%B8%89%E5%9B%BD%E6%BC%94%E4%B9%89
   @GET("front/searchKeyWordApi/getVideoListByKeyWord.do")
   Call<Findmorebean> getfindmore(@Query("keyword") String keyword);
}
