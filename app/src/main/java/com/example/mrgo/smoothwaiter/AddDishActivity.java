package com.example.mrgo.smoothwaiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class AddDishActivity extends AppCompatActivity
{

    EditText foodNameET,priceET,descriptionFoodET;
    TextView categoryNameTV;
    Spinner categoryNameSP;
    Button addFoodBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        foodNameET = (EditText) findViewById(R.id.foodNameEditText);
        categoryNameTV = (TextView) findViewById(R.id.categoryNameTextView);
        descriptionFoodET = (EditText) findViewById(R.id.descriptionFoodEditText);
        priceET = (EditText) findViewById(R.id.priceEditText);
        categoryNameSP = (Spinner) findViewById(R.id.categorySpinner);
        addFoodBTN = (Button) findViewById(R.id.addFoodButton);

        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Entree");
        list.add("Main");
        list.add("Desert");
        list.add("Beverage");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryNameSP.setAdapter(adapter);

        categoryNameSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                categoryNameTV.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    public void addFood(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        int categoryID = 2;
        switch (categoryNameTV.getText().toString())
        {
            case "Entree":
                categoryID = 1;
                break;
            case "Main":
                categoryID = 2;
                break;
            case "Desert":
                categoryID = 3;
                break;
            case "Beverage":
                categoryID = 4;
                break;
            default:
                break;
        }

        try
        {
            db.addFood(foodNameET.getText().toString(),descriptionFoodET.getText().toString(),categoryID, Integer.parseInt(priceET.getText().toString()));
            Toast.makeText(this, "Add Food Complete", Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "Total Food " + db.getTotalFood(), Toast.LENGTH_SHORT).show();


        }catch (Exception ex)
        {
            Log.d("Add Food","Add Food Error" + ex.toString());
        }
    }
}
