package com.jujitsutech.sansad.fragment;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jujitsutech.sansad.MainActivity;
import com.jujitsutech.sansad.R;
import com.jujitsutech.sansad.adapter.BillsAdapter;
import com.jujitsutech.sansad.bean.ParliamentBills;
import com.jujitsutech.sansad.loader.BillsLoader;

public class BillsInParliament extends Fragment implements
		LoaderManager.LoaderCallbacks<List<ParliamentBills>> {

	private View view;
	ListView billsList;
	private final int LOADER_ID = 100;
    private List<ParliamentBills> data;

	public static BillsInParliament newInstance() {
		return new BillsInParliament();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        setRetainInstance(true);
		view = inflater.inflate(R.layout.bills_details, container, false);
		return view;
	}

    public void init(List<ParliamentBills> data) {
		billsList = (ListView) view.findViewById(R.id.lv_bills);
		billsList.setAdapter(new BillsAdapter(getActivity(), data));
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(2);
        initiateLoader();
	}

	public void initiateLoader() {
        if (null == data) {
            getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
        } else {
            init(data);
        }
		/*
		if (application.isDeviceConnectedToNetwork()) {
			showProgressBar("Fetching results for " + searchEditText.getText().toString());
			getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
		} else {
			dismissProgressBar();
			application.showAlertDialog("Please check your internet connection", getActivity());
		}
		*/
	}
	
	@Override
	public Loader<List<ParliamentBills>> onCreateLoader(int arg0, Bundle arg1) {
		return new BillsLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<ParliamentBills>> arg0,
			List<ParliamentBills> data) {
        this.data = data;
		getLoaderManager().destroyLoader(LOADER_ID);
		init(data);
	}

	@Override
	public void onLoaderReset(Loader<List<ParliamentBills>> arg0) {
	}
}
