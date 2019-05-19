package com.example.android.gamingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView pname,pphone,pemail;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    FirebaseUser firebaseUser;
    ProfileModel profileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pname=findViewById(R.id.profilename);
        pphone=findViewById(R.id.profilephone);
        pemail=findViewById(R.id.profileemail);
        Toast.makeText(Profile.this,"1",Toast.LENGTH_SHORT).show();

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("profiledetails");
Toast.makeText(Profile.this,"2",Toast.LENGTH_SHORT).show();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final String uid=firebaseUser.getUid().toString();
        Toast.makeText(Profile.this,uid,Toast.LENGTH_SHORT).show();


        SharedPreferences result=getSharedPreferences("phone",Context.MODE_PRIVATE);
        final String shphonenumber=result.getString("Value","0000000000").trim();





        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            { Toast.makeText(Profile.this,"4",Toast.LENGTH_SHORT).show();


                    Toast.makeText(Profile.this,"5",Toast.LENGTH_SHORT).show();
                    String name1 = dataSnapshot.child(shphonenumber).child("name").getValue().toString();
                        String email1 = dataSnapshot.child(shphonenumber).child("email").getValue().toString();
                        String phone1 = dataSnapshot.child(shphonenumber).child("phonenumber").getValue().toString();

                         ProfileModel profileModel = new ProfileModel(name1, email1, phone1);


                        pname.setText(name1);
                        pphone.setText(phone1);
                        pemail.setText(email1);



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {          }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {            }
        };
        databaseReference.addChildEventListener(childEventListener);








    }
}
