package com.bawei.myvideo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.bawei.myvideo.adapter.MyListAdapter;
import com.bawei.myvideo.faxian.FoundFragment;
import com.bawei.myvideo.fuli.FuliActivity;
import com.bawei.myvideo.home.v.HomeFragment;
import com.bawei.myvideo.last.CollectionActivity;
import com.bawei.myvideo.me.MyFragment;
import com.bawei.myvideo.net.ResideLayout;
import com.bawei.myvideo.zhuanti.v.ZhuantiFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hjm.bottomtabbar.BottomTabBar;
import com.mikepenz.iconics.view.IconicsTextView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener, ColorChooserDialog.ColorCallback {

    private BottomTabBar mBtb;
    private List<String> list = new ArrayList<>();
    private ListView mMenu;
    private AlertDialog dialog;
    private SimpleDraweeView mSimpGif;
    /**
     * {faw_share} 关于
     */
    private IconicsTextView mMainGuanyu;
    /**
     * {faw_share} 主题
     */
    private IconicsTextView mMainZhuti;
    private ResideLayout mResidelayout;
    /**
     * 小闲猫
     */
    private TextView mName;
    private SharedPreferences user;
    private LinearLayout mLine;
    private LinearLayout mLine1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.activity_show);
        initView();
        //初始化颜色
        initColor();
        mBtb.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("精选", R.mipmap.found_select, R.mipmap.found, HomeFragment.class)
                .addTabItem("专题", R.mipmap.special_select, R.mipmap.special, ZhuantiFragment.class)
                .addTabItem("发现", R.mipmap.fancy_select, R.mipmap.fancy, FoundFragment.class)
                .addTabItem("我的", R.mipmap.my_select, R.mipmap.my, MyFragment.class)
                .isShowDivider(true);
