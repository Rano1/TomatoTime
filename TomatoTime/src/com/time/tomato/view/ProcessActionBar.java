package com.time.tomato.view;

import android.view.View.OnClickListener;
import com.time.tomato.R;
import com.time.tomato.RunningActivity;
import com.time.tomato.app.AppApplication;
import com.time.tomato.tools.Constants;
import com.time.tomato.tools.TomatoNotification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProcessActionBar extends LinearLayout implements OnClickListener{
	TextView tv_countdown;
	private ImageButton ib_right;
	private Button btn_middle;
	
	public ProcessActionBar(Context context) {
		super(context);
		init(context);
	}

	public ProcessActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.process_actionbar, null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tv_countdown = (TextView)view.findViewById(R.id.tv_countdown);
		btn_middle = (Button)view.findViewById(R.id.btn_middle);
		ib_right = (ImageButton)view.findViewById(R.id.ib_right);
		btn_middle.setOnClickListener(this);
		ib_right.setOnClickListener(this);
		addView(view,params);
		initData();
	}

	private void initData() {
		intent_autoservice =  new Intent(Constants.INTENT_AUTOSERVICE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_middle:
			getContext().startActivity(new Intent(getContext(), RunningActivity.class));
			((Activity)getContext()).overridePendingTransition(R.anim.slide_top_in, 0);
			break;
		case R.id.ib_right:
			if(isRunning){
				stopService();
			}else{
				startService();//开启服务
			}
			break;

		default:
			break;
		}
	}
	
	/** 服务是否在进行工作 */
	public boolean isRunning = false;
	/** 服务对应ACTION INTENT */
	public Intent intent_autoservice;
	
	/** 开始服务 */
	public void startService(){
		setRunningStatus(true);
		long finishtime = 1000 * 60 * 25 + System.currentTimeMillis();
		AppApplication.getApp().setFinishTime(finishtime);
		getContext().startService(intent_autoservice);
	}
	
	/** 停止服务 */
	public void stopService(){
		setRunningStatus(false);
		getContext().stopService(intent_autoservice);
		//清除通知
		TomatoNotification.cancelAllNotify(getContext());
	}
	
	/** 改变UI中服务当前状态 */
	public void setRunningStatus(boolean status) {
		if (status) {
			ib_right.setImageResource(R.drawable.ic_action_cancel_red);
		}else{
			ib_right.setImageResource(R.drawable.ic_action_play_red);
		}
		isRunning = status;
	}
}
