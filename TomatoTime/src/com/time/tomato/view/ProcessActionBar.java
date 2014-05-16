package com.time.tomato.view;

import com.time.tomato.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class ProcessActionBar extends LinearLayout {

	public ProcessActionBar(Context context) {
		super(context);
		init(context);
	}

	public ProcessActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.process_actionbar, null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		addView(view,params);
	}
}
