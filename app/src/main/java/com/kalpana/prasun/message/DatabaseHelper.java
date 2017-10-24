package com.kalpana.prasun.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himanshu Singh on 23-Oct-17.
 */

class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "ContentManager.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + ContentContract.ContentEntry.TABLE_NAME + " (" +
                ContentContract.ContentEntry.COLUMN_CONTENT_MESSAGE + " TEXT NOT NULL, " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + ContentContract.ContentEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public DatabaseHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist

        db1.execSQL(DROP_BENEFICIARY_TABLE);

        // Create tables again
        onCreate(db1);

    }

    //Method to create beneficiary records

    public void addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContentContract.ContentEntry.COLUMN_CONTENT_MESSAGE, data.getMessage());

        db.insert(ContentContract.ContentEntry.TABLE_NAME, null, values);
        db.close();
    }

    public List<Data> getAllBeneficiary() {
        // array of columns to fetch
        String[] columns = {
                ContentContract.ContentEntry.COLUMN_CONTENT_MESSAGE
        };
        // sorting orders
        String sortOrder =
               ContentContract.ContentEntry.COLUMN_CONTENT_MESSAGE + " ASC";
        List<Data> DataList = new ArrayList<Data>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(ContentContract.ContentEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setMessage(cursor.getString(cursor.getColumnIndex(ContentContract.ContentEntry.COLUMN_CONTENT_MESSAGE)));
                // Adding user record to list
                DataList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return DataList;
    }

}
