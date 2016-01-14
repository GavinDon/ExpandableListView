package com.lhdz.myexpandlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李南 on 2016/1/14  10:47
 * Email:fengyunzhinan@163.com
 */
public class MainActivity extends Activity {


    private ExpandableListView mExpandableListView;
    //父控件数据
    private List<String> firstList;
    //子控件数据
    private List<List<String>> secondList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
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
                return false;
            }
        });
    }

    /**
     * 设置一些测试数据
     */
    private void initData() {
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
    private void initView() {
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandlist);
        mExpandableListView.setGroupIndicator(null);

    }


}
