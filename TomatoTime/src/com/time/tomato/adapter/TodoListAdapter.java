package com.time.tomato.adapter;

import java.util.ArrayList;

import com.time.tomato.R;
import com.time.tomato.entity.ToastEntity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoListAdapter extends BaseAdapter {
	ArrayList<ToastEntity> toastList;
	Activity activity;
	LayoutInflater inflater = null;
	
	public TodoListAdapter(Activity activity , ArrayList<ToastEntity> toastList) {
		this.activity = activity;
		this.toastList = toastList;
		inflater = LayoutInflater.from(activity);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return toastList == null ? 0 : toastList.size();
	}

	@Override
	public ToastEntity getItem(int position) {
		if(toastList != null && toastList.size() !=0){
			return toastList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			view = inflater.inflate(R.layout.list_row_todolist_item, null);
		}
		TextView textView = (TextView)view.findViewById(R.id.textView);
		View important_color = (View)view.findViewById(R.id.important_color);
		
		ToastEntity toast = getItem(position);
		textView.setText(toast.getContent());
		if(toast.getIsImportant()){
			important_color.setVisibility(View.VISIBLE);
		}else{
			important_color.setVisibility(View.GONE);
		}
		return view;
	}

}
