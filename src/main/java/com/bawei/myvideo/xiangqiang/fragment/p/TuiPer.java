package com.bawei.myvideo.xiangqiang.fragment.p;

import com.bawei.myvideo.xiangqiang.fragment.m.TuiMod;
import com.bawei.myvideo.xiangqiang.fragment.v.TuiView;

/**
 * Created by 王利博 on 2018/6/27.
 */

public class TuiPer implements TuiPerIm {
    private TuiView tuiView;
    private TuiMod tuiMod;

    public TuiPer(TuiView tuiView) {
        this.tuiView = tuiView;
        tuiMod=new TuiMod(tuiView);
    }

    @Override
    public void login(String s) {
            tuiMod.login(s);
    }
}
