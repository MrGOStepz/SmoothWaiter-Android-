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

}
