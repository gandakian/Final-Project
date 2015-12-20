package com.almightyamir.medsreminder.dbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.almightyamir.medsreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almighty Amir on 15-Dec-15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "medicinesManager.db";

    // Contacts table name
    private static final String TABLE_MEDICINES = "medicines";

    // Contacts Table Columns names
     static final String KEY_ID = "id";
     static final String KEY_MEDS_NAME = "meds_name";
     static final String KEY_MEDS_UNITS = "meds_unit";
     static final String KEY_START_DATE = "start_date";
     static final String KEY_END_DATE = "end_date";
     static final String KEY_TIMES_PER_DAY = "times_per_day";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEDICINES = "CREATE TABLE " + TABLE_MEDICINES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MEDS_NAME + " TEXT,"
                + KEY_MEDS_UNITS + " INTEGER," + KEY_START_DATE + " REAL," + KEY_END_DATE + " REAL,"
                + KEY_TIMES_PER_DAY + " INTEGER" + ")";

        Log.e("Create Tables", ""+CREATE_MEDICINES);
        db.execSQL(CREATE_MEDICINES);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINES);

        Log.e("onUpgrade", "Called");

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new medication
    public void addMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_MEDS_NAME, medication.getMedName());
        values.put(KEY_MEDS_UNITS, medication.getMedUnits());
        values.put(KEY_START_DATE, medication.getStartDate());
        values.put(KEY_END_DATE, medication.getEndDate());
        values.put(KEY_TIMES_PER_DAY, medication.getTimesPerDay());


        // Inserting Row
        db.insert(TABLE_MEDICINES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single medication
    public Medication getMedication(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDICINES, new String[] { KEY_ID,
                        KEY_MEDS_NAME, KEY_MEDS_UNITS, KEY_START_DATE, KEY_END_DATE, KEY_TIMES_PER_DAY }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Medication medication = new Medication(cursor.getInt(0),
                cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
                cursor.getInt(4), cursor.getInt(5));

        return medication;
    }

    // Getting single todays medication
    public List<Medication> getTodayMedication(String selectQuery) {
        List<Medication> medicationList = new ArrayList<Medication>();
        // Select All Query
        //String selectQuery = "SELECT * FROM " + TABLE_MEDICINES + " WHERE " + KEY_END_DATE + " >= " + today  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        System.out.println("cursor.getCount() = " + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Medication medication = new Medication();

                medication.setId(cursor.getInt(0));
                medication.setMedName(cursor.getString(1));
                medication.setMedUnits(cursor.getInt(2));
                medication.setStartDate(cursor.getLong(3));
                medication.setEndDate(cursor.getLong(4));
                medication.setTimesPerDay(cursor.getInt(5));
                // Adding contact to list
                medicationList.add(medication);
            } while (cursor.moveToNext());
        }

        // return medication list
        return medicationList;
    }

    // Getting All Medication
    public ArrayList<Medication> getAllMedication() {
        ArrayList<Medication> medicationList = new ArrayList<Medication>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Medication medication = new Medication();

                medication.setId(cursor.getInt(0));
                medication.setMedName(cursor.getString(1));
                medication.setMedUnits(cursor.getInt(2));
                medication.setStartDate(cursor.getLong(3));
                medication.setEndDate(cursor.getLong(4));
                medication.setTimesPerDay(cursor.getInt(5));
                // Adding contact to list
                medicationList.add(medication);
            } while (cursor.moveToNext());
        }

        // return medication list
        return medicationList;
    }

    // Updating single medication
    public int updateContact(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MEDS_NAME, medication.getMedName());
        values.put(KEY_MEDS_UNITS, medication.getMedUnits());

        // updating row
        return db.update(TABLE_MEDICINES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(medication.getId()) });
    }

    // Deleting single medication
    public void deleteMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICINES, KEY_ID + " = ?",
                new String[] { String.valueOf(medication.getId()) });
        db.close();
    }


    // Getting medication Count
    public int getMedicationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEDICINES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
