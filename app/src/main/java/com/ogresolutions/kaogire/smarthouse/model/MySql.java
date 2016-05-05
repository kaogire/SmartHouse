package com.ogresolutions.kaogire.smarthouse.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ogresolutions.kaogire.smarthouse.objects.Guest;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Njuguna on 4/27/2016.
 */
public class MySql extends SQLiteOpenHelper {
    //constants
    private final String COMMA_SEP = " , ";
    private final String TYPE_TEXT = " TEXT ";
    private final String TYPE_INTEGER = " INTEGER ";
    private final String TYPE_INTEGER_KEY = " INTEGER PRIMARY KEY ";
    //Guest table
    private final String GUEST_TABLE = " guest_table ";
    private final String GUEST_ID = " id ";
    private final String GUEST_NAME = " name ";
    private final String GUEST_TIME_IN = " time_in ";
    private final String GUEST_TIME_OUT = " time_out ";
    private final String CREATE_GUEST_TABLE = "CREATE TABLE IF NOT EXISTS guest_table("+GUEST_ID+" INTEGER PRIMARY KEY ,"+GUEST_NAME+" TEXT,"+GUEST_TIME_IN+" TEXT,"+GUEST_TIME_OUT+" TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GUEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public MySql(Context context){
        super(context, "MyDatabase", null, 1);
    }

    //guest details
    public void addGuest(Guest guest)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues guestValues = new ContentValues();
        guestValues.put(GUEST_NAME, guest.getName());
        if(guest.getTimeOut()!= null||guest.getTimeIn()!=null) {
            guestValues.put(GUEST_TIME_IN, guest.getTimeIn().toString());
            guestValues.put(GUEST_TIME_OUT, guest.getTimeOut().toString());
        }

        db.insert(GUEST_TABLE,null,guestValues);
        db.close();
    }

    public ArrayList<Guest> getAllGuests()
    {
        ArrayList<Guest> guests = new ArrayList<Guest>();
        String selectQuery = "SELECT * FROM "+GUEST_TABLE+ "ORDER BY "+GUEST_ID +"DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Guest guest = new Guest();
                guest.setId(Integer.parseInt(cursor.getString(0)));
                guest.setName(cursor.getString(1));
                guest.setTimeIn(cursor.getString(2));
                guest.setTimeOut(cursor.getString(3));
                // Adding contact to list
                guests.add(guest);
            } while (cursor.moveToNext());
        }

        // return contact list
        return guests;
    }
    public void deleteGuest(Guest guest) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(GUEST_TABLE, GUEST_ID + " = ?",
                new String[]{String.valueOf(guest.getId())});
        db.close();
    }
    public int getGuestCount() {
        String countQuery = "SELECT  * FROM " + GUEST_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateCard(Guest guest) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GUEST_TIME_IN, guest.getTimeIn().toString());
        values.put(GUEST_TIME_OUT, guest.getTimeOut().toString());

        // updating row
        return db.update(GUEST_TABLE, values, GUEST_ID + " = ?",
                new String[] { String.valueOf(guest.getId()) });
    }
}
