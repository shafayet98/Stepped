package com.example.stepped_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepped_01.Util.SharedPrefUtility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpId;
    private EditText nameId;
    private EditText emailId;
    private EditText passId;

    private String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        signUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(validateData()){
                saveSignUpData();
                goToServiceActivity();
            }else{
                Toast.makeText(SignUpActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    private void init(){
        signUpId = findViewById(R.id.signUpId);
        nameId = findViewById(R.id.nameId);
        emailId = findViewById(R.id.emailId);
        passId = findViewById(R.id.passId);
    }

    private void saveSignUpData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString(SharedPrefUtility.NAME, name);
        editor.putString(SharedPrefUtility.EMAIL, email);
        editor.putString(SharedPrefUtility.PASSWORD, password);
        editor.apply();
        editor.commit();
    }

    private void goToServiceActivity(){
        Intent intent = new Intent(SignUpActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

    private boolean validateData(){
        name = nameId.getText().toString().trim();
        email = emailId.getText().toString().trim();
        password = passId.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !name.equals("") && noSpecialChars(name)){
            if(!TextUtils.isEmpty(email) && !email.equals("") && isValidEmail(email)) {
                if(!TextUtils.isEmpty(password) && !password.equals("") && password.length() > 6){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean noSpecialChars(String s){
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = pattern.matcher(s);
        Log.d("msg", !matcher.find() + "");
        return !matcher.find();
    }

    private boolean isValidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
