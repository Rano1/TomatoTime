package com.time.tomato.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppApplication extends Application {
	public final static String USERINFO = "userinfo";
	public static AppApplication mAppApplication;
	public SharedPreferences sp_userinfo;
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
	
	public void setFinishTime(long finishtime){
		Editor editor = sp_userinfo.edit();
		editor.putLong("finishtime", finishtime);
		editor.commit();
		
	}
	
	public long getFinishtTime(){
		return sp_userinfo.getLong("finishtime", 0);
	}
}
