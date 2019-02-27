package com.example.stepped_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.stepped_01.Bmi.Bmi;
import com.example.stepped_01.Bmi.BmiCalculations;

public class BmiCalculatorActivity extends AppCompatActivity {


    private Button calculateBmiId;
    private EditText heightId;
    private EditText weightId;
    private EditText ageId;
    private RadioGroup radioGroupId;
    private RadioButton maleId;
    private RadioButton femaleId;

    Bmi bmi;
    BmiCalculations calculations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        init();

        calculateBmiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setUpBmi()){
                    Intent intent = new Intent(BmiCalculatorActivity.this, ResultBmiActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Bmi", calculations.getBmiNumber());
                    bundle.putString("Result", calculations.getResultOnBmi());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(BmiCalculatorActivity.this, "Wrong inputs", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
            }
        });

    }

    private void init(){
        calculateBmiId = findViewById(R.id.calculateBmiId);
        heightId = findViewById(R.id.heightId);
        weightId = findViewById(R.id.weightId);
        ageId = findViewById(R.id.ageId);
        radioGroupId = findViewById(R.id.radioGroupId);
        maleId = findViewById(R.id.maleId);
        femaleId = findViewById(R.id.femaleId);
    }

    private boolean setUpBmi(){
        String height = heightId.getText().toString().trim();
        String weight = weightId.getText().toString().trim();
        String age = ageId.getText().toString().trim();
        if(validate(height, weight, age)){
            bmi = new Bmi(Double.parseDouble(heightId.getText().toString()),
                    Double.parseDouble(weightId.getText().toString()),
                    getCheckedValue(),
                    Integer.parseInt(ageId.getText().toString()));

            calculations = new BmiCalculations(bmi);
            return true;
        }
        return false;
    }

    private boolean validate(String height, String weight, String age){
        if(!TextUtils.isEmpty(height) && !height.equals("")){
            if(!TextUtils.isEmpty(weight) && !weight.equals("")){
                if(!TextUtils.isEmpty(age) && !age.equals("")){
                    if(radioGroupId.getCheckedRadioButtonId() != -1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String getCheckedValue(){
        int id = radioGroupId.getCheckedRadioButtonId();

        if(id == R.id.maleId){
            return "Male";
        }else if(id == R.id.femaleId){
            return "Female";
        }
        return "";
    }

    private void clearAll(){
        heightId.setText("");
        weightId.setText("");
        ageId.setText("");
        radioGroupId.clearCheck();
    }

}
