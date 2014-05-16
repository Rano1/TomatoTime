package com.time.tomato.app;

import android.app.Application;

public class AppApplication extends Application {
	public static AppApplication mAppApplication;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAppApplication = this;
	}

	public static AppApplication getApp() {
		return mAppApplication;
	}
}
