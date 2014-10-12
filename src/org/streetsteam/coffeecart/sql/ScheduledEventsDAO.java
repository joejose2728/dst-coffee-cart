package org.streetsteam.coffeecart.sql;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.streetsteam.coffeecart.model.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ScheduledEventsDAO {

  private SQLiteDatabase database;
  private SQLiteHelper dbHelper;
  private String[] allColumns = { SQLiteHelper.COLUMN_ID,
		  SQLiteHelper.COLUMN_EVENTAPI_ID,
		  SQLiteHelper.COLUMN_EVENT_NAME,
		  SQLiteHelper.COLUMN_DESCRIPTION,
		  SQLiteHelper.COLUMN_START,
		  SQLiteHelper.COLUMN_END,
		  SQLiteHelper.COLUMN_CAPACITY,
		  SQLiteHelper.COLUMN_VENUE,};

  public ScheduledEventsDAO(Context context) {
    dbHelper = new SQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public long createScheduleEvent(Event event) {
    ContentValues values = new ContentValues();
    values.put(SQLiteHelper.COLUMN_EVENTAPI_ID, event.getApiEventId());
    values.put(SQLiteHelper.COLUMN_EVENTAPI_ID, event.getApiEventId());
    values.put(SQLiteHelper.COLUMN_EVENTAPI_ID, event.getApiEventId());
    values.put(SQLiteHelper.COLUMN_START, event.getStart());
    values.put(SQLiteHelper.COLUMN_END, event.getEnd());
    values.put(SQLiteHelper.COLUMN_CAPACITY, event.getCapacity());
    values.put(SQLiteHelper.COLUMN_VENUE, event.getVenue().toString());
    return database.insert(SQLiteHelper.TABLE_SCHEDULED_EVENTS, null,values);
  }


  public List<Event> getScheduledEvents() {
    List<Event> events = new ArrayList<Event>();

    Cursor cursor = database.query(SQLiteHelper.TABLE_SCHEDULED_EVENTS,allColumns, null, null, null, null, null);
    cursor.moveToFirst();

    while (!cursor.isAfterLast()) {
      Event event = new Event();
      event.setApiEventId(cursor.getString(1));
      event.setEventName(cursor.getString(2));
      event.setDescription(cursor.getString(3));
      event.setStart(cursor.getString(4));
      event.setEnd(cursor.getString(5));
      event.setCapacity(cursor.getString(6));
      event.setVenueString(cursor.getString(7));
      events.add(event);
      cursor.moveToNext();
    }
    cursor.close();
    return events;
  }
  
  public void likeScheduledEvent(boolean like, String eventAPIId){
	  ContentValues values = new ContentValues();
	  values.put(SQLiteHelper.COLUMN_LIKE, like + "");
	  
	  database.update(SQLiteHelper.TABLE_SCHEDULED_EVENTS, values,
			  SQLiteHelper.COLUMN_EVENTAPI_ID + "=?", new String[]{eventAPIId});
  }

  public List<Event> getExpiredEvents() {
	    List<Event> events = new ArrayList<Event>();
	    String selection = "like is null and start >" + new Date(System.currentTimeMillis()).toString();
	    Cursor cursor = database.query(SQLiteHelper.TABLE_SCHEDULED_EVENTS,allColumns, selection, null, null, null, null);
	    cursor.moveToFirst();

	    while (!cursor.isAfterLast()) {
	      Event event = new Event();
	      event.setApiEventId(cursor.getString(1));
	      event.setEventName(cursor.getString(2));
	      event.setDescription(cursor.getString(3));
	      event.setStart(cursor.getString(4));
	      event.setEnd(cursor.getString(5));
	      event.setCapacity(cursor.getString(6));
	      event.setVenueString(cursor.getString(7));
	      events.add(event);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return events;
	  } 
} 