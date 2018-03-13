package com.csce.hamstersftw.hamsterhelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shivk on 3/11/2018.
 */

public class Databasehelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HamsterHelp.db";
    private static final String TABLE_NAME = "Information";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Fname = "firstName";
    private static final String COLUMN_Lname = "lastName";
    private static final String COLUMN_Bday = "birthDay";
    private static final String COLUMN_Phno = "mobile";
    private static final String COLUMN_a1 = "addressLine1";
    private static final String COLUMN_a2 = "addressLine2";
    private static final String COLUMN_e = "email";
    private static final String COLUMN_p = "password";
    SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_CREATE = "create table Information (id integer primary key not null , " +
            "FirstName text not null, lastName text not null, birthDay text not null, mobile text not null, addressLine1 text not null, addressLine2 text not null,email text not null,password text not null)";

    public Databasehelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase = sqLiteDatabase;
    }
    public void insertInfo( Userinfo u){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from Information";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_Fname, u.getFirstName());
        values.put(COLUMN_Lname,u.getLastName());
        values.put(COLUMN_Bday,u.getBirthDay());
        values.put(COLUMN_Phno,u.getMobile());
        values.put(COLUMN_a1,u.getAddressLine1());
        values.put(COLUMN_a2,u.getAddressLine2());
        values.put(COLUMN_e,u.getEmail());
        values.put(COLUMN_p,u.getPassword());
        sqLiteDatabase.insert(TABLE_NAME, null,values);
        sqLiteDatabase.close();

    }

    public String serachPass(String email){
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select email,password from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(email)){
                    b= cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }
}
