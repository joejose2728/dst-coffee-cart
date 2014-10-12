package org.streetsteam.coffeecart.sql;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_SCHEDULED_EVENTS = "events";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_EVENTAPI_ID = "event_api_id";
  public static final String COLUMN_EVENT_NAME = "name";
  public static final String COLUMN_DESCRIPTION = "description";
  public static final String COLUMN_START = "start";
  public static final String COLUMN_END = "end";
  public static final String COLUMN_CAPACITY = "capacity";
  public static final String COLUMN_VENUE = "venue";
  public static final String COLUMN_LIKE = "like";

  private static final String DATABASE_NAME = "coffee-cart.db";
  private static final int DATABASE_VERSION = 1;

  private static final String DATABASE_CREATE = "create table "
      + TABLE_SCHEDULED_EVENTS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_EVENTAPI_ID
      + " text not null, " + COLUMN_EVENT_NAME
      + " text not null, " + COLUMN_DESCRIPTION
      + " text not null, " + COLUMN_START
      + " text not null, " + COLUMN_END
      + " text not null, " + COLUMN_CAPACITY
      + " text not null, " + COLUMN_VENUE
      + " text not null, " + COLUMN_LIKE
      + " text);"; 

  public SQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(SQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULED_EVENTS);
    onCreate(db);
  }

} 