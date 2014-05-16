package com.time.tomato.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	/** ¹ÜÀíµÄActivity */
	public Activity activity; 
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.activity = activity;
	}

}
