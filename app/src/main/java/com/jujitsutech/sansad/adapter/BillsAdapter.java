package com.jujitsutech.sansad.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jujitsutech.sansad.bean.ParliamentBills;
import com.jujitsutech.sansad.view.BillsHolderView;

public class BillsAdapter extends BaseAdapter{

	private Context context;
	private List<ParliamentBills> data;
	
	public BillsAdapter(Context context, List<ParliamentBills> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		if (null != data) {
			return data.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		BillsHolderView holderView;
		if (null != convertView && convertView instanceof BillsHolderView) {
			holderView = (BillsHolderView) convertView;
		} else {
			holderView = new BillsHolderView(context);
		}
		holderView.bindView(data.get(position).getTitle());
		return holderView;
	}

}
