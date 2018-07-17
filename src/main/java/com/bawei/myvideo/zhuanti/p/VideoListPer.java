package com.bawei.myvideo.zhuanti.p;

import com.bawei.myvideo.zhuanti.m.VideoListMod;
import com.bawei.myvideo.zhuanti.v.VideolistView;

/**
 * Created by 王利博 on 2018/6/22.
 */

public class VideoListPer implements VideoListPerIm {
    private VideolistView videolistView;
    private VideoListMod videoListMod;

    public VideoListPer(VideolistView videolistView) {
        this.videolistView = videolistView;
        videoListMod=new VideoListMod(videolistView);
    }

    @Override
    public void login(String s) {
        videoListMod.login(s);
    }
}
