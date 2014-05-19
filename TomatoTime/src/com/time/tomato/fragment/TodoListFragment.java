package com.time.tomato.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.time.tomato.R;
import com.time.tomato.adapter.TodoListAdapter;
import com.time.tomato.base.BaseFragment;
import com.time.tomato.db.DBHelper;
import com.time.tomato.db.DBUtil;
import com.time.tomato.entity.TomatoEntity;
import com.time.tomato.tools.Constants;

/**
 * 土豆列表
 */
public class TodoListFragment extends BaseFragment {
	private final static String TAG = "TodoListFragment";
	ArrayList<TomatoEntity> tomatoList;
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
//		tomatoList = Constants.getToastList();
		getTomatoList();
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
		View view_footer = LayoutInflater.from(activity).inflate(R.layout.view_fake_listview, null);
		AutoCompleteTextView edit_text = (AutoCompleteTextView)view_footer.findViewById(R.id.edit_text);
		edit_text.setHint("增加新土豆");
		lv_list.addFooterView(view_footer);
		mAdapter = new TodoListAdapter(activity, tomatoList);
		lv_list.setAdapter(mAdapter);
	}

	public void getTomatoList(){
		if(tomatoList == null){
			tomatoList = new ArrayList<TomatoEntity>();
		}
		Cursor cursor = null;
		cursor = DBUtil.getInstance(activity).selectData(null, DBHelper.ISFINISHED + "=?", new String[] { "0" }, null, null, null);
		while(cursor.moveToNext()){
			TomatoEntity tomato  = new TomatoEntity();
			tomato.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
			tomato.setContent(cursor.getString(cursor.getColumnIndex(DBHelper.CONTENT)));
			tomato.setIsTop(cursor.getLong((cursor.getColumnIndex(DBHelper.ISTOP))));
			tomato.setIsFinished(cursor.getLong((cursor.getColumnIndex(DBHelper.ISFINISHED))));
			tomato.setIsImportant(cursor.getLong((cursor.getColumnIndex(DBHelper.ISIMPORTANT))));
			tomatoList.add(tomato);
		}
		Log.d(TAG, "size = " + tomatoList.size());
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
