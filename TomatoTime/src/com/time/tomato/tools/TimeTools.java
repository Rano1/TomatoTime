package com.time.tomato.tools;

import android.content.Context;
import android.os.Build;

public class TimeTools {

	/** 获取倒计时分秒*/
	public static String getCountDwon(long countdown) {
		int m = (int) (countdown / 60000L);//分
		int s = (int) ((countdown - 60000 * m) / 1000L);//秒
		String str;
		if(s < 10){
			str = "0" + String.valueOf(s);
		}else{
			str = String.valueOf(s);
		}
		return  m + ":" + str;
//		if (j < 10)
//			;
//		for (String str = "0" + String.valueOf(j);; str = String.valueOf(j))
//			return i + ":" + str;
	}

	public static long getFinalTime() {
		long time = 0;
		return time;
	}
}
