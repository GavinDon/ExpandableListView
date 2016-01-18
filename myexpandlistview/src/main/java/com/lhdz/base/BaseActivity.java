package com.lhdz.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 李南 on 2016/1/18  11:26
 * Email:fengyunzhinan@163.com
 */
public abstract class BaseActivity extends Activity implements BaseInterface {

    /**
     * 初始化接口中的方法
     */
    public void initialize() {
        initView();
        initData();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
