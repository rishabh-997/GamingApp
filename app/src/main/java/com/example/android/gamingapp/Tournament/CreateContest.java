package com.example.android.gamingapp.Tournament;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.gamingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreateContest extends AppCompatActivity {
    TextInputEditText xstartdate,xenddate,xstarttime,xendtime,xnameoftournamnet,xfees,xwinningprice,xgamename,xcoordinatorname,xcontactno;
    Button createcontest;
    String startdate,enddate,starttime,endtime,nameoftournamnet,fees,winningprice,gamename,coordinatorname,contactno;
    ImageButton uploadimage;
    String doc_url=" sanketagarwal";
    Uri doc_data=null;
    int capture=1000;


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
        uploadimage=findViewById(R.id.uploadimage);


        firebaseStorage = FirebaseStorage.getInstance();
      //  storageReference=firebaseStorage.getReference().child("Contestimages/"+System.currentTimeMillis()+".jpg");

   /*     uploadimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,capture);
            }
        });*/

        createcontest.setOnClickListener(v -> {

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



   /*         storageReference.putFile(doc_data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri)
                        {
                            Uri abc=uri;
                            doc_url=abc.toString();
                        }
                    });
                }
            });*/


databaseReference=FirebaseDatabase.getInstance().getReference("Tournaments");
if(!(startdate.isEmpty()||nameoftournamnet.isEmpty()||fees.isEmpty()||gamename.isEmpty()||starttime.isEmpty() ||contactno.isEmpty())) {
CreateContestModel createContestModel = new CreateContestModel(startdate, enddate, starttime, endtime, nameoftournamnet, fees, winningprice, gamename, coordinatorname, contactno, doc_url);
databaseReference.child(nameoftournamnet).setValue(createContestModel).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            Toast.makeText(CreateContest.this, "Successfully created contest", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CreateContest.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

        }
    }
});
}else {
Toast.makeText(CreateContest.this,"Please Fill The form Correctly", Toast.LENGTH_LONG).show(); }





        });


    }
}
