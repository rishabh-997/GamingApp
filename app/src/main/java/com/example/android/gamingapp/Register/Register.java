package com.example.android.gamingapp.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.gamingapp.Payment.PaymentActivity;
import com.example.android.gamingapp.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
Chip phone,fees;
TextInputEditText username;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phone=findViewById(R.id.registerphone);
        fees=findViewById(R.id.registerfees);
        username=findViewById(R.id.registerusername);
        register=findViewById(R.id.register);

        SharedPreferences result=getSharedPreferences("phone",Context.MODE_PRIVATE);
        final String shphonenumber=result.getString("Value","0000000000").trim();
   final String rfees="50";
        phone.setText(shphonenumber);
        fees.setText(rfees);
        final String nameoftournament="pubg";
        final String uname=username.getText().toString().trim();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Register.this,PaymentActivity.class);
                i.putExtra("nameoftournament",nameoftournament);
                i.putExtra("username",uname);
                i.putExtra("phone",shphonenumber);
                i.putExtra("fees",rfees);
                startActivity(i);
            }
        });















    }
}
