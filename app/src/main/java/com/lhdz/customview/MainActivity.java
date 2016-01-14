package com.lhdz.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    int data[] = {34, 35, 26, 89, 15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quick_sort(data, 0, data.length - 1);


    }

    public void quick_sort(int s[], int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) { // 从右向左找第一个小于x的数
                    j--;
                    if (i < j) {
                        s[i++] = s[j];
                        Log.i("ss", s[j] + "");
                    }
                }
                while (i < j && s[i] < x) { // 从左向右找第一个大于等于x的数
                    i++;
                    if (i < j) {
                        s[j--] = s[i];
                        Log.i("ss2", s[i] + "");
                    }
                }
                s[i] = x;
                quick_sort(s, l, i - 1); // 递归调用
                quick_sort(s, i + 1, r);
            }

        }
    }
}
