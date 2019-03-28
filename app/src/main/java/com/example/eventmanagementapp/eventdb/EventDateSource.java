package com.example.eventmanagementapp.eventdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eventmanagementapp.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDateSource {
    private EventDBHelper dbHelper;
    private SQLiteDatabase db;

    public EventDateSource(Context context){
        dbHelper = new EventDBHelper(context);
    }

    public void openDB(){
      db = dbHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    public long insertNewEvent(Event event){
      this.openDB();
      ContentValues values = new ContentValues();
      values.put(EventDBHelper.TABLE_EVENT_COL_NAME, event.getEventName());
      values.put(EventDBHelper.TABLE_EVENT_COL_CATEGORY, event.getEventCategory());
      values.put(EventDBHelper.TABLE_EVENT_COL_LOCATION, event.getEventLocation());
      values.put(EventDBHelper.TABLE_EVENT_COL_DATE, event.getEventDate());

      long insertedRow = db.insert(EventDBHelper.TABLE_EVENT, null, values);
      this.closeDB();
      return insertedRow;
    }

    public List<Event> getAllEventsAsc(){
        List<Event> eventList = new ArrayList<>();
        this.openDB();
        Cursor cursor = db.rawQuery("select * from "+EventDBHelper.TABLE_EVENT+" order by "+
                EventDBHelper.TABLE_EVENT_COL_NAME+" asc", null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(EventDBHelper.TABLE_EVENT_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(EventDBHelper.TABLE_EVENT_COL_NAME));
                String category = cursor.getString(cursor.getColumnIndex(EventDBHelper.TABLE_EVENT_COL_CATEGORY));
                String location = cursor.getString(cursor.getColumnIndex(EventDBHelper.TABLE_EVENT_COL_LOCATION));
                String date = cursor.getString(cursor.getColumnIndex(EventDBHelper.TABLE_EVENT_COL_DATE));
                eventList.add(new Event(id, name, category, location, date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.closeDB();
        return eventList;
    }
}
