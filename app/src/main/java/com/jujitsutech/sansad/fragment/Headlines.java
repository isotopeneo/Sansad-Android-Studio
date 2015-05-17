package com.jujitsutech.sansad.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jujitsutech.sansad.MainActivity;
import com.jujitsutech.sansad.R;
import com.jujitsutech.sansad.adapter.HeadlinesAdapter;
import com.jujitsutech.sansad.loader.HeadlinesLoader;
import com.jujitsutech.sansad.util.Singleton;

import java.util.List;

public class Headlines extends Fragment implements LoaderManager.LoaderCallbacks<List<com.jujitsutech.sansad.bean.Headlines>>{

	private View view;
	private static Context context;
    private ListView headlinesList;
    private HeadlinesAdapter headlinesAdapter;
    private final int LOADER_ID = 100;

	public static Headlines newInstance(Context reference) {
		context = reference;
        Headlines fragment = new Headlines();
		return fragment;
	}

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        setRetainInstance(true);
		view = inflater.inflate(R.layout.headlines_details, container, false);
        headlinesList = (ListView) view.findViewById(R.id.list_news_view1);
		return view;
	}

	public void init(List<com.jujitsutech.sansad.bean.Headlines> data) {
        headlinesAdapter = new HeadlinesAdapter(getActivity(), data);
        headlinesList.setAdapter(headlinesAdapter);
        if (null == Singleton.headlinesList) {
            Singleton.headlinesList = data;
        }
        if (null != data) {
            if (data.size() > 20) {
                headlinesList.smoothScrollToPosition((Singleton.currentPageNumber - 1) * 20);
            }
        }
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(1);
        initiateLoader();
	}

    public void initiateLoader(){
        if (((MainActivity) context).isDeviceConnectedToNetwork()) {
            ((MainActivity) context).showProgressBar("Fetching Data");
            getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
        } else {
            ((MainActivity) context).showAlertDialog("Please check your internet connection");
        }
    }

    @Override
    public Loader<List<com.jujitsutech.sansad.bean.Headlines>> onCreateLoader(int id, Bundle args) {
        return new HeadlinesLoader(getActivity(), args);
    }

    @Override
    public void onLoadFinished(Loader<List<com.jujitsutech.sansad.bean.Headlines>> loader, List<com.jujitsutech.sansad.bean.Headlines> data) {
        ((MainActivity) context).dismissProgressBar();
        getLoaderManager().destroyLoader(LOADER_ID);
        init(data);
    }

    @Override
    public void onLoaderReset(Loader<List<com.jujitsutech.sansad.bean.Headlines>> loader) {

    }
}
