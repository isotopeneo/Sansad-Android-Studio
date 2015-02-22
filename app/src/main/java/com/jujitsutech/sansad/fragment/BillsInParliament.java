package com.jujitsutech.sansad.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jujitsutech.sansad.MainActivity;
import com.jujitsutech.sansad.R;
import com.jujitsutech.sansad.adapter.BillsAdapter;
import com.jujitsutech.sansad.bean.ParliamentBills;
import com.jujitsutech.sansad.loader.BillsLoader;
import com.jujitsutech.sansad.util.Singleton;

import java.util.List;

public class BillsInParliament extends Fragment implements
		LoaderManager.LoaderCallbacks<List<ParliamentBills>>, AbsListView.OnScrollListener, OnTouchListener {

	private View view;
	ListView billsList;
	private final int LOADER_ID = 100;
    private static Context context;
    private int totalVisible;
    private int totalItemsCount;
    private BillsAdapter billsAdapter;

	public static BillsInParliament newInstance(Context reference) {
        context = reference;
		return new BillsInParliament();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        setRetainInstance(true);
		view = inflater.inflate(R.layout.bills_details, container, false);
        billsList = (ListView) view.findViewById(R.id.lv_bills);
        billsList.setOnScrollListener(this);
        billsList.setOnTouchListener(this);
		return view;
	}

    public void init(List<ParliamentBills> data) {
        billsAdapter = new BillsAdapter(getActivity(), data);
		billsList.setAdapter(billsAdapter);
        if (null == Singleton.data) {
            Singleton.data = data;
        }
        if (data.size() > 20) {
            billsList.smoothScrollToPosition((Singleton.currentPageNumber - 1) * 20);
        }
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(2);
        initiateLoader();
	}

	public void initiateLoader() {
		if (((MainActivity) context).isDeviceConnectedToNetwork()) {
            ((MainActivity) context).showProgressBar("Fetching Data");
            getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
		} else {
            ((MainActivity) context).showAlertDialog("Please check your internet connection");
		}
	}
	
	@Override
	public Loader<List<ParliamentBills>> onCreateLoader(int arg0, Bundle arg1) {
		return new BillsLoader(getActivity(), arg1);
	}

	@Override
	public void onLoadFinished(Loader<List<ParliamentBills>> arg0,
			List<ParliamentBills> data) {
        ((MainActivity) context).dismissProgressBar();
		getLoaderManager().destroyLoader(LOADER_ID);
		init(data);
	}

	@Override
	public void onLoaderReset(Loader<List<ParliamentBills>> arg0) {
	}

    public void searchBills(Bundle arg1) {
        ((MainActivity) context).showProgressBar("Fetching Data");
        getLoaderManager().initLoader(LOADER_ID, arg1, this).forceLoad();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.totalVisible = firstVisibleItem + visibleItemCount;
        this.totalItemsCount = totalItemCount;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                if (totalVisible == totalItemsCount) {
                    if (Singleton.currentPageNumber < Singleton.totalNumberOfPages()) {
                        ((MainActivity) context).showProgressBar("Fetching Data");
                        Bundle arguments = new Bundle();
                        arguments.putInt("pageNumber", Singleton.currentPageNumber + 1);
                        getLoaderManager().initLoader(LOADER_ID, arguments, this).forceLoad();
                    }
                }
                break;
        }
        return false;
    }
}
