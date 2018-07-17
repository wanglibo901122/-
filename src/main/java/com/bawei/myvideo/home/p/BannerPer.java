package com.bawei.myvideo.home.p;

import com.bawei.myvideo.home.m.BannerMod;
import com.bawei.myvideo.home.v.BannerView;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class BannerPer implements BannerPerIm{
    private BannerView bannerView;
    private BannerMod bannerMod;

    public BannerPer(BannerView bannerView) {
        this.bannerView = bannerView;
        bannerMod=new BannerMod(bannerView);
    }

    @Override
    public void login() {
        bannerMod.login();
    }
}
