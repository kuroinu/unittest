package com.practice.model.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResultModel extends SQLiteOpenHelper {

	final static private int DB_VERSION = 1;

	public ResultModel(Context context) {
		super(context, "alphabethitterdb", null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table result(score text not null);");
		db.execSQL("insert into result(score) values ('01:30');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// データベースの変更が生じた場合は、ここに処理を記述する。

	}

	public List<String> selectTop3() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query("result", new String[] { "score" }, null, null,
				null, null, "score ASC", "3");
		List<String> l = new ArrayList<String>();
		if (c.moveToFirst()) {
			do {
				l.add(c.getString(0));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return l;
	}

	public void insert(String score) {
		ContentValues values = new ContentValues();
		values.put("score", score);
		SQLiteDatabase db = this.getReadableDatabase();
		if (db.insert("result", null, values) < 0) {
			// エラー時
		}
		db.close();
	}

	public void deleteAll() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete("result", null, null);
		db.close();
	}
}
