package com.lhdz.myexpandlistview;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.lhdz.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李南 on 2016/1/14  10:47
 * Email:fengyunzhinan@163.com
 */
public class MainActivity extends BaseActivity {


    private ExpandableListView mExpandableListView;
    //父控件数据
    private List<String> firstList;
    //子控件数据
    private List<List<String>> secondList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        setContentView(R.layout.activity_main);
        initialize();
        // 给expandableListView设置适配器;
        MyExpandAdapter adapter = new MyExpandAdapter(this, firstList, secondList);
        mExpandableListView.setAdapter(adapter);
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                parent.getAdapter().getItem(groupPosition);
                MyExpandAdapter.ViewHolder vh;
                vh = (MyExpandAdapter.ViewHolder) parent.getTag();

                Toast.makeText(MainActivity.this, secondList.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                initData();

                return false;
            }
        });
    }

    /**
     * 设置一些测试数据
     */
    @Override
    public void initData() {
        firstList = new ArrayList<>();
        secondList = new ArrayList<List<String>>();
        addData("西天", new String[]{"孙悟空", "唐僧", "沙僧"});
        addData("大秦", new String[]{"张仪", "芈八子", "吴起"});
        addData("三国", new String[]{"诸葛亮", "周瑜", "曹操"});


    }

    public void addData(String first, String[] secondArray) {
        firstList.add(first);
        List<String> adds = new ArrayList<>();
        for (int i = 0; i < secondArray.length; i++) {
            adds.add(secondArray[i]);
        }
        secondList.add(adds);
    }

    /**
     * 控件的初始化
     */
    @Override
    public void initView() {
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandlist);
        mExpandableListView.setGroupIndicator(null);
        //获取Android_id
        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        //获取android_IMEI唯一号
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
    }

    private void setTranslucentStatus() {
        //若版本号大于4.4（奇巧）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            SystemStatusManager tintManager = new SystemStatusManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            // 设置状态栏的颜色
//            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
//            getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }


}
