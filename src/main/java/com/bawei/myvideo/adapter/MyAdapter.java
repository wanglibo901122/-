package com.bawei.myvideo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 王利博 on 2018/6/24.
 */


public class MyAdapter extends FragmentPagerAdapter {
  private  Context context;
    private List<Fragment> list;
    private List<String> listStr;



    //有参构造
  public MyAdapter( FragmentManager fm, List<Fragment> list, List<String> listStr) {

         super(fm);

        this.list=list;
        this.listStr=listStr;
    }
    //每个Item
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    //Fragment的数量
    @Override
    public int getCount() {
        return list.size();
    }
    //返回标题
    @Override
    public CharSequence getPageTitle(int position) {
        return listStr.get(position);
    }
}
