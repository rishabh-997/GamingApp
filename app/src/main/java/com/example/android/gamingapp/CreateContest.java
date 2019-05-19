package com.example.android.gamingapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreateContest extends AppCompatActivity {
    EditText xstartdate,xenddate,xstarttime,xendtime,xnameoftournamnet,xfees,xwinningprice,xgamename,xcoordinatorname,xcontactno;
    Button createcontest;
    String startdate,enddate,starttime,endtime,nameoftournamnet,fees,winningprice,gamename,coordinatorname,contactno;



    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contest);

        xstartdate=findViewById(R.id.startdate);
        xenddate=findViewById(R.id.enddate);
        xstarttime=findViewById(R.id.starttime);
        xendtime=findViewById(R.id.endtime);
        xnameoftournamnet=findViewById(R.id.name);
        xfees=findViewById(R.id.fees);
        xwinningprice=findViewById(R.id.winningprice);
        xgamename=findViewById(R.id.gamename);
        xcoordinatorname=findViewById(R.id.coordinatorname);
        xcontactno=findViewById(R.id.contactno);
        createcontest=findViewById(R.id.createcontest);



        createcontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startdate=xstartdate.getText().toString().trim();
                enddate=xenddate.getText().toString().trim();
                starttime=xstarttime.getText().toString().trim();
                endtime=xendtime.getText().toString().trim();
                nameoftournamnet=xnameoftournamnet.getText().toString().trim();
                fees=xfees.getText().toString().trim();
                winningprice=xwinningprice.getText().toString().trim();
                gamename=xgamename.getText().toString().trim();
                coordinatorname=xcoordinatorname.getText().toString().trim();
                contactno=xcontactno.getText().toString().trim();


databaseReference=FirebaseDatabase.getInstance().getReference("Tournaments");
CreateContestAdapter createContestAdapter=new CreateContestAdapter(startdate,enddate,starttime,endtime,nameoftournamnet,fees,winningprice,gamename,coordinatorname,contactno);
databaseReference.child(nameoftournamnet).setValue(createContestAdapter).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful())
        {
            Toast.makeText(CreateContest.this,"Successful creat contest",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(CreateContest.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

        }
    }
});





            }
        });


    }
}
