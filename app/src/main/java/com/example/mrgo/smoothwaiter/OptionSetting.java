package com.example.mrgo.smoothwaiter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mr.GO on 22/01/2017.
 */

public class OptionSetting
{
    private SQLiteDatabase contactsDB = null;

    public static int totalTable = 0;
    public static String rememberIDPW = "";
    public static int checkRememberID= 0;

    public static int getTotalTable()
    {
        return totalTable;
    }

    public static void setTotalTable(int totalTable)
    {
        OptionSetting.totalTable = totalTable;
    }

    public static String getRememberIDPW()
    {
        return rememberIDPW;
    }

    public static void setRememberIDPW(String rememberIDPW)
    {
        OptionSetting.rememberIDPW = rememberIDPW;
    }

    public static int getCheckRememberID()
    {
        return checkRememberID;
    }

    public static void setCheckRememberID(int checkRememberID)
    {
        OptionSetting.checkRememberID = checkRememberID;
    }

    public static void addOprion(String optionName, String optionValue)
    {

    }

//    OptionSetting()
//    {
//        //Create or open a private database called MyContacts.db
//        contactsDB = SQLiteDatabase.openOrCreateDatabase("SmoothWaiter.db",MODE.PRIVATE,null);
//        //if creating a new DB, it won't actually create to DB until we do some SQL with it
//        contactsDB.execSQL("CREATE TABLE IF NOT EXISTS tb_option"
//                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + " name TEXT,"
//                + " value TEXT);");
//    }
//
//    public int getTotalRow()
//    {
//        Cursor cursor = contactsDB.rawQuery("SELECT COUNT(ID) FROM tb_option",null);
//
//        //get data from column
//        int countID = cursor.getColumnIndex("COUNT(ID)");
//
//        return countID;
////        if(countID < 1)
////        {
////            contactsDB.execSQL("INSERT INTO `tb_option`(name,value)"
////                    + " VALUES ('totalTable','9')");
////
////        }
//    }

}
