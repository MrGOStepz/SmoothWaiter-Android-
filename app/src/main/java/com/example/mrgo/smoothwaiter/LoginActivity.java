package com.example.mrgo.smoothwaiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    EditText userNameET,passwordET;
    Button loginBTN;
    CheckBox rememberPasswordCB;
    TextView forgotPasswordTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameET = (EditText) findViewById(R.id.userNameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        loginBTN = (Button) findViewById(R.id.loginButton);
        rememberPasswordCB = (CheckBox) findViewById(R.id.rememberPWCB);
        forgotPasswordTV = (TextView) findViewById(R.id.forgotPWTV);


        userNameET.setText("admin");
        passwordET.setText("admin");

    }


    public void forgotPassword(View view)
    {

    }

    public void login(View view)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        Staff staff;
        int countUser = db.validateUser(userNameET.getText().toString(),passwordET.getText().toString());
        int countOption = db.totalOption();

        if(countUser > 0)
        {
            staff = db.getStaffDetail(userNameET.getText().toString());


            Intent orderScreen = new Intent(view.getContext(), OrderActivity.class);
            orderScreen.putExtra("stuffKey", staff);
            startActivity(orderScreen);
            Toast.makeText(this, "Password correct", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Total Option " + countOption, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickCheckbox(View view)
    {
        if(rememberPasswordCB.isChecked() == true)
        {
            rememberPasswordCB.setChecked(false);
        }
        else
        {
            rememberPasswordCB.setChecked(true);
        }
    }
}