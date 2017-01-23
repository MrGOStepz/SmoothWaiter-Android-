package com.example.mrgo.smoothwaiter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Mr.GO on 22/01/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "SmoothWaiter.db";

    //Table Name
    private static final String TABLE_USER = "tb_user";
    private static final String TABLE_OPTION = "tb_option";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL("CREATE TABLE IF NOT EXISTS tb_option"
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name TEXT UNIQUE,"
                    + " value TEXT);");

            db.execSQL("CREATE TABLE IF NOT EXISTS tb_user"
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " username TEXT UNIQUE,"
                    + " password TEXT,"
                    + " name TEXT,"
                    + " level INTEGER,"
                    + " active INTEGER,"
                    + " date_active TEXT);");
        }
        catch (SQLiteAbortException ex)
        {
            Log.d(TAG, "onCreate: " + ex.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTION);

        //Create tables again
        onCreate(db);
    }

    public int validateUser(String userName, String password)
    {
        int userCount;
        String countUserSQL = "SELECT * FROM tb_user WHERE username = '" + userName + "' AND password = '" + password + "'" ;
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countUserSQL,null);
        userCount = cursor.getCount();
        cursor.close();
        return userCount;
    }

    public Staff getStaffDetail(String userName)
    {
        Staff staff = new Staff();
        SQLiteDatabase  db = this.getReadableDatabase();

        Cursor cursor =  db.rawQuery("SELECT id,username,name,password,level FROM tb_user "
                                + "WHERE username = '" +  userName + "'", null);

        //get data from column
        int idCol = cursor.getColumnIndex("id");
        int userNameCol = cursor.getColumnIndex("username");
        int passwordCol = cursor.getColumnIndex("password");
        int nameCol = cursor.getColumnIndex("name");
        int levelCol = cursor.getColumnIndex("level");

        //cursor points to 1 row at a time
        cursor.moveToFirst();

        if (cursor != null && (cursor.getCount() > 0))
        {
            do
            {
                //cycle through all the rows of sql query and add data to string
                staff.setId(Integer.parseInt(cursor.getString(idCol)));
                staff.setUserName(cursor.getString(userNameCol));
                staff.setPassword(cursor.getString(passwordCol));
                staff.setStuffLevel(Integer.parseInt(cursor.getString(levelCol)));
                staff.setStuffName(cursor.getString(nameCol));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return staff;
    }

    public int totalOption()
    {
        int totalOption;
        String countUserSQL = "SELECT * FROM tb_option" ;
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countUserSQL,null);
        totalOption = cursor.getCount();
        cursor.close();
        return  totalOption;
    }

    public void resetTableOption()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS tb_option");
        db.execSQL("CREATE TABLE IF NOT EXISTS tb_option"
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT UNIQUE,"
                + " value TEXT);");

    }

    //totalTable
    public void addOption (String optionName, String optionValue)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", optionName);
            values.put("value", optionValue);

            // Inserting Row
            db.insert("tb_option", null, values);
            db.close(); // Closing database connection

        }
        catch (Exception ex)
        {
            Log.d("addOption Error:", ex.toString());
        }
    }

    public void updateOption (String optionName, String optionValue)
    {
        try
        {

        }
        catch (Exception ex)
        {
            Log.d("updateOption Error:", ex.toString());
        }
    }

    public int getTotalTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("tb_option", new String[] { "value" }
                , "name" + "=?",
                new String[] { String.valueOf("totalTable") }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return Integer.parseInt(cursor.getString(0));
    }

    public int updateTotalTable(String totalTable)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("value", totalTable);

        // updating row
        return db.update("tb_option", values, "name" + " = ?",
                new String[] { "totalTable" });
    }


}
