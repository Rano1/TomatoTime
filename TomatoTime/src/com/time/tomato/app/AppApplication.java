package com.time.tomato.app;

import com.time.tomato.tools.Constants;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppApplication extends Application {
	public final static String USERINFO = "userinfo";
	public static AppApplication mAppApplication;
	public SharedPreferences sp_userinfo;
	/** 默认土豆时间 */
	public long worktime_default = 25 * 1000 * 60;
	/** 默认休息时间 */
	public long resttime_default = 5 * 1000 * 60;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAppApplication = this;
		sp_userinfo = getSharedPreferences(USERINFO,Context.MODE_PRIVATE);
	}

	public static AppApplication getApp() {
		return mAppApplication;
	}
	
	/** 
	 * 判断服务是否正在进行 
	 * true:正在进行   false:服务已经停止
	 *  */
	public boolean isServiceRunning(){
		return false;
	}
	
	
	/** 设置土豆时间  */
	public void setWorkTime(long custom){
		Editor editor = sp_userinfo.edit();
		editor.putLong(Constants.WORKTIME, custom);
		editor.commit();
	}
	
	/** 获取土豆时间  */
	public long getWorkTime(){
		return sp_userinfo.getLong(Constants.WORKTIME, worktime_default);
	}
	
	/** 设置休息时间  */
	public void setRestTime(long custom){
		Editor editor = sp_userinfo.edit();
		editor.putLong(Constants.RESTTIEM, custom);
		editor.commit();
	}
	
	/** 获取土豆时间  */
	public long getRestTime(){
		return sp_userinfo.getLong(Constants.RESTTIEM, resttime_default);
	}
	
	/** 设置服务结束时间  */
	public void setFinishTime(long finishtime){
		Editor editor = sp_userinfo.edit();
		editor.putLong("finishtime", finishtime);
		editor.commit();
		
	}
	
	/** 获取服务结束时间  */
	public long getFinishtTime(){
		return sp_userinfo.getLong("finishtime", 0);
	}
}
