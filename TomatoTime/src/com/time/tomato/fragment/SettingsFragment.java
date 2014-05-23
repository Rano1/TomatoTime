package com.time.tomato.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.time.tomato.R;
import com.time.tomato.R.style;
import com.time.tomato.app.AppApplication;
import com.time.tomato.base.BaseFragment;
import com.time.tomato.tools.Constants;
import com.time.tomato.view.wheel.ArrayWheelAdapter;
import com.time.tomato.view.wheel.NumericWheelAdapter;
import com.time.tomato.view.wheel.OnWheelChangedListener;
import com.time.tomato.view.wheel.WheelView;
/**
 *	自定义土豆 
 */
public class SettingsFragment extends BaseFragment implements OnClickListener{
	Button edt_worktime;
	Button edt_resttime;
	long worktime;
	long resttime;
	Button btn_save;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_settings, null);
		edt_worktime = (Button)view.findViewById(R.id.edt_worktime);
		edt_resttime = (Button)view.findViewById(R.id.edt_resttime);
		btn_save = (Button)view.findViewById(R.id.btn_save);
		edt_worktime.setOnClickListener(this);
		edt_resttime.setOnClickListener(this);
		btn_save.setOnClickListener(this);
		initData();
		return view;
	}

	/** 初始化滚轮 */
	private void initWorkWheel() {
		View view_wheel = LayoutInflater.from(activity).inflate(R.layout.view_wheel_time, null);
		Button btn_wheel_confirm = (Button)view_wheel.findViewById(R.id.btn_wheel_confirm);
		final Dialog dialog_wheel = new Dialog(activity, style.dialog_wheel);
		dialog_wheel.setContentView(view_wheel);
		dialog_wheel.show();
		//分，秒的WheelView
		final WheelView wheelView_minutes = (WheelView)view_wheel.findViewById(R.id.wheelView_minutes);
		final WheelView wheelView_seconds = (WheelView)view_wheel.findViewById(R.id.wheelView_seconds);
		//分
		wheelView_minutes.setAdapter(new ArrayWheelAdapter<String>(Constants.array_time));
		wheelView_minutes.setLabel("分");
		wheelView_minutes.setCyclic(false);//不循环
		wheelView_minutes.setCurrentItem(1);
		//秒
		wheelView_seconds.setAdapter(new ArrayWheelAdapter<String>(Constants.array_time));
		wheelView_seconds.setLabel("秒");
		wheelView_seconds.setCyclic(false);//不循环
		wheelView_seconds.setCurrentItem(1);
		
		btn_wheel_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				worktime = Long.valueOf(Constants.array_time[wheelView_minutes.getCurrentItem()]);
				edt_worktime.setText(String.valueOf(worktime));
				dialog_wheel.dismiss();
			}
		});
		
	}

	private void initData() {
		worktime = AppApplication.getApp().getWorkTime();
		resttime = AppApplication.getApp().getRestTime();
		edt_worktime.setText(String.valueOf(worktime));
		edt_resttime.setText(String.valueOf(resttime));
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_worktime:
			initWorkWheel();
			break;
		case R.id.edt_resttime:
			initWorkWheel();
			break;
		case R.id.btn_save:
			if(AppApplication.getApp().isServiceRunning()){
				Toast.makeText(activity, "服务正在运行，请先停止服务再进行设置！", Toast.LENGTH_SHORT).show();
			}else{
				AppApplication.getApp().setWorkTime(worktime);
				AppApplication.getApp().setRestTime(resttime);
			}
			break;

		default:
			break;
		}
	}

}
