package com.jujitsutech.sansad.loader;

import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.jujitsutech.sansad.bean.ParliamentBills;
import com.jujitsutech.sansad.contenthandler.BillsHandler;
import com.jujitsutech.sansad.request.RequestExecutor;

public class BillsLoader extends AsyncTaskLoader<List<ParliamentBills>> {

	public BillsLoader(Context context) {
		super(context);
	}

	@Override
	public List<ParliamentBills> loadInBackground() {
		return new BillsHandler().parseContent(RequestExecutor
				.makeGetRequest("http://sansad.co/api/bills"));
	}

}
