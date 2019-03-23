package com.example.student.databasepractice.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME =  "student.db";

    public static final int DATABASE_VERSION = 1;

    public DataDbHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE "+ DataContract.DataEntry.TABLE_NAME + "("
                
                + DataContract.DataEntry._ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + DataContract.DataEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + DataContract.DataEntry.COLUMN_ROLLNO + " INTEGER, "
                + DataContract.DataEntry.COLUMN_CLASS + " TEXT NOT NULL);"
                ;

        db.execSQL(SQL_CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
