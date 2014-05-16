package com.time.tomato.service;

import com.time.tomato.tools.TimeTools;
import com.time.tomato.tools.TomatoNotification;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

public class AutoCountDownTimer extends CountDownTimer {
	public Context context;
	public final static String TAG  = "AutoCountDownTimer";
	
	public AutoCountDownTimer(Context context,long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.context = context;
	}

	/** 计时器结束的时候要做的事情 */
	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		TomatoNotification.notifyFinished(context);
		Log.d(TAG, "onFinish");
	}
	
	/**倒计时开始时要做的事情*/
	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		String countdown =  TimeTools.getCountDwon(millisUntilFinished);
		TomatoNotification.notify(context, countdown, "番茄运行中");
		Log.d(TAG, countdown +"时间后结束" );
	}

}
