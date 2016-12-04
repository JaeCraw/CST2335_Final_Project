package com.example.jae.cst2335_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by martin on 2016-12-01.
 */
public class LivingRoomDataBaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Database";
    public static int VERSION_NUM = 1;
    public static final String TABLE_NAME = "ActivityTable";
    public static final String col = "Activity";
    public LivingRoomDataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQL: db.execSQL("CREATE TABLE " + TABLE_NAME +" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ col +" TEXT);" );
        Log.i("LivingRoomDatabase", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        SEL: db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("LivingRoomDatabase", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion=" + newVer);
    }

    public void insertData(String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(col, message);
        db.insert(TABLE_NAME, null, value);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
        return cursor;
    }
}
