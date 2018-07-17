package com.bawei.myvideo.xiangqiang.startlook;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.MyAdapter;
import com.bawei.myvideo.app.Myapp;
import com.bawei.myvideo.greenbean.Person;
import com.bawei.myvideo.greenbean.User;
import com.bawei.myvideo.home.bean.Xiangqingbean;
import com.bawei.myvideo.xiangqiang.startlook.fragment.jianjiefragment;
import com.bawei.myvideo.xiangqiang.startlook.fragment.v.pinglunfragment;
import com.bumptech.glide.Glide;
import com.com.sky.downloader.greendao.DaoSession;
import com.com.sky.downloader.greendao.PersonDao;
import com.com.sky.downloader.greendao.UserDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogRecord;

import cn.jzvd.JZVideoPlayerStandard;
import de.greenrobot.event.EventBus;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private Boolean flage = false;
    private ImageView mTitleback;
    private TextView mTitlename;
    private ImageView mTitlelike;
     private IjkVideoView mIjkPlayer;
    private TabLayout mTablayout;
    private ViewPager mViewpage;
    private jianjiefragment jianjie;
    private pinglunfragment pinglun;
    List<Fragment> list = new ArrayList<>();
    List<String> listStr = new ArrayList<>();
    private Xiangqingbean.RetBean db;
    private PersonDao personDao;
    private UserDao userDao;
    // private JCVideoPlayerStandard mVideoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        DaoSession daoSession = Myapp.getDaoSession();
        personDao = daoSession.getPersonDao();
        userDao = daoSession.getUserDao();
        //初始化
       IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        AndroidMediaController controller = new AndroidMediaController(this);
           mIjkPlayer.setMediaController(controller);
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = (Xiangqingbean.RetBean) getIntent().getSerializableExtra("user");

        //播放视屏
            mIjkPlayer.setVideoURI(Uri.parse(db.getSmoothURL()));
           mIjkPlayer.start();
        //视频

        //拆分22
       /* String[] split = db.getSmoothURL().split(".m3u8");
        String s = split[0].toString();
        String url = s + ".mp4";
        Log.d("哈哈", "onCreate: "+url);

        mVideoplayer.setUp(  url
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");

      //  Glide.with(this).load( db.getPic()) .into(mVideoplayer.thumbImageView);
        mVideoplayer.widthRatio = 4;//播放比例
        mVideoplayer.heightRatio = 3;*/





        //标题
        mTitlename.setText(db.getTitle());
        //进入页面后先把数据的信息获取到  遍历查看收藏的表里面有没有响应的数据
        List<User> data12 = userDao.queryBuilder().build().list();
        for (int i = 0; i < data12.size(); i++) {
            String name = data12.get(i).getName();
            if (db.getTitle().equals(name)) {
                //有改数据的话就返回 true
                flage = true;
                break;
            }
        }

        //判断 布尔值是否true 来改变响应的 图片
        if (flage) {
            mTitlelike.setImageResource(R.mipmap.collection_select);
        } else {
            mTitlelike.setImageResource(R.mipmap.collection);
        }


        //初始化
        initDate();

        MyAdapter my = new MyAdapter(getSupportFragmentManager(), list, listStr);
        mViewpage.setAdapter(my);

        //设置TabLayout的模式
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        //让TabLayout和ViewPager关联
        try {
            mTablayout.setupWithViewPager(mViewpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Eventbus 传值到 简介fragment
        EventBus.getDefault().postSticky(db);

        //Eventbus 传值到 pinglunfragment
        EventBus.getDefault().postSticky(db.getDataID());
    }


    private void initView() {
        mTitleback = (ImageView) findViewById(R.id.titleback);
        mTitleback.setOnClickListener(this);
        mTitlename = (TextView) findViewById(R.id.titlename);
        mTitlelike = (ImageView) findViewById(R.id.titlelike);
        mTitlelike.setOnClickListener(this);
          mIjkPlayer = (IjkVideoView) findViewById(R.id.ijkPlayer);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpage = (ViewPager) findViewById(R.id.viewpage);

      //  mVideoplayer =  (JCVideoPlayerStandard)findViewById(R.id.videoplayer);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.titleback:
                finish();
                break;
            case R.id.titlelike:
                //收藏
                //点击收藏  反之取消
                if (flage) {
                    mTitlelike.setImageResource(R.mipmap.collection);
                    getToast("取消收藏");
                    //删除
                    List<User> list12 = userDao.queryBuilder().where(UserDao.Properties.Name.eq(db.getTitle())).build().list();
                    for (int i = 0; i < list12.size(); i++) {
                        userDao.delete(list12.get(i));
                    }
                } else {
                    mTitlelike.setImageResource(R.mipmap.collection_select);
                    getToast("收藏成功");
                    //点击收藏就添加进数据库里面
                    User u = new User();
                    u.setName(db.getTitle());
                    u.setUid(db.getDataID());
                    u.setStrurl(db.getPic());
                    userDao.insert(u);
                }
                flage = !flage;
                break;
        }
    }

    private void getToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void initDate() {
        jianjie = new jianjiefragment();
        pinglun = new pinglunfragment();
        list.add(jianjie);
        list.add(pinglun);

        String name1 = "简介";
        String name2 = "评论";
        listStr.add(name1);
        listStr.add(name2);
    }


    @Override
    protected void onPause() {
        super.onPause();
          mIjkPlayer.pause();
       JCVideoPlayer.releaseAllVideos();
        List<Person> newlist = personDao.queryBuilder().where(PersonDao.Properties.Name.eq(db.getTitle())).build().list();
        if (newlist.size() == 0) {
            //图片存入数据库
            Person person = new Person();
            person.setName(db.getTitle());
            person.setStrurl(db.getPic());
            person.setUid(db.getDataID());
            personDao.insert(person);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
          mIjkPlayer.resume();
    }
    @Override
    public void onBackPressed() {
     if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}
