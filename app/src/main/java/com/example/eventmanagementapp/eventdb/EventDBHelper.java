package com.example.eventmanagementapp.eventdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class EventDBHelper extends SQLiteOpenHelper {

    public static  final String DB_NAME = "eventdb";
    public static final int DB_VERSION = 1;

    public static final String TABLE_EVENT = "tbl_event";
    public static final String TABLE_EVENT_COL_ID = "_id";
    public static final String TABLE_EVENT_COL_NAME = "event_name";
    public static final String TABLE_EVENT_COL_CATEGORY = "event_category";
    public static final String TABLE_EVENT_COL_LOCATION = "event_location";
    public static final String TABLE_EVENT_COL_DATE = "event_date";

    public static final String CREATE_TABLE_EVENT =
         "create table "+TABLE_EVENT+"("+
                 TABLE_EVENT_COL_ID+" integer primary key, "+
                 TABLE_EVENT_COL_NAME+" text, "+
                 TABLE_EVENT_COL_CATEGORY+" text, "+
                 TABLE_EVENT_COL_LOCATION+" text, "+
                 TABLE_EVENT_COL_DATE+" text)";

    public EventDBHelper(@Nullable Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
