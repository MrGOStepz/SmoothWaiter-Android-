package com.example.mrgo.smoothwaiter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

            db.execSQL("CREATE TABLE IF NOT EXISTS tb_food"
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name TEXT,"
                    + " description TEXT,"
                    + " category_id INTEGER,"
                    + " price INTEGER);");

            db.execSQL("CREATE TABLE IF NOT EXISTS tb_category"
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name TEXT UNIQUE);");

            db.execSQL("CREATE TABLE IF NOT EXISTS tb_menuorder"
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " tablename TEXT,"
                    + " foodname TEXT,"
                    + " staffname TEXT,"
                    + " price INTEGER,"
                    + " active INTEGER);");

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

    public List<String> getListTableActive()
    {
        List<String> tableList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT DISTINCT tablename FROM tb_menuorder WHERE active = '1'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                tableList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return contact list
        return tableList;
    }

    public int getCountActiveTable()
    {
        int countTableActive;
        String countTableActiveSQL = "SELECT DISTINCT tablename FROM tb_menuorder WHERE active = '1'" ;
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countTableActiveSQL,null);
        countTableActive = cursor.getCount();
        cursor.close();
        return countTableActive;
    }

    public ListFoodStatus getListFoodStatus(String table)
    {
        ListFoodStatus listFoodStatus = new ListFoodStatus();
        // Select All Query
        String selectQuery = "SELECT * FROM tb_menuorder WHERE tablename = '" + table + "' AND active = '1'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Food food;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                listFoodStatus.setTable(cursor.getString(1));
                food = new Food();
                food.setName(cursor.getString(2));

                listFoodStatus.setName(cursor.getString(3));
                // Adding contact to list
                listFoodStatus.listFood.add(food);
            } while (cursor.moveToNext());
        }
        // return contact list
        return listFoodStatus;
    }

    public void addMenuOrder(String table,String foodName,String staffName)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tablename", table);
            values.put("foodname", foodName);
            values.put("staffname", staffName);
            values.put("price", 5);
            values.put("active", 1);

            // Inserting Row
            db.insert("tb_menuorder", null, values);
            db.close(); // Closing database connection

        }
        catch (Exception ex)
        {
            Log.d("addMenuOrder Error:", ex.toString());
        }
    }

    public List<Food> getListFood(int categoryID)
    {
        List<Food> foodsList = new ArrayList<Food>();
        // Select All Query
        String selectQuery = "SELECT  * FROM tb_food WHERE category_id = '"+ categoryID +"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setDescription(cursor.getString(2));
                switch (categoryID)
                {
                    case 1:
                        food.setCategory("Entree");
                        break;
                    case 2:
                        food.setCategory("Main");
                        break;
                    case 3:
                        food.setCategory("Desert");
                        break;
                    case 4:
                        food.setCategory("Beverage");
                        break;
                    default:
                        break;
                }

                food.setPrice(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                foodsList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodsList;
    }

    public void addFood(String name,String description, int categoryID, int price)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("description", description);
            values.put("category_id", categoryID);

            values.put("price", price);

            // Inserting Row
            db.insert("tb_food", null, values);
            db.close(); // Closing database connection

        }
        catch (Exception ex)
        {
            Log.d("addFood Error:", ex.toString());
        }
    }

    public List<Staff> getAllStaff()
    {
        List<Staff> staffList = new ArrayList<Staff>();
        // Select All Query
        String selectQuery = "SELECT  * FROM tb_user";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff();
                staff.setId(Integer.parseInt(cursor.getString(0)));
                staff.setUserName(cursor.getString(1));
                staff.setStuffName(cursor.getString(3));
                staff.setStuffLevel(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                staffList.add(staff);
            } while (cursor.moveToNext());
        }

        // return contact list
        return staffList;
    }


    public int getCategoryCount()
    {
        int categoryCount;
        String countCategorySQL = "SELECT * FROM tb_category" ;
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countCategorySQL,null);
        categoryCount = cursor.getCount();
        cursor.close();
        return categoryCount;
    }

    public void addCategory(String name)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);

            // Inserting Row
            db.insert("tb_category", null, values);
            db.close(); // Closing database connection

        }
        catch (Exception ex)
        {
            Log.d("addOption Error:", ex.toString());
        }
    }

    public int getTotalFood()
    {
        int foodCount;
        String countFoodSQL = "SELECT * FROM tb_food" ;
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countFoodSQL,null);
        foodCount = cursor.getCount();
        cursor.close();
        return foodCount;
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

    public int getTotalUser()
    {
        int userCount;
        String countUserSQL = "SELECT * FROM tb_user" ;
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countUserSQL,null);
        userCount = cursor.getCount();
        cursor.close();
        return userCount;
    }

    public void createAdminUser()
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("username", "admin");
            values.put("password", "admin");
            values.put("name", "admin");
            values.put("level", "3");

            // Inserting Row
            db.insert("tb_user", null, values);
            db.close(); // Closing database connection

        }
        catch (Exception ex)
        {
            Log.d("addOption Error:", ex.toString());
        }
    }

    public void addStaff(String userName,String password, String name, int level)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("username", userName);
            values.put("password", password);
            values.put("name", name);
            values.put("level", level);

            // Inserting Row
            db.insert("tb_user", null, values);
            db.close(); // Closing database connection

        }
        catch (Exception ex)
        {
            Log.d("addOption Error:", ex.toString());
        }

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

    public String getValueOption(String nameOption)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("tb_option", new String[] { "value" }
                , "name" + "=?",
                new String[] { String.valueOf(nameOption) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(0);
    }

    public int setValueOption(String nameOption, String value)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("value", value);

            // updating row
            return db.update("tb_option", values, "name" + " = ?",
                    new String[] { nameOption });
        }
        catch (Exception ex)
        {
            Log.d("updateOption Error:", ex.toString());
            return 0;
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

    public int setCheckedRememberPW(boolean checked)
    {
        String valueChecked;
        try
        {
            valueChecked = checked ? "1" : "0";
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("value", valueChecked);

            // updating row
            return db.update("tb_option", values, "name" + " = ?",
                    new String[] { "checkedRememberPW" });
        }
        catch (Exception ex)
        {
            Log.d("updateOption Error:", ex.toString());
            return 0;
        }
    }

}
