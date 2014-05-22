package com.time.tomato.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Int2;
import android.text.TextUtils;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.time.tomato.MainActivity;
import com.time.tomato.R;
import com.time.tomato.adapter.TodoListAdapter;
import com.time.tomato.app.AppApplication;
import com.time.tomato.base.BaseFragment;
import com.time.tomato.db.DBHelper;
import com.time.tomato.db.DBUtil;
import com.time.tomato.entity.TomatoEntity;
import com.time.tomato.tools.Constants;
import com.time.tomato.view.DragListView;
import com.time.tomato.view.rotatemenu.ArcMenu;

/**
 * 土豆列表
 */
public class TodoListFragment extends BaseFragment {
	private final static String TAG = "TodoListFragment";
	ArrayList<TomatoEntity> tomatoList;
	TodoListAdapter mAdapter;
	DragListView lv_list;
	ImageView btn_menu;
	/** 圆环菜单 */
	ArcMenu arc_menu;
	int[] rotateDrawables;
	
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
		rotateDrawables = new int[]{R.drawable.ic_rotate_add , R.drawable.ic_rotate_refresh};
		getTomatoList();
	}
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = LayoutInflater.from(activity).inflate(R.layout.frm_todolist,null);
		lv_list = (DragListView) view.findViewById(R.id.lv_list);
		arc_menu = (ArcMenu)view.findViewById(R.id.arc_menu);
		btn_menu = (ImageView) view.findViewById(R.id.btn_menu);
		btn_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initPopupWindow(v);
			}
		});
		setList();
		initArcMenu(arc_menu, rotateDrawables);
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
		tomatoList.clear();
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
		final PopupWindow popupWindow = new PopupWindow(view_pop , LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT);
		final EditText edit_text = (EditText)view_pop.findViewById(R.id.edit_text);
		Button btn_add_cancel = (Button)view_pop.findViewById(R.id.btn_add_cancel);
		Button btn_add_confirm = (Button)view_pop.findViewById(R.id.btn_add_confirm);
		//取消
		btn_add_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
		//确认
		btn_add_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String content = edit_text.getText().toString();
				if(!TextUtils.isEmpty(content)){
					ContentValues values = new ContentValues();
					values.put(DBHelper.ID, "1");
					values.put(DBHelper.CONTENT, content);
					values.put(DBHelper.ISFINISHED, "0");
					values.put(DBHelper.ISIMPORTANT, "0");
					values.put(DBHelper.ISTOP, "0");
					DBUtil.getInstance(activity).insertData(values);
					TomatoEntity tomato  = new TomatoEntity();
					tomato.setId(1);
					tomato.setId(1);
					tomato.setContent(content);
					tomato.setIsFinished(0L);
					tomato.setIsImportant(0L);
					tomato.setIsTop(0L);
					tomatoList.add(tomato);
					popupWindow.dismiss();
					Toast.makeText(activity, "添加成功！", Toast.LENGTH_SHORT).show();
					mAdapter.notifyDataSetChanged();
				}else{
					Toast.makeText(activity, "土豆内容不能为空！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		ScaleAnimation animation = new ScaleAnimation(0, 100, 0, 100);
		popupWindow.setAnimationStyle(R.anim.slide_buttom_in);
		popupWindow.setFocusable(true);  
		popupWindow.showAtLocation(view ,Gravity.TOP,0,100);
		edit_text.setFocusable(true);
		edit_text.setFocusableInTouchMode(true);
		Log.d(TAG, "onclick");
	}
	
	/** 初始化菜单 */
	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
        final int itemCount = itemDrawables.length;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(activity);
            item.setImageResource(itemDrawables[i]);

            final int position = i;
            menu.addItem(item, new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(position == 0){
                    	initPopupWindow(view);
                    }else{
                    	((MainActivity)activity).showProgressBar(true);
                    	getTomatoList();
                    	new Handler().postDelayed(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								((MainActivity)activity).showProgressBar(false);
								mAdapter.notifyDataSetChanged();
								Toast.makeText(activity, "数据已更新！", Toast.LENGTH_SHORT).show();
							}
						}, 2000L);
                    }
                }
            });
        }
    }
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
