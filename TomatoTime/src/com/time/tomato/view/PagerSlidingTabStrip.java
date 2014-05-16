package com.time.tomato.view;

import com.time.tomato.tools.BaseTools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class PagerSlidingTabStrip extends HorizontalScrollView {
	private int bar_tool_red = Color.parseColor("#FF0033");
	private int bar_tool_gray = Color.parseColor("#666666");
	private LinearLayout tabsContainer;

	public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PagerSlidingTabStrip(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		tabsContainer = new LinearLayout(context);
		tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
		tabsContainer.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(tabsContainer);
		initTabs();
	}

	public void initTabs(){
		tabsContainer.removeAllViews();
		for(int i =0 ; i< 3 ; i++){
			addTab(i, "title" + i);
		}
	}
	
	public void addTab(int tabs_id, String tab_title) {
		TextView tv = new TextView(getContext());
		tv.setWidth(BaseTools.getWindowWidth((Activity)getContext()) / 3);
		tv.setGravity(Gravity.CENTER);
		tv.setFocusable(true);
//		tv.setSingleLine();tv.setOnClickListener(l)
		tv.setText(tab_title);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		tabsContainer.addView(tv);
	}
}
