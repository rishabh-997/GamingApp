package com.example.android.gamingapp.Authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.gamingapp.MainActivity;
import com.example.android.gamingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class signup extends AppCompatActivity {
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;
    EditText username,pone,email,pass,otp;
    Button submit,otpverify,submitotp;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();


        otpverify=findViewById(R.id.otpverify);
      otp=findViewById(R.id.otp);
        username = findViewById(R.id.username);
        pone = findViewById(R.id.userphone);
        email = findViewById(R.id.useremail);
        submit = findViewById(R.id.makaccount);
        pass = findViewById(R.id.userpass);
        submitotp=findViewById(R.id.submitotp);
        String namei = username.getText().toString().trim();
        String ph = pone.getText().toString().trim();
        String ema = email.getText().toString().trim();
        String passwo = pass.getText().toString().trim();


        submit.setVisibility(View.GONE);
        submitotp.setVisibility(View.GONE);

otpverify.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        StartFirebaseLogin();
        submitotp.setVisibility(View.VISIBLE);
        String ph = pone.getText().toString().trim();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+ph,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                signup.this,        // Activity (for callback binding)
                mCallback);

    }
});


        databaseReference=firebaseDatabase.getReference("profiledetails");


        submitotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String otpn = otp.getText().toString().trim();

                boolean work = false;
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otpn);
                firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // startActivity(new Intent(signup.this,MainActivity.class));
                                    //finish();
                                    Toast.makeText(signup.this, " OTP Verified", Toast.LENGTH_SHORT).show();
                                    submit.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(signup.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });











        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitotp.setVisibility(View.GONE);
                String namei = username.getText().toString().trim();
                final String ph = pone.getText().toString().trim();
                String ema = email.getText().toString().trim();
                String passwo = pass.getText().toString().trim();
                final String otpn = otp.getText().toString().trim();
                if (otpn==null) {
                    Toast.makeText(signup.this, "please verify otp", Toast.LENGTH_LONG).show();
                }
                else if(namei==null){
                    Toast.makeText(signup.this, "please fill name", Toast.LENGTH_LONG).show();
                }
                else if(ema==null){
                    Toast.makeText(signup.this, "please fill email", Toast.LENGTH_LONG).show();
                }
                else if(passwo==null)
                    Toast.makeText(signup.this, "please fill passwo", Toast.LENGTH_LONG).show();
                else

                {
                    final ProgressDialog progressDialog = new ProgressDialog(signup.this);
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(ema, passwo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                finish();
                                Toast.makeText(signup.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                email.setText("");
                                pass.setText("");


                                sharedPreferences=getSharedPreferences("phone",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("Value",ph);
                                editor.apply();

                                startActivity(new Intent(signup.this, MainActivity.class));
                            } else
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



                    String uid = firebaseAuth.getCurrentUser().getUid();



                    signupmodel sign = new signupmodel(namei, ema, ph, passwo);
                        databaseReference.child(ph).setValue(sign).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                    Toast.makeText(signup.this, "Uploaded on FireBase", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });










                }

            }
        });



    }


    private void StartFirebaseLogin() {

        firebaseAuth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(signup.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(signup.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }





}
