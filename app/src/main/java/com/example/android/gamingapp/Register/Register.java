package com.example.android.gamingapp.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gamingapp.Payment.PaymentActivity;
import com.example.android.gamingapp.Profile.Profile;
import com.example.android.gamingapp.Profile.ProfileModel;
import com.example.android.gamingapp.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
Chip phone,fees;
TextInputEditText username;
Button register;
String email,shphonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        phone=findViewById(R.id.registerphone);
//        fees=findViewById(R.id.registerfees);
        username=findViewById(R.id.registerusername);
        register=findViewById(R.id.register);
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String emai1=firebaseUser.getEmail();

        SharedPreferences result=getSharedPreferences("phone",Context.MODE_PRIVATE);
         shphonenumber=result.getString("Value","0000000000").trim();
   final String rfees="50";
        phone.setText(emai1);
        fees.setText(rfees);
        final String nameoftournament="pubg";
        final String uname=username.getText().toString().trim();
         DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("profiledetails");
         String ema=emai1.replace('.',',');
        if(shphonenumber.equals("0000000000")){
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {

                    String name1 = dataSnapshot.child(ema).child("name").getValue().toString();

                    shphonenumber = dataSnapshot.child(ema).child("phonenumber").getValue().toString();


                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Register.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }


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
