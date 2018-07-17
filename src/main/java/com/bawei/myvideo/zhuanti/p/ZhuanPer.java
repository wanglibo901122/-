package com.bawei.myvideo.zhuanti.p;

import com.bawei.myvideo.zhuanti.m.ZhuanMod;
import com.bawei.myvideo.zhuanti.v.ZhuanView;

/**
 * Created by 王利博 on 2018/6/22.
 */

public class ZhuanPer implements ZhuanPerIm {
    private ZhuanView zhuanView;
    private ZhuanMod zhuanMod;

    public ZhuanPer(ZhuanView zhuanView) {
        this.zhuanView = zhuanView;
        zhuanMod=new ZhuanMod(zhuanView);
    }

    @Override
    public void login() {
        zhuanMod.login();
    }
}
