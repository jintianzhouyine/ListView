package com.example.mycomputer.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;

import java.util.List;

/**
 * Created by mycomputer on 2017/6/19.
 */

/**
 * 数据的适配类
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {
    /**
     * Data
     */
    private List<SortModel> list = null;
    private Context mContext;

    public SortAdapter(List<SortModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    /**
     * 当ListView数据发生变化是，调用此方法更新ListView
     */
    public void updateListView(List<SortModel> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int i) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }
}
