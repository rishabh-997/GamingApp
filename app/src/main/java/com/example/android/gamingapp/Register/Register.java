package com.example.android.gamingapp.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.gamingapp.Payment.PaymentActivity;
import com.example.android.gamingapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

public class Register extends AppCompatActivity implements PaymentStatusListener {
    TextView phone, fees;
    TextInputEditText username;
    Button register;
    String email, shphonenumber="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Create instance of EasyUpiPayment
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                .setPayeeVpa("8318526422@paytm")
                .setPayeeName("TANMAY PAL")
                .setTransactionRefId("1234567890_tanmay_pal_refid")
                .setTransactionId("1234567890_tanmay_pal_id")
                .setDescription("tanmay ki testing")
                .setAmount("1.0")
                .build();

        //Register Listener for Events
        easyUpiPayment.setPaymentStatusListener(this);


        username = findViewById(R.id.registerusername);
        register = findViewById(R.id.register);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String emai1 = firebaseUser.getEmail();

        SharedPreferences result = getSharedPreferences("phone", Context.MODE_PRIVATE);

        final String nameoftournament = "pubg";
        final String uname = username.getText().toString().trim();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("profiledetails");
        String ema = emai1.replace('.', ',');
        if (shphonenumber.equals("")) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //String name1 = dataSnapshot.child(ema).child("name").getValue().toString();

                    //shphonenumber = dataSnapshot.child(ema).child("phonenumber").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Register.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        register.setOnClickListener(v -> {

            easyUpiPayment.startPayment();
            /*Intent i = new Intent(Register.this, PaymentActivity.class);
            i.putExtra("nameoftournament", nameoftournament);
            i.putExtra("username", uname);
            startActivity(i);*/
        });
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {

        Log.i("Payment Status","Completed transaction");
        Toast.makeText(this, "Your Payment Transaction was Completed", Toast.LENGTH_SHORT).show();
        //what to do next
    }

    @Override
    public void onTransactionSuccess() {

        Toast.makeText(this, "Your Payment Transaction was Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSubmitted() {

        Toast.makeText(this, "Your Payment Transaction was submitted, it may take time", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionFailed() {

        Toast.makeText(this, "Your Payment Transaction was Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Your Payment Transaction was cancelled", Toast.LENGTH_SHORT).show();
    }
}
