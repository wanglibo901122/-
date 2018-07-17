package com.bawei.myvideo.sousuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.myvideo.R;
import com.bawei.myvideo.app.Myapp;
import com.bawei.myvideo.greenbean.Find;
import com.bawei.myvideo.sousuo.v.ShowfindmoreActivity;
import com.com.sky.downloader.greendao.FindDao;

import java.util.List;
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .........    佛祖保佑         永无BUG ..............

public class FindmoreActivity extends AppCompatActivity implements View.OnClickListener {
    private FlowLayout flow_layout;
    private TextView tagView;
    private EditText mEdit;
    private Button mFind;
    private FlowLayout mFlowLayout;
    private boolean flag = false;
    private FindDao findDao;
    /**
     * 清除搜索记录
     */
    private Button mDelall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findmore);
        //沉浸式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        findDao = Myapp.getDaoSession().getFindDao();
        initView();
        getData();
    }

    private void initView() {
        flow_layout = (FlowLayout) findViewById(R.id.flow_layout);
        mEdit = (EditText) findViewById(R.id.edit);
        mFind = (Button) findViewById(R.id.find);
        mFind.setOnClickListener(this);
        mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        mDelall = (Button) findViewById(R.id.delall);
        mDelall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.find:
                getview();
                break;
            case R.id.delall:
                findDao.deleteAll();
                flow_layout.removeAllViews();
                break;
        }
    }

    //创建布局
    private void getview() {
        String s = mEdit.getText().toString();
        if (s != null & !"".equals(s)) {
            List<Find> data = findDao.queryBuilder().where(FindDao.Properties.Name.eq(s)).build().list();
            if (data.size() == 0) {
                //添加进数据库
                Find find = new Find();
                find.setName(s);
                findDao.insert(find);
                //制空
                mEdit.setText("");
            }

            //携值跳转
            startActivity(new Intent(FindmoreActivity.this, ShowfindmoreActivity.class).putExtra("uid", s));

            finish();
        }

    }


    public void getData() {
        List<Find> list = findDao.queryBuilder().build().list();

        for (int i = 0; i < list.size(); i++) {

            tagView = (TextView) getLayoutInflater().inflate(R.layout.item, flow_layout, false);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            lp.leftMargin = 10;
            lp.rightMargin = 10;
            lp.topMargin = 10;
            tagView.setLayoutParams(lp);
            tagView.setText(list.get(i).getName());
            flow_layout.addView(tagView);
        }


    }
}
