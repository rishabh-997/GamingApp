package com.example.android.gamingapp.Payment;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.gamingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends Activity implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.coming_soon);

        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        /*Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });*/
    }

    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {

            String nameoftournament=getIntent().getStringExtra("nameoftournament");
            String uname=getIntent().getStringExtra("username");
            String fees=getIntent().getStringExtra("fees");
            String phone=getIntent().getStringExtra("phone");

            JSONObject options = new JSONObject();
            options.put("name", uname);
            options.put("description", nameoftournament);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
            String email=firebaseUser.getEmail();

            JSONObject preFill = new JSONObject();
            preFill.put("email", email);
            preFill.put("contact", phone);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            String nameoftournament=getIntent().getStringExtra("nameoftournament");
            String uname=getIntent().getStringExtra("username");
            String fees=getIntent().getStringExtra("fees");
            String ph=getIntent().getStringExtra("phone");
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
            String email=firebaseUser.getEmail();
            String ema=email.replace('.','.');
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();


            databaseReference.child("Register").child(nameoftournament).child(uname).child(ph).child(ema).child(razorpayPaymentID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Toast.makeText(PaymentActivity.this,"Successfully Register Happy Gaming",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            databaseReference.child("Check").child(nameoftournament).child(ema).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}

