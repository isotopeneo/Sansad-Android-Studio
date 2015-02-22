package com.jujitsutech.sansad.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.jujitsutech.sansad.bean.ParliamentBills;
import com.jujitsutech.sansad.contenthandler.BillsHandler;
import com.jujitsutech.sansad.request.RequestExecutor;
import com.jujitsutech.sansad.util.LoggerClass;
import com.jujitsutech.sansad.util.Singleton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class BillsLoader extends AsyncTaskLoader<List<ParliamentBills>> {

    private Bundle arguments;

	public BillsLoader(Context context) {
		super(context);
	}

    public BillsLoader(Context context, Bundle arg) {
        super(context);
        this.arguments = arg;
    }

	@Override
	public List<ParliamentBills> loadInBackground() {
        if (null == arguments) {
            if (null == Singleton.data) {
                return new BillsHandler().parseContent(RequestExecutor
                        .makeGetRequest("http://sansad.co/api/bills"));
            } else {
                return Singleton.data;
            }
        } else {
            if (arguments.containsKey("title")) {

                String encodedString = arguments.getString("title");
                try {
                    encodedString = URLEncoder.encode(arguments.getString("title"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    LoggerClass.log(e.getMessage());
                }
                return new BillsHandler().parseContent(RequestExecutor
                        .makeGetRequest("http://sansad.co/api/bills?query=" + encodedString));

            } else {
                List<ParliamentBills> temp = new BillsHandler().parseContent(RequestExecutor
                        .makeGetRequest("http://sansad.co/api/bills?page=" + arguments.getInt("pageNumber")));
                for (ParliamentBills bill : temp) {
                    Singleton.data.add(bill);
                }
                return Singleton.data;
            }

        }
	}


}
