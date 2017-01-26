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

import java.util.ArrayList;
import java.util.List;

public class AddStaffActivity extends AppCompatActivity
{
    Spinner levelAdminSpinner;
    Button addStaffBTN;
    TextView levelPermissionTV;
    EditText userNameET,passwordET,nameET;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        levelPermissionTV = (TextView) findViewById(R.id.levelPermissionTextView);
        levelAdminSpinner = (Spinner) findViewById(R.id.staffLevelSpinner);
        addStaffBTN = (Button) findViewById(R.id.addStaffButton);
        userNameET = (EditText) findViewById(R.id.staffNameEditText);
        passwordET = (EditText) findViewById(R.id.staffPasswordEditText);
        nameET = (EditText) findViewById(R.id.staffNameEditText);




        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("User");
        list.add("Manager");
        list.add("Admin");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelAdminSpinner.setAdapter(adapter);

        levelAdminSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                levelPermissionTV.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void addStaffUser(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        int level = 1;
        switch (levelPermissionTV.getText().toString())
        {
            case "User":
                level = 1;
                break;
            case "Manage":
                level = 2;
                break;
            case "Admin":
                level = 3;
                break;
            default:
                break;
        }

        try
        {
            db.addStaff(userNameET.getText().toString(),passwordET.getText().toString(),nameET.getText().toString(),level);
            Toast.makeText(this, "Add Staff Complete", Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "Total User " + db.getTotalUser(), Toast.LENGTH_SHORT).show();
        }catch (Exception ex)
        {
            Log.d("Add Staff","Add Staff Error");
        }
        
    }
}
