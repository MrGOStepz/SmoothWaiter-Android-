package com.example.mrgo.smoothwaiter;

import android.content.Intent;
import android.content.SharedPreferences;
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
    DatabaseHandler db = new DatabaseHandler(this);
    SharedPreferences prefs = null;

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

        prefs = getSharedPreferences("com.example.mrgo.smoothwaiter", MODE_PRIVATE);
        if(prefs.getBoolean("FIRST_RUN",true))
        {
            db.createAdminUser();
            db.addOption("totalTable","9");
            db.addOption("checkedRememberPW","0");
            db.addOption("userName","");
            db.addOption("password","");
            prefs.edit().putBoolean("FIRST_RUN",false).commit();
        }

        if(db.getValueOption("checkedRememberPW").equals("0"))
        {
            rememberPasswordCB.setChecked(false);
            db.setCheckedRememberPW(false);
        }
        else
        {
            userNameET.setText(db.getValueOption("userName"));
            passwordET.setText(db.getValueOption("password"));
            rememberPasswordCB.setChecked(true);
            db.setCheckedRememberPW(true);
        }

        Toast.makeText(this, "Total Food " + db.getTotalFood(), Toast.LENGTH_SHORT).show();

    }


    public void forgotPassword(View view)
    {

    }

    public void login(View view)
    {
        Staff staff;
        int countUser = db.validateUser(userNameET.getText().toString(),passwordET.getText().toString());
        int countOption = db.totalOption();

        if(countUser > 0)
        {
            staff = db.getStaffDetail(userNameET.getText().toString());

            if(rememberPasswordCB.isChecked() == false)
            {
                db.setValueOption("userName", "");
                db.setValueOption("password", "");
                db.setCheckedRememberPW(false);
            }
            else
            {
                db.setValueOption("userName", userNameET.getText().toString());
                db.setValueOption("password", passwordET.getText().toString());
                db.setCheckedRememberPW(true);
            }

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

    }
}
