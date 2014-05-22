package com.time.tomato.adapter;

import java.util.ArrayList;

import com.time.tomato.R;
import com.time.tomato.entity.TomatoEntity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class TodoListAdapter extends BaseAdapter {
	ArrayList<TomatoEntity> toastList;
	Activity activity;
	LayoutInflater inflater = null;
	
	public TodoListAdapter(Activity activity , ArrayList<TomatoEntity> toastList) {
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
	public TomatoEntity getItem(int position) {
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
		ViewHolder holder;
		if(view == null){
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_row_todolist_item, null);
			holder.important_color = (View)view.findViewById(R.id.important_color);
			holder.tv_list_item_content = (TextView)view.findViewById(R.id.tv_list_item_content);
			holder.btn_pin = (Button)view.findViewById(R.id.btn_pin);
			holder.checkBox_cir = (CheckBox)view.findViewById(R.id.checkBox_cir);
			view.setTag(holder);
		}else{
			holder = (ViewHolder)view.getTag();
		}
		TomatoEntity tomato = getItem(position);
		holder.tv_list_item_content.setText(tomato.getContent());
		if(tomato.getIsImportant() == 1){
			holder.important_color.setVisibility(View.VISIBLE);
		}else{
			holder.important_color.setVisibility(View.GONE);
		}
		return view;
	}
	
	public class ViewHolder{
		View important_color;
		TextView tv_list_item_content;
		Button btn_pin;
		CheckBox checkBox_cir;
	}
	/** 
	 * ½»»»Êý¾Ý
	 * */
	public void exchange(int from , int to){
		TomatoEntity fromTomato =  toastList.get(from);
		toastList.remove(from);
		toastList.add(to, fromTomato);
	}
	
	public void remove(int position){
		toastList.remove(position);
	}
}
