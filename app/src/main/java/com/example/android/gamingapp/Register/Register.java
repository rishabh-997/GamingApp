package com.example.android.gamingapp.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gamingapp.Payment.PaymentActivity;
import com.example.android.gamingapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
TextView phone,fees;
EditText username;
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
