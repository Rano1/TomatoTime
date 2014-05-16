package com.time.tomato;

import java.util.ArrayList;

import com.time.tomato.adapter.ToastPagerAdapter;
import com.time.tomato.base.BaseActivity;
import com.time.tomato.fragment.HistoryFragment;
import com.time.tomato.fragment.StatisticalFragment;
import com.time.tomato.fragment.TodoListFragment;
import com.time.tomato.view.ToastViewPager;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	private ToastViewPager pager;
	private ToastPagerAdapter mAdapter;
	private FragmentManager fm;
	private TodoListFragment mTodoListFragment;
	private HistoryFragment mHistoryFragment;
	private StatisticalFragment mStatisticalFragment;
	private ArrayList<Fragment> fragmentList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		initFragment();
		initView();
		initViewPager();
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
		mAdapter = new ToastPagerAdapter(fm , fragmentList);
		pager.setAdapter(mAdapter);
	}
	
	private void initView() {
		pager = (ToastViewPager) findViewById(R.id.pager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
