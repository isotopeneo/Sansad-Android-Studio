package com.jujitsutech.sansad.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jujitsutech.sansad.bean.Headlines;
import com.jujitsutech.sansad.view.HeadlinesHolderView;

import java.util.List;

/**
 * Created by Sujit Devkar on 24-02-2015.
 */
public class HeadlinesAdapter extends BaseAdapter {

    private Context context;
    private List<Headlines> data;

    public HeadlinesAdapter(Context context, List<Headlines> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data != null){
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeadlinesHolderView holderView;
        if(null != convertView && convertView instanceof HeadlinesHolderView){
            holderView = (HeadlinesHolderView) convertView;
        }
        else {
            holderView = new HeadlinesHolderView(context);
        }
        holderView.bindView(data.get(position).getHeadline());
        return holderView;
    }
}