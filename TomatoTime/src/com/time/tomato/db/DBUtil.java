package com.time.tomato.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	public static DBUtil mInstance;
	private Context mContext;
	private DBHelper mDBhelper;
	private SQLiteDatabase mSQLiteDatabase;
	
	public DBUtil(Context context) {
		this.mContext = context;
		mDBhelper = new DBHelper(context);
		mSQLiteDatabase = mDBhelper.getWritableDatabase();
	}

	public static DBUtil getInstance(Context context) {
		if(mInstance ==null){
			mInstance = new DBUtil(context);
		}
		return mInstance;
	}
	
	/**
	 * 关闭数据库
	 */
	public void close() {
		mDBhelper.close();
		mDBhelper = null;
		mSQLiteDatabase.close();
		mSQLiteDatabase = null;
		mInstance = null;
	}

	/**
	 * 添加数据
	 */
	public void insertData(ContentValues values) {
		mSQLiteDatabase.insert(DBHelper.TABLE_TOMATO, null, values);
	}

	/**
	 * 更新数据
	 * 
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 */
	public void updateData(ContentValues values, String whereClause,
			String[] whereArgs) {
		mSQLiteDatabase.update(DBHelper.TABLE_TOMATO, values, whereClause,
				whereArgs);
	}

	/**
	 * 删除数据
	 * 
	 * @param whereClause
	 * @param whereArgs
	 */
	public void deleteData(String whereClause, String[] whereArgs) {
		mSQLiteDatabase
				.delete(DBHelper.TABLE_TOMATO, whereClause, whereArgs);
	}

	/**
	 * 查询数据
	 * 
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public Cursor selectData(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		Cursor cursor = mSQLiteDatabase.query(DBHelper.TABLE_TOMATO,columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
}
