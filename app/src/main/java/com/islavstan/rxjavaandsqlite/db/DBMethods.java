package com.islavstan.rxjavaandsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

//http://www.xem.se/tag/rxandroid/
public class DBMethods {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public DBMethods(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void saveContent(String text, String photoPath) {
        ContentValues newValues = new ContentValues();
        newValues.put("name", text);
        newValues.put("photo_path", photoPath);
        db.insert("Favorite", null, newValues);
    }

    public void delete(int id) {
        db.delete("Favorite", "id = " + id, null);
    }


    public int getCount() {
        String countQuery = "SELECT  * FROM Favorite";
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public Observable<Long> write(String text, String photoPath) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", text);
                contentValues.put("photo_path", photoPath);
                subscriber.onNext(db.insert("Favorite", null, contentValues));
            }
        });
    }


    public Observable<String> get(int id) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String countQuery = "SELECT name FROM Favorite where id = " + id;
                Cursor cursor = db.rawQuery(countQuery, null);
                subscriber.onNext(cursor.moveToFirst() ? cursor.getString(cursor.getColumnIndex("name")) : null);
                cursor.close();
            }
        });
    }
}


