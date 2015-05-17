package com.jujitsutech.sansad.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jujitsutech.sansad.R;
import com.jujitsutech.sansad.bean.Headlines;

import java.util.jar.Attributes;

/**
 * Created by Sujit Devkar on 24-02-2015.
 */
public class HeadlinesHolderView extends GridLayout {

    private TextView title;

    public HeadlinesHolderView(Context context, AttributeSet attributes){
        super(context,attributes);
        init(context);
    }

    public HeadlinesHolderView(Context context){
        super(context);
        init(context);
    }

    public void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_news, this);
        title = (TextView) view.findViewById(R.id.list_news_textView1);
    }

    public void bindView(String headlineTitle){
        title.setText(headlineTitle);
    }

}
