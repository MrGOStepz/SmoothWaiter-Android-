package com.example.mrgo.smoothwaiter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.GO on 27/01/2017.
 */

public class ListFoodStatus
{
    private String table = "";
    public List<Food> listFood = new ArrayList<Food>();
    private String name = "";

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTable()
    {
        return table;
    }

    public void setTable(String table)
    {
        this.table = table;
    }
}