//侧拉框里面的数据
        list.add("{ion_android_list}  我的收藏");
        list.add("{ion_android_download}  我的下载");
        list.add("{ion_android_happy}  福利");
        list.add("{gmd_3d_rotation}  分享");
        list.add("{gmd_adb}  建议反馈");
        list.add("{faw_cog}  设置");
        list.add("{faw_th_list}  电影列表");
        //适配
        mMenu.setAdapter(new MyListAdapter(this, list));


        //点击吐司并关闭抽屉
        mMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:

                        startActivity(new Intent(ShowActivity.this, CollectionActivity.class));
                        break;
                    case 1:
                        getTosat(1);
                        break;
                    case 2:

                        //跳转到福利页面
                        Intent intent = new Intent(ShowActivity.this, FuliActivity.class);
                        startActivity(intent);
                        break;
                    case 3:

                        //分享
                        getShard();
                        break;
                    case 4:

                        //反馈
                        getFeedback();
                        break;
                    case 5:
                        getTosat(5);
                        break;
                    case 6:
                        getTosat(6);
                        break;
                }
                //关闭抽屉
                mResidelayout.closePane();
            }
        });

        user = getSharedPreferences("User", MODE_PRIVATE);
        //设置动态图
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //设置uri,加载本地的gif资源
                .setUri(Uri.parse("res://" + getPackageName() + "/" + R.mipmap.timg))//设置uri
                .build();
        //设置Controller
        mSimpGif.setController(mDraweeController);

    }

    /**
     * 初始化
     */
    private void initColor() {


        mMainZhuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //颜色的数组
                int[] primary = new int[]{
                        Color.parseColor("#F44336"),
                        Color.parseColor("#FF0000"),
                        Color.parseColor("#FFFF00"),
                        Color.parseColor("#00FF00"),
                        Color.parseColor("#0000FF"),
                        Color.parseColor("#00FFFF"),
                        Color.parseColor("#FF00FF"),
                        Color.parseColor("#ff6600"),
                        Color.parseColor("#ff9966"),
                        Color.parseColor("#cc0000"),
                        Color.parseColor("#993399"),
                        Color.parseColor("#cc6699"),
                        Color.parseColor("#ffccff"),
                        Color.parseColor("#cc66cc"),
                        Color.parseColor("#cc33cc"),
                        Color.parseColor("#00ff33"),
                        Color.parseColor("#3399cc"),
                        Color.parseColor("#0066ff"),
                        Color.parseColor("#0099ff"),
                        Color.parseColor("#00cc99")
                };

                //有些按钮是系统默认的
                new ColorChooserDialog.Builder(ShowActivity.this, R.string.color_palette)
                        .accentMode(true)//
                        .customColors(primary, null)//两个颜色数组
                        .dynamicButtonColor(true)//动态按钮颜色
                        .customButton(0)//设置颜色不显示
                        .cancelButton(R.string.cancle)
                        .doneButton(R.string.done)
                        .show(ShowActivity.this);//传入上下文

            }
        });
    }

    /**
     * 反馈按钮
     */
    private void getFeedback() {
        final EditText et = new EditText(this);
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(99)});
        et.setSingleLine(false);
        new AlertDialog.Builder(this).setTitle("请输入消息")
                // .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        Toast.makeText(getApplicationContext(), et.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("取消", null).show();
    }

    /**
     * 分享
     */
    private void getShard() {
        //分享网址
        UMWeb umWeb = new UMWeb("https://blog.csdn.net/jonly_w");
        new ShareAction(ShowActivity.this).withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShowActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShowActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShowActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 吐司的方法
     *
     * @param i
     */
    private void getTosat(int i) {
        Toast.makeText(this, list.get(i), Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mBtb = (BottomTabBar) findViewById(R.id.btb);
        mMenu = (ListView) findViewById(R.id.menu);
        //SharedPreferences

        mSimpGif = (SimpleDraweeView) findViewById(R.id.simp_gif);
        mSimpGif.setOnClickListener(this);
        mMainGuanyu = (IconicsTextView) findViewById(R.id.main_guanyu);
        mMainGuanyu.setOnClickListener(this);
        mMainZhuti = (IconicsTextView) findViewById(R.id.main_zhuti);

        mResidelayout = (ResideLayout) findViewById(R.id.residelayout);
        mName = (TextView) findViewById(R.id.name);

        mLine = (LinearLayout) findViewById(R.id.line);
        mLine1 = (LinearLayout) findViewById(R.id.line1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.simp_gif:


                getLogin();
                break;
            case R.id.main_guanyu:
                Toast.makeText(this, mMainGuanyu.getText().toString(), Toast.LENGTH_SHORT).show();
                getGuanyu();
                break;

        }
    }


    /**
     * 三方登录
     */
    private void getLogin() {

        //第三方登录
        UMShareAPI.get(ShowActivity.this)
                .getPlatformInfo(ShowActivity.this,
                        SHARE_MEDIA.QQ, umAuthListener);
    }

    /**
     * 侧拉框底部关于按钮的数据
     */
    private void getGuanyu() {
        dialog = new AlertDialog.Builder(this)
                //  .setTitle("AlerDialog")
                .setMessage("今天不想敲代码额，明天再继续吧....今天不想敲代码额，明天再继续吧.....")
                //   .setPositiveButton("确定",null)
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

    /**
     * QQ登录回调监听
     */
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            //Toast.makeText(ContentActivity.this, "成功了", Toast.LENGTH_SHORT).show();

            mSimpGif.setImageURI(Uri.parse(map.get("iconurl")));
            mName.setText(map.get("name"));
            //  Log.e("'aa", map.toString());
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(ShowActivity.this, "失败了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    //QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中，添加：
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {
        mLine.setBackgroundColor(selectedColor);
        mLine1.setBackgroundColor(selectedColor);

        user.edit().putInt("color", selectedColor).commit();

    }

    @Override
    public void onColorChooserDismissed(@NonNull ColorChooserDialog dialog) {

    }
}
