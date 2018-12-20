package com.example.ravikiran.hellodoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteDB";
    private static final String DB_Name = "myDB";
    private static final String table_01 = "DOCTORS";
    private static final String table_02 = "PATIENTS";

    Database(Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_t01 = "CREATE TABLE " + table_01 +
                " ( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT," +
                " HOSPITAL TEXT," +
                " DEPT TEXT," +
                " HOURS TEXT )";
        sqLiteDatabase.execSQL(create_t01);
        Log.d(TAG, "onCreate: " + table_01 + " created.");

        String create_t02 = "CREATE TABLE " + table_02 +
                " ( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT," +
                " SEX TEXT," +
                " AGE TEXT," +
                " PHONE TEXT," +
                " EMAIL TEXT," +
                " HOSPITAL TEXT," +
                " DEPT TEXT," +
                " DOC TEXT," +
                " HOUR TEXT )";
        sqLiteDatabase.execSQL(create_t02);
        Log.d(TAG, "onCreate: " + table_02 + " created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addDoc(String s_name, String s_hosp, String s_dept, StringBuilder s_hour) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME", s_name);
        cv.put("HOSPITAL", s_hosp);
        cv.put("DEPT", s_dept);
        cv.put("HOURS", String.valueOf(s_hour));
        sqLiteDatabase.insert(table_01, null, cv);

        Log.d(TAG, "AddRow: row " + s_name + " added.");
    }

    public ArrayList<String> getDocs() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                table_01,
                null,
                null,
                null,
                null,
                null,
                "ID DESC");

        ArrayList<String> e = new ArrayList<>();
        while(c.moveToNext()) {
            e.add(c.getString(1) + " - " + c.getString(2) + " - " + c.getString(3) + " - " + c.getString(4));
        }
        c.close();
        Log.d(TAG, "getDocs: " + table_01);
        return e;
    }

    public ArrayList<String> getPatients() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                table_02,
                new String[]{"Name", "AGE", "HOUR"},
                null,
                null,
                null,
                null,
                "ID DESC");

        ArrayList<String> e = new ArrayList<>();
        while(c.moveToNext()) {
            e.add(c.getString(0) + " (" + c.getString(1) + ") at " + c.getString(2));
        }
        c.close();
        Log.d(TAG, "getDocs: " + table_01);
        return e;
    }


    public ArrayList<String> getList(String s_c) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                table_01,
                new String[]{s_c},
                null,
                null,
                null,
                null,
                "ID DESC");

        ArrayList<String> e = new ArrayList<>();
        while(c.moveToNext()) {
            if  (!e.contains(c.getString(0)))
                e.add(c.getString(0));
        }
        c.close();
        Log.d(TAG, "getList: " + s_c);
        return e;
    }

    public ArrayList<String> getList(String s_c, String var1) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                table_01,
                new String[]{s_c},
                "DEPT = ?",
                new String[]{var1},
                null,
                null,
                null);

        ArrayList<String> e = new ArrayList<>();
        while(c.moveToNext()) {
            if  (!e.contains(c.getString(0)))
                e.add(c.getString(0));
        }
        c.close();
        Log.d(TAG, "getList: " + s_c + " at " + var1);
        return e;
    }

    public ArrayList<String> getList(String s_c, String var1, String var2) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.query(
                table_01,
                new String[]{s_c},
                "DEPT = ? AND HOSPITAL = ?",
                new String[]{var1, var2},
                null,
                null,
                null);

        ArrayList<String> e = new ArrayList<>();
        while(c.moveToNext()) {
            if  (!e.contains(c.getString(0)))
                e.add(c.getString(0));
        }
        c.close();
        Log.d(TAG, "getList: " + s_c + " at " + var1 + ", " + var2);
        return e;
    }

    public ArrayList<String> getList(Context context, String s_c, String var1, String var2, String var3) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        hours H = new hours();
        Cursor c = sqLiteDatabase.query(
                table_01,
                new String[]{s_c},
                "DEPT = ? AND HOSPITAL = ? AND NAME = ?",
                new String[]{var1, var2, var3},
                null,
                null,
                null);

        c.moveToNext();
        String bin_hours = c.getString(0);
        c.close();

        ArrayList<String> hours_available = H.getHoursFromBinary(context, bin_hours);
        Log.d(TAG, "getList: " + s_c + " at " + var1 + ", " + var2 + ", " + var3);
        return hours_available;
    }

    public void addPatient(String name, String sex, String age, String phone, String email,
                           String hos, String dept, String doc, String hour) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        hours H = new hours();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("SEX", sex);
        cv.put("AGE", age);
        cv.put("PHONE", phone);
        cv.put("EMAIL", email);
        cv.put("HOSPITAL", hos);
        cv.put("DEPT", dept);
        cv.put("DOC", doc);
        cv.put("HOUR", hour);
        sqLiteDatabase.insert(table_02, null, cv);

        Cursor c = sqLiteDatabase.query(
                table_01,
                new String[]{"HOURS"},
                "DEPT = ? AND HOSPITAL = ? AND NAME = ?",
                new String[]{dept, hos, doc},
                null,
                null,
                null);
        c.moveToNext();
        String hour_binary = c.getString(0);
        c.close();
        ContentValues cv_copy = new ContentValues();
        cv_copy.put("HOURS", H.getNumberFromHours(hour, hour_binary));
        sqLiteDatabase.update(table_01, cv_copy, "NAME LIKE ?", new String[]{doc});

        Log.d(TAG, "AddPatient: " + name + " added.");
    }
}