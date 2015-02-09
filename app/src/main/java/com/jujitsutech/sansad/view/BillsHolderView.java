package com.jujitsutech.sansad.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jujitsutech.sansad.R;

public class BillsHolderView extends GridLayout {

	private TextView title;
	
	public BillsHolderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BillsHolderView(Context context) {
		super(context);
		init(context);
	}
	
	public void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.row_item_bill, this);
		title = (TextView) view.findViewById(R.id.billTitle);
	}
	public void bindView(String billTitle) {
		title.setText(billTitle);
	}
}
