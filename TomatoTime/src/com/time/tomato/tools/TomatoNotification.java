package com.time.tomato.tools;

import com.time.tomato.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
/**
 *	通知栏管理类
 */
public class TomatoNotification {
	/** 通知ID */
	public static int NOTIFY_ID = 996;
	/**
	 *	清除所有的通知栏 
	 */
	public static void cancelAllNotify(Context context){
		NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancelAll();
	}
	
	public static void notify(Context context, String countdown, String status) {
		NotificationCompat.Builder builder = new Builder(context);
		builder.setContentTitle(countdown)
			   .setContentText(status)
			   .setSmallIcon(R.drawable.ic_stat_notify_msg)
			   .setOngoing(true)
			   .setContentIntent(notifyPending(context));
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(NOTIFY_ID, builder.build());
	}
	
	public static PendingIntent notifyPending(Context context){
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
		return pending;
	}
	
	public static void notifyFinished(Context context){
		NotificationCompat.Builder builder = new Builder(context);
		builder.setContentTitle("番茄时间结束")
			   .setContentText("点击返回")
			   .setSmallIcon(R.drawable.ic_stat_notify_msg)
			   .setOngoing(false)
			   .setContentIntent(notifyPending(context));
		Notification notify = builder.build();
		notify.flags = Notification.FLAG_AUTO_CANCEL;
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(NOTIFY_ID, notify);
	}
}
