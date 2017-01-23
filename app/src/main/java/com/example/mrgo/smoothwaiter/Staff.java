package com.example.mrgo.smoothwaiter;

import java.io.Serializable;

/**
 * Created by MrGO on 11/01/2017.
 */

public class Staff implements Serializable
{
    private int id;
    private String userName;
    private String password;
    private String stuffName;
    private int stuffLevel;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getStuffName()
    {
        return stuffName;
    }

    public void setStuffName(String stuffName)
    {
        this.stuffName = stuffName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getStuffLevel()
    {
        return stuffLevel;
    }

    public void setStuffLevel(int stuffLevel)
    {
        this.stuffLevel = stuffLevel;
    }
}
