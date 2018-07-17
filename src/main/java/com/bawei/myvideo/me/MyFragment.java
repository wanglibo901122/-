package com.bawei.myvideo.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.myvideo.R;
import com.bawei.myvideo.ShowActivity;
import com.bawei.myvideo.adapter.MyListAdapter;
import com.bawei.myvideo.app.Myapp;
import com.bawei.myvideo.fuli.FuliActivity;
import com.bawei.myvideo.greenbean.Person;
import com.bawei.myvideo.greenbean.User;
import com.bawei.myvideo.last.CollectionActivity;
import com.bawei.myvideo.last.HistoryActivity;
import com.com.sky.downloader.greendao.PersonDao;
import com.com.sky.downloader.greendao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王利博 on 2018/6/19.
 */

public class MyFragment extends Fragment {
    private List<String> list = new ArrayList<>();
    private View view;
    private ListView mLv;
    private PersonDao personDao;
    private UserDao userDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_me, container, false);
        personDao = Myapp.getDaoSession().getPersonDao();
        userDao = Myapp.getDaoSession().getUserDao();
        list.add("{ion_android_list}       历  史              ----->");
        list.add("{ion_android_download}      缓  存              ----->");
        list.add("{ion_android_happy}      收  藏              ----->");
        list.add("{faw_th_list}      福  利              ----->");


        initView(view);
        mLv.setAdapter(new MyListAdapter(getContext(), list));
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 switch (i){
                     case 0:
                         //查询数据
                       List<Person> list = personDao.queryBuilder().orderDesc(PersonDao.Properties.Id).build().list();
                        if(list.size()>0){
                            startActivity(new Intent(getActivity(), HistoryActivity.class));
                        }else {
                           getToast("当前没有历史观看记录");
                        }

                         break;
                     case 1:
                         getToast("缓存没有");
                         break;
                     case 2:

                         List<User> data = userDao.queryBuilder().orderDesc(UserDao.Properties.Id).build().list();
                         if(data.size()>0){
                             startActivity(new Intent(getActivity(), CollectionActivity.class));
                         }else {
                             getToast("当前没有收藏");
                         }
                         break;
                     case 3:
                         //跳转福利页面
                         startActivity( new Intent(getActivity(), FuliActivity.class));
                         break;

                 }

            }
        });
        return view;
    }

    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
    }

    /**
     * 吐司提示
     * @param str
     */
    private void getToast(String str){
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
}
