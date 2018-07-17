package com.bawei.myvideo.sousuo.p;

import com.bawei.myvideo.sousuo.m.FindMod;
import com.bawei.myvideo.sousuo.v.FindView;

/**
 * Created by 王利博 on 2018/7/2.
 */

public class FindPer implements FindPerIm {
    private FindView findView;
    private FindMod findMod;

    public FindPer(FindView findView) {
        this.findView = findView;
        findMod=new FindMod(findView);
    }

    @Override
    public void login(String s) {
        findMod.login(s);
    }
}
