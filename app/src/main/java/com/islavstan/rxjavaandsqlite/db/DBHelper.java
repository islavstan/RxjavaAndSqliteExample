package com.islavstan.rxjavaandsqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//https://github.com/DongYuHui/reactive-sqlite/blob/master/app/src/main/java/com/kyletung/reactivesqlite/data/user/UserOpenHelper.java
public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper dbHelper;

    public DBHelper(Context context) {

        super(context, "CleveroadTask", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);

    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL("create table Favorite ("
                + "id integer primary key autoincrement, name text, photo_path text );");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}