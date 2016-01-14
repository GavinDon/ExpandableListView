package com.lhdz.myexpandlistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 李南 on 2016/1/14  10:55
 * Email:fengyunzhinan@163.com
 */
public class MyExpandAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    //父控件数据
    private List<String> firstList;
    //展开的子类控件数据
    private List<List<String>> secondList;

    public MyExpandAdapter(Context mContext, List<String> firstList, List<List<String>> secondList) {
        this.mContext = mContext;
        this.firstList = firstList;
        this.secondList = secondList;
    }

    @Override
    public int getGroupCount() {
        return firstList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return secondList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return firstList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return secondList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 表明儿童和组id是否在改变底层数据是稳定的（直接从API中翻译过来的，不懂什么意思）
     *
     * @return 是否相同的ID总是指向的是同一个对象
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.group, null);
            vh = new ViewHolder();
            vh.vh_textView = (TextView) convertView.findViewById(R.id.tv_group);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.vh_textView.setTextSize(20);
        vh.vh_textView.setTextColor(Color.parseColor("#ff0660"));
        vh.vh_textView.setText(firstList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.group, null);
            vh = new ViewHolder();
            vh.vh_textView = (TextView) convertView.findViewById(R.id.tv_group);
            convertView.setTag(vh);


        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.vh_textView.setText(secondList.get(groupPosition).get(childPosition));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class ViewHolder {
        TextView vh_textView;
    }
}
