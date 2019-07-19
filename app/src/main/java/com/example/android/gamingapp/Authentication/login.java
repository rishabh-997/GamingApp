package com.example.android.gamingapp.Authentication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Toast;


import com.example.android.gamingapp.Tournament.MainActivity;
import com.example.android.gamingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity
{
    TextInputEditText username, password;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        firebaseAuth = FirebaseAuth.getInstance();
        requestSmsPermission();



        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            finish();
            startActivity(new Intent(login.this,MainActivity.class));
        }

    }

    public void login(View view) {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(user.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(this,"These fields cannot be left empty ",Toast.LENGTH_LONG).show();
            // username.requestFocus();
            // password.requestFocus();
        }
        else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                finish();
                                Toast.makeText(login.this, "Logged In", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this, MainActivity.class));
                            } else
                                Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void signup_screen(View view) {
        finish();
        startActivity(new Intent(login.this, signup.class));
    }

    public void forgetpassword(View v)
    {
        startActivity(new Intent(login.this,ForgetPassword.class));
    }
    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

}