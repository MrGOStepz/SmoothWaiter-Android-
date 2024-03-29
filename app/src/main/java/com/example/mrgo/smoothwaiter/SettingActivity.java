package com.example.mrgo.smoothwaiter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity
{

    EditText totalET;
    Button applyBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        totalET = (EditText) findViewById(R.id.totalTableEditText);
        applyBTN = (Button) findViewById(R.id.applyTotalTableButton);

        DatabaseHandler db = new DatabaseHandler(this);

        totalET.setText(String.valueOf(db.getTotalTable()));

    }

    public void applyTotalTable(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        db.setValueOption("totalTable",totalET.getText().toString());
    }

    public void addStaff(View view)
    {
        Intent addStaffScreen = new Intent(this,AddStaffActivity.class);
        startActivity(addStaffScreen);
    }

    public void addFood(View view)
    {
        Intent addFoodScreen = new Intent(this,AddDishActivity.class);
        startActivity(addFoodScreen);
    }
}
