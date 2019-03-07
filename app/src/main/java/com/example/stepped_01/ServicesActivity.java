package com.example.stepped_01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.stepped_01.Util.SharedPrefUtility;

public class ServicesActivity extends AppCompatActivity {

    private CardView pedometerId;
    private CardView bmiId;
    private CardView timerId;
    private CardView alarmId;
    private Toolbar toolbarId;

    private String bmi, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        init();
        getBmi();

        timerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicesActivity.this, TimerActivity.class));
            }
        });

        pedometerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicesActivity.this, PedometerActivity.class));
            }
        });

        bmiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bmi.equals("")){
                    alertUserForBmi();
                }else{
                    goToBmiActivity();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

    private void init(){
        pedometerId = findViewById(R.id.pedometerId);
        bmiId = findViewById(R.id.bmiId);
        timerId = findViewById(R.id.timerId);
        alarmId =findViewById(R.id.alarmId);
        toolbarId = findViewById(R.id.toolbarId);

        setSupportActionBar(toolbarId);
    }

    private void getBmi(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        bmi = sharedPreferences.getString(SharedPrefUtility.BMI, "");
        result = sharedPreferences.getString(SharedPrefUtility.RESULT, "");
    }

    private void alertUserForBmi(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ServicesActivity.this);
        builder1.setTitle("Your previous Bmi was: " + bmi);
        builder1.setMessage("would you like to calculate again?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       goToBmiActivity();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void goToBmiActivity(){
        startActivity(new Intent(ServicesActivity.this, BmiCalculatorActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                alertLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alertLogout(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ServicesActivity.this);
        builder1.setTitle("Are you sure?");
        builder1.setMessage("Do you really want to logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.logout_flag = true;
                        saveLoginFlag();
                        Toast.makeText(ServicesActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ServicesActivity.this, MainActivity.class));
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void saveLoginFlag(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefUtility.SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SharedPrefUtility.LOGIN_FLAG, MainActivity.logout_flag);

        editor.commit();
        editor.apply();
    }
}
