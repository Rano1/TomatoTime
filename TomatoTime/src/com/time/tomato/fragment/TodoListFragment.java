package com.time.tomato.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

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
	ImageView btn_menu;
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
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = LayoutInflater.from(activity).inflate(R.layout.frm_todolist,null);
		lv_list = (ListView) view.findViewById(R.id.lv_list);
		btn_menu = (ImageView) view.findViewById(R.id.btn_menu);
		btn_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initPopupWindow(v);
			}
		});
		setList();
		return view;
	}

	private void setList() {
//		View view_footer = LayoutInflater.from(activity).inflate(R.layout.view_fake_listview, null);
//		AutoCompleteTextView edit_text = (AutoCompleteTextView)view_footer.findViewById(R.id.edit_text);
//		edit_text.setHint("增加新土豆");
//		lv_list.addFooterView(view_footer);
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
	
	public void initPopupWindow(View view){
		View view_pop = LayoutInflater.from(activity).inflate(R.layout.view_pop_add, null);
		PopupWindow popupWindow = new PopupWindow(view_pop , LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT);
		EditText edit_text = (EditText)view_pop.findViewById(R.id.edit_text);
//		ScaleAnimation animation = new ScaleAnimation(0, 100, 0, 100);
//		popWindow.setAnimationStyle(R.anim.slide_buttom_in);
		popupWindow.showAtLocation(view ,Gravity.TOP,0,100);
		popupWindow.update(); 
		popupWindow.setFocusable(true);  
		edit_text.setFocusable(true);
		edit_text.setFocusableInTouchMode(true);
		Log.d(TAG, "onclick");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
