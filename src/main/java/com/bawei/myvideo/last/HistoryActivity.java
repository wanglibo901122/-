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
import com.bawei.myvideo.adapter.DataListAdapter;
import com.bawei.myvideo.adapter.HistoryAdapter;
import com.bawei.myvideo.app.Myapp;
import com.bawei.myvideo.greenbean.Person;
import com.bawei.myvideo.xiangqiang.XqingActivity;
import com.com.sky.downloader.greendao.PersonDao;

import java.lang.reflect.Field;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    // private Button mQuery;
    private PersonDao personDao;
    private ImageView mImgback;
    private RecyclerView mMyrv;
    private ImageView mDel;
    private AlertDialog dialog;
    private HistoryAdapter adapter;
    private List<Person> list;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        personDao = Myapp.getDaoSession().getPersonDao();
        mMyrv.setLayoutManager(new GridLayoutManager(this, 3));
        //查询数据

        que();
    }

    private void que() {
        list = personDao.queryBuilder().orderDesc(PersonDao.Properties.Id).build().list();
        //适配器
        adapter = new HistoryAdapter(list, this);
        mMyrv.setAdapter(adapter);

        adapter.setOnRecyclerViewListener(new DataListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(HistoryActivity.this, XqingActivity.class).putExtra("url", list.get(position).getUid()));
                finish();
            }
        });

    }

    private void initView() {
        mImgback = (ImageView) findViewById(R.id.imgback);
        mImgback.setOnClickListener(this);
        mMyrv = (RecyclerView) findViewById(R.id.myrv);
        mDel = (ImageView) findViewById(R.id.del);
        mDel.setOnClickListener(this);
        mLl = (LinearLayout) findViewById(R.id.ll);
    }

    @Override
    public void onClick(View view) {
        //结束当前页面

        switch (view.getId()) {
            case R.id.imgback:
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
                .setMessage("是否确定清空历史记录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //清空数据库
                        personDao.deleteAll();
                        que();
                        //更换背景
                    mLl.setBackgroundResource(R.mipmap.bg_blue);
                        //刷新适配器
                        // adapter.notifyDataSetChanged();
                        //结束当前页面
                        //   finish();
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
