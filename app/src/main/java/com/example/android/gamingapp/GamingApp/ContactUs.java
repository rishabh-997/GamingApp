package com.example.android.gamingapp.GamingApp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.gamingapp.R;

public class ContactUs extends AppCompatActivity {
EditText phone,email;
Button sendcall,sendemail,rate,fb,insta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        phone=findViewById(R.id.callin);
        email=findViewById(R.id.emailin);

        sendcall=findViewById(R.id.call);
        sendemail=findViewById(R.id.emaik);
        rate=findViewById(R.id.rateus);
        fb=findViewById(R.id.fb);
        insta=findViewById(R.id.insta);

        sendcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                String number=phone.getText().toString().trim();
                if(number.trim().isEmpty())
                {
                    i.setData(Uri.parse("+919879580784"));
                }
                else
                {
                    i.setData(Uri.parse("+91"+number));
                }

                if(ActivityCompat.checkSelfPermission(ContactUs.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(ContactUs.this,"please give epermission to call",Toast.LENGTH_LONG);
                    requestPermission();
                }
                else
                {
                    startActivity(i);
                }



            }

            private void requestPermission() {
                ActivityCompat.requestPermissions(ContactUs.this,new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });



        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("email"));
                String[] s={"sanketagarwal702@gmail.com","xyz@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL,s);
                i.putExtra(Intent.EXTRA_SUBJECT,"I Found this problem in APP");
                i.putExtra(Intent.EXTRA_TEXT,"HI i Want this improvemnet in this app and i am facing this r" +
                        "problem in app ");
                i.setType("message/rfc822");
                Intent chooser=Intent.createChooser(i,"Launch Email");
                startActivity(chooser);


            }
        });





















    }
}
