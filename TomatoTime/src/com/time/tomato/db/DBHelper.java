package com.time.tomato.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static String DB_NAME = "datebase.db";
	public static int DB_VERSION = 1;
	public static String TABLE_TOMATO = "tomato";
	public static String ID = "id";
	public static String CONTENT = "content";
	public static String ISTOP = "istop";
	public static String ISFINISHED = "isfinished";
	public static String ISIMPORTANT = "isimportant";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists " + TABLE_TOMATO +
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				ID + " INTEGER , " + 
				CONTENT + " TEXT , " + 
				ISTOP + " LONG , " + 
				ISFINISHED + " LONG , " + 
				ISIMPORTANT + " LONG)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

}
