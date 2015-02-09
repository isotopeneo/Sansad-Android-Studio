package com.jujitsutech.sansad.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jujitsutech.sansad.MainActivity;
import com.jujitsutech.sansad.R;

public class Headlines extends Fragment {

	private View view;
	
	public static Headlines newInstance() {
		Headlines fragment = new Headlines();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.headlines_details, container, false);
		init();
		return view;
	}
	
	public void init() {
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(1);
	}
}
