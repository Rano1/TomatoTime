package com.time.tomato.service;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import com.time.tomato.app.AppApplication;
import com.time.tomato.tools.TimeTools;
import com.time.tomato.tools.TomatoNotification;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class AutoService extends Service {
	private final static String TAG = "AutoService";
	/** 屏幕锁 */
	private PowerManager.WakeLock mWakeLock;
	public boolean isRunning = false;
	private Timer timer;

	/** 倒计时类 */
	private CountDownTimer mCountDownTimer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate");
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		//PowerManager.PARTIAL_WAKE_LOCK 保持CPU 运转，屏幕和键盘灯有可能是关闭的。
		mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Tomato");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		if (!isRunning) {
			startRunning();
		} else {
			stopRunning();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	public void startRunning() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Log.d(TAG, "1");
			}
		}, 0L, 1000L);
		startThread();
	}

	public void stopRunning() {
		if (mCountDownTimer != null) {
			mCountDownTimer.cancel();
			mCountDownTimer = null;
		}
		// stopSelf();
	}

	public void startThread() {
		mWakeLock.acquire();
		if (mCountDownTimer == null) {
			long timeMillis = System.currentTimeMillis();
			long finishtime = AppApplication.getApp().getFinishtTime();
			mCountDownTimer = new AutoCountDownTimer(getApplicationContext() , finishtime - timeMillis, 1000L).start();
//			mCountDownTimer = new AutoCountDownTimer(getApplicationContext() , 25 * 60 * 1000L, 1000L).start();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mWakeLock.isHeld()) {
			mWakeLock.release();
		}
		if (timer != null) {
			timer.cancel();
		}
		if (mCountDownTimer != null) {
			mCountDownTimer.cancel();
			mCountDownTimer = null;
		}
		TomatoNotification.cancelAllNotify(getApplicationContext());
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

}
