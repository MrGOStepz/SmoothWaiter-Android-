package com.example.mrgo.smoothwaiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListOrderActivity extends AppCompatActivity
{
    ListView foodListLV;
    Button confirmMenuBTN;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        foodListLV = (ListView) findViewById(R.id.confirmMenuListView);
        confirmMenuBTN = (Button) findViewById(R.id.confirmOrderButton);

        String[] listMenu = ListOrderMenu.menuList.toArray(new String[0]);
        adapter = new StaffAdapter(this, listMenu);
        foodListLV.setAdapter(adapter);
    }

    public void confirmOrder(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        for (int i = 0;i < adapter.getCount();i++)
        {
            db.addMenuOrder(ListOrderMenu.Table,adapter.getItem(i).toString(),CurrentUserLogin.name);
        }

        ListOrderMenu.menuList = new ArrayList<>();

        Intent intent = new Intent(this,OrderActivity.class);
        startActivity(intent);
    }
}
