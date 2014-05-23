package com.time.tomato.tools;

import java.util.ArrayList;

import com.time.tomato.entity.TomatoEntity;

/**
 * 常量类
 */
public class Constants {
	// SharedPreferences 个人信息中存放的元素 
	public final static String FINISHTIME = "finishtime";
	public final static String WORKTIME = "worktime";
	public final static String RESTTIEM = "resttime";
	
	
	/** 土豆服务对应的ACTION */
	public final static String INTENT_AUTOSERVICE = "com.intent.service.autoservice";
	
	/**
	 * 获取Toast列表
	 */
	public static ArrayList<TomatoEntity> getToastList(){
		ArrayList<TomatoEntity> list = new ArrayList<TomatoEntity>();
		for(int i = 0; i < 5 ; i++){
			TomatoEntity toast = new TomatoEntity();
			toast.setId(i + 1);
			toast.setContent("内容" + i);
			toast.setIsFinished(0L);
			toast.setIsTop(0L);
			toast.setIsImportant(0L);
			list.add(toast);
		}
		return list;
	}
	
	/** wheel滚轮的内容,月份展示 */
	public final static String[] array_mouth = new String[]{"一月" , "二月", "三月", "一月", "一月", "一月", "一月", "一月", "一月", "一月"}; 
	
	/** wheel滚轮的内容,时间 */
	public final static String[] array_time = new String[]{"00" , "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}; 
	
	
}
