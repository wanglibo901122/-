package com.bawei.myvideo.last;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.bawei.myvideo.R;
import com.bawei.myvideo.adapter.CollectionAdapter;
import com.bawei.myvideo.adapter.DataListAdapter;
import com.bawei.myvideo.app.Myapp;
import com.bawei.myvideo.greenbean.User;
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.com.sky.downloader.greendao.UserDao;

import java.lang.reflect.Field;
import java.util.List;

public class CollectionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgback;
    private ImageView mDel;
    private RecyclerView mMyrv;
    private AlertDialog dialog;
    private UserDao userDao;
    private List<User> list;
    private LinearLayout mLll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        userDao = Myapp.getDaoSession().getUserDao();

        //查询数据
        selectall();
    }

    private void selectall() {
        list = userDao.queryBuilder().orderDesc(UserDao.Properties.Id).build().list();
        CollectionAdapter adapter = new CollectionAdapter(list, this);
        mMyrv.setAdapter(adapter);
        mMyrv.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setOnRecyclerViewListener(new DataListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(CollectionActivity.this, XqingActivity.class).putExtra("url", list.get(position).getUid()));
                finish();
            }
        });
    }

    private void initView() {
        mImgback = (ImageView) findViewById(R.id.imgback);
        mImgback.setOnClickListener(this);
        mDel = (ImageView) findViewById(R.id.del);
        mDel.setOnClickListener(this);
        mMyrv = (RecyclerView) findViewById(R.id.myrv);
        mLll = (LinearLayout) findViewById(R.id.lll);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.imgback:
                //结束当前页面
                finish();
                break;
            case R.id.del:
                getdel();
                break;
        }
    }

    /**
     * 清空数据库
     */
    public void getdel() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("AlerDialog")
                .setMessage("是否确定清空我的收藏？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //清空数据库
                        userDao.deleteAll();
                        selectall();
                        //更换背景
                        mLll.setBackgroundResource(R.mipmap.bg_blue);
                        //刷新适配器
                        // adapter.notifyDataSetChanged();
                        //结束当前页面
                        getdia();
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
        //改变字体颜色
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(dialog);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(Color.RED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getdia() {
        {

            dialog = new AlertDialog.Builder(this)
                    .setTitle("AlerDialog")
                    .setMessage("当前已无数据是否返回？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //清空数据库

                            //刷新适配器
                            // adapter.notifyDataSetChanged();
                            //结束当前页面
                            finish();

                        }
                    })
                    .create();
            dialog.show();

            //改变字体颜色
            try {
                Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
                mAlert.setAccessible(true);
                Object mAlertController = mAlert.get(dialog);
                Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
                mMessage.setAccessible(true);
                TextView mMessageView = (TextView) mMessage.get(mAlertController);
                mMessageView.setTextColor(Color.RED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
