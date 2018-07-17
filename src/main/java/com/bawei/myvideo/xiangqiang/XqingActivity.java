package com.bawei.myvideo.xiangqiang;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.MyAdapter;
import com.bawei.myvideo.app.Myapp;
import com.bawei.myvideo.greenbean.User;
import com.bawei.myvideo.home.bean.DataListBean;
import com.bawei.myvideo.home.bean.Xiangqingbean;
import com.bawei.myvideo.xiangqiang.fragment.JianFragment;
import com.bawei.myvideo.xiangqiang.fragment.p.TuiPer;
import com.bawei.myvideo.xiangqiang.fragment.v.TuiFragment;
import com.bawei.myvideo.xiangqiang.fragment.v.TuiView;
import com.bawei.myvideo.xiangqiang.startlook.StartActivity;
import com.bumptech.glide.Glide;
import com.com.sky.downloader.greendao.PersonDao;
import com.com.sky.downloader.greendao.UserDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class XqingActivity extends AppCompatActivity implements View.OnClickListener,TuiView {

    private ImageView mTitleback;
    private TextView mTitlename;
    private ImageView mTitlelike;
    private ImageView mVideoimg;
    private TextView mPf;
    private TextView mLx;
    private TextView mDq;
    private TextView mSysj;
    /**
     * Start
     */
    private Button mVideobtn;
    private Boolean flage=false;
    private   UserDao userDao;
    private  Xiangqingbean.RetBean db;
    List<Fragment> list = new ArrayList<>();
    List<String> listStr = new ArrayList<>();
    private TabLayout mTablayout;
    private ViewPager mViewpage;
    private  JianFragment jianFragment;
    private TuiFragment tuiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xqing);

        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
          userDao = Myapp.getDaoSession().getUserDao();

        //获取传递来的对象
        String url = getIntent().getStringExtra("url");
        initView();



        new TuiPer(this).login(url);
        //进入页面后先把数据的信息获取到  遍历查看收藏的表里面有没有响应的数据
        List<User> list = userDao.queryBuilder().build().list();
        for(int i=0;i<list.size();i++){
            String uid = list.get(i).getUid();
            if(url.equals(uid)){
                //有改数据的话就返回 true
                flage=true;
                break;
            }
        }
      //判断 布尔值是否true 来改变响应的 图片
      if(flage){
            mTitlelike.setImageResource(R.mipmap.collection_select);
        }else{
            mTitlelike.setImageResource(R.mipmap.collection);
        }
        /////////////////////////
        //初始化
       /* initDate();

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
*/


    }

    private void initView() {
        mTitleback = (ImageView) findViewById(R.id.titleback);
        mTitleback.setOnClickListener(this);
        mTitlename = (TextView) findViewById(R.id.titlename);
        mTitlelike = (ImageView) findViewById(R.id.titlelike);
        mTitlelike.setOnClickListener(this);
        mVideoimg = (ImageView) findViewById(R.id.videoimg);
        mPf = (TextView) findViewById(R.id.pf);
        mLx = (TextView) findViewById(R.id.lx);
        mDq = (TextView) findViewById(R.id.dq);
        mSysj = (TextView) findViewById(R.id.sysj);
        mVideobtn = (Button) findViewById(R.id.videobtn);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpage = (ViewPager) findViewById(R.id.viewpage);
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
                //点击收藏  反之取消
            if(flage){
                    mTitlelike.setImageResource(R.mipmap.collection);
                getToast("取消收藏");
                //删除
                List<User> list = userDao.queryBuilder().where(UserDao.Properties.Name.eq(db.getTitle())).build().list();
                 for (int i=0;i<list.size();i++){
                     userDao.delete(list.get(i));
                 }
            }else{
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
    private void initDate() {

          jianFragment = new JianFragment();
          tuiFragment = new TuiFragment();
        list.add(jianFragment);
        list.add(tuiFragment);

        String name1 = "热门";
        String name2 = "关注";
        listStr.add(name1);
        listStr.add(name2);

    }

    /**
     * 成功
     * @param body
     */
    @Override
    public void getSuccess(Xiangqingbean body) {
           db = body.getRet();


        //title
        mTitlename.setText(db.getTitle().split(" ")[0] );
        //影片名字
        mPf.setText(db.getTitle());
        //时长
        mLx.setText(db.getDuration() );
        //类型
        mDq.setText(db.getVideoType());
        //图片
        Glide.with(this).load(db.getPic()).into(mVideoimg);
        //类型和地区暂无

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

        //Activity 向简介fragment 传值 Strign
        EventBus.getDefault().postSticky(db.getDescription()+","+db.getDirector()+","+db.getActors());
        //Activity 向推荐fragment 传值 list
        EventBus.getDefault().postSticky(db.getList().get(0).getChildList());
        //播放监听
        mVideobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(XqingActivity.this, StartActivity.class).putExtra("user",db));
            }
        });
    }
    /**
     * gettost
     */
    private void getToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
