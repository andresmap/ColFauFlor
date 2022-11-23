package com.example.andresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPasword;
    Button mButtonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewRegister=findViewById(R.id.TextViewRegister);
        mTextInputEmail=findViewById(R.id.textinputcorreoElectronico);
        mTextInputPasword=findViewById(R.id.textinputPasword);
        mButtonlogin=findViewById(R.id.btnlogin);

        mButtonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });



        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String email=mTextInputEmail.getText().toString();
        String pasword=mTextInputPasword.getText().toString();
        Log.d("campo","email"+email);
        Log.d( "campo","pasword"+pasword);
    }
}