package com.bawei.myvideo.xiangqiang.startlook.fragment.p;

import com.bawei.myvideo.xiangqiang.startlook.fragment.m.PingMod;
import com.bawei.myvideo.xiangqiang.startlook.fragment.v.PingView;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class PingPer implements PingPerIm {
    private PingView pingView;
    private PingMod pingMod;

    public PingPer(PingView pingView) {
        this.pingView = pingView;
        pingMod=new PingMod(pingView);
    }

    @Override
    public void longin(String str) {
        pingMod.login(str);
    }
}
