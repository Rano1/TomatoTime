package com.time.tomato;

import java.util.ArrayList;

import com.time.tomato.adapter.TomatoPagerAdapter;
import com.time.tomato.base.BaseActivity;
import com.time.tomato.fragment.HistoryFragment;
import com.time.tomato.fragment.StatisticalFragment;
import com.time.tomato.fragment.TodoListFragment;
import com.time.tomato.tools.Constants;
import com.time.tomato.view.ProcessActionBar;
import com.time.tomato.view.TomatoViewPager;
import com.time.tomato.view.smoothprogressbar.SmoothProgressBar;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {
	private final static String TAG = "MainActivity";
	private TomatoViewPager pager;
	private TomatoPagerAdapter mAdapter;
	private FragmentManager fm;
	private TodoListFragment mTodoListFragment;
	private HistoryFragment mHistoryFragment;
	private StatisticalFragment mStatisticalFragment;
	private ArrayList<Fragment> fragmentList;
	private SmoothProgressBar top_progressBar;
	private View actionbar_shadow;
	private ProcessActionBar action_bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		initFragment();
		initView();
		initViewPager();
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				top_progressBar.setVisibility(View.GONE);
				actionbar_shadow.setVisibility(View.VISIBLE);
			}
		}, 2000);
	}

	private void initFragment() {
		fm = getSupportFragmentManager();
		mTodoListFragment = new TodoListFragment();
		mHistoryFragment = new HistoryFragment();
		mStatisticalFragment = new StatisticalFragment();
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mTodoListFragment);
		fragmentList.add(mHistoryFragment);
		fragmentList.add(mStatisticalFragment);
	}
	
	private void initViewPager() {
		mAdapter = new TomatoPagerAdapter(fm , fragmentList);
		pager.setAdapter(mAdapter);
	}
	
	private void initView() {
		action_bar = (ProcessActionBar)findViewById(R.id.action_bar);
		pager = (TomatoViewPager) findViewById(R.id.pager);
		top_progressBar = (SmoothProgressBar)findViewById(R.id.top_progressBar);
		actionbar_shadow = (View)findViewById(R.id.actionbar_shadow);
		actionbar_shadow.setVisibility(View.GONE);
		top_progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		//退出时候关闭服务
		stopService(new Intent(Constants.INTENT_AUTOSERVICE));
	}
}
