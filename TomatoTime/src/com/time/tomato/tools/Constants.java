package com.time.tomato.tools;

import java.util.ArrayList;

import com.time.tomato.entity.ToastEntity;

/**
 * 常量类
 */
public class Constants {
	/**
	 * 获取Toast列表
	 */
	public static ArrayList<ToastEntity> getToastList(){
		ArrayList<ToastEntity> list = new ArrayList<ToastEntity>();
		for(int i = 0; i < 5 ; i++){
			ToastEntity toast = new ToastEntity();
			toast.setId(i + 1);
			toast.setContent("内容" + i);
			toast.setIsFinished(false);
			toast.setIsTop(false);
			toast.setIsImportant(false);
			list.add(toast);
		}
		return list;
	}
}
