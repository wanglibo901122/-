package com.bawei.myvideo.net;

import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王利博 on 2018/6/19.
 */


public class OkhttpUntils {
    private volatile static OkhttpUntils util=null;
    private OkhttpUntils(){
    }
    public static OkhttpUntils getmInstance(){
        if (util==null){
            synchronized (OkhttpUntils.class){
                if (util==null){
                    util=new OkhttpUntils();
                }
            }
        }
        return util;
    }


    public GetRequest  getRequestInterface(String str){
        //okHttp拦截器
        OkHttpClient builder = new OkHttpClient.Builder()
             //   .addInterceptor(new MyInterceptor())//拦截器
                .build();

        //创建Retrofit实例
        retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl(str)//设置网路请求URL
                .client(builder)//设置okHttp拦截器
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RXjava平台
                .build();

        GetRequest  anInterface = retrofit.create(GetRequest.class);

        return anInterface;
    }
}