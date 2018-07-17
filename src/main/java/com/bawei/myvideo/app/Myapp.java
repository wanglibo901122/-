package com.bawei.myvideo.app;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.com.sky.downloader.greendao.DaoMaster;
import com.com.sky.downloader.greendao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import de.greenrobot.event.EventBus;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class Myapp extends Application {
    private  static DaoSession daoSession;

    {
        // PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setQQZone("1106791289", "aIWIYvo71fEGzrxg");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        Fresco.initialize(this);

        UMConfigure.init(this,"5ae065ce8f4a9d0f3500012c"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
    //历史记录
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "persondemo3.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession= daoMaster.newSession();
    }
    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
