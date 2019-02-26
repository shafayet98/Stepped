package com.example.stepped_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView signupId;
    private Button loginId;
    private EditText usernameId;
    private EditText passwordId;

    private static final String SHARED_PREF = "SHARED_PREF";
    private static final String NAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String user = sharedPreferences.getString(NAME, "");
        String pass = sharedPreferences.getString(PASSWORD, "");
        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)){
            goToServiceActivity();
        }

        signupId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        loginId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(validateCredentials()){
                goToServiceActivity();
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            }else{
                clearAll();
                Toast.makeText(MainActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    private void init(){
        signupId = findViewById(R.id.signupId);
        loginId = findViewById(R.id.loginId);
        usernameId = findViewById(R.id.usernameId);
        passwordId = findViewById(R.id.passwordId);
    }

    private boolean validateData(){
        name = usernameId.getText().toString().trim();
        password = passwordId.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !name.equals("")){
            if(!TextUtils.isEmpty(password) && !password.equals("")) {
                return true;
            }
        }
        return false;
    }

    private void goToServiceActivity(){
        Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

    private boolean validateCredentials(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String user = sharedPreferences.getString(NAME, "");
        String pass = sharedPreferences.getString(PASSWORD, "");
        if(validateData()){
            if(name.equals(user) && password.equals(pass)){
                return true;
            }
        }
        return false;
    }

    private void clearAll(){
        usernameId.setText("");
        passwordId.setText("");
    }
}
