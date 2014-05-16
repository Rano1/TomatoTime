package com.time.tomato.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.time.tomato.R;
import com.time.tomato.adapter.TodoListAdapter;
import com.time.tomato.base.BaseFragment;
import com.time.tomato.entity.ToastEntity;
import com.time.tomato.tools.Constants;

/**
 * Õ¡∂π¡–±Ì
 */
public class TodoListFragment extends BaseFragment {
	ArrayList<ToastEntity> toastList;
	TodoListAdapter mAdapter;
	ListView lv_list;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		toastList = Constants.getToastList();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_todolist,null);
		lv_list = (ListView) view.findViewById(R.id.lv_list);
		setList();
		return view;
	}

	private void setList() {
		mAdapter = new TodoListAdapter(activity, toastList);
		lv_list.setAdapter(mAdapter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
