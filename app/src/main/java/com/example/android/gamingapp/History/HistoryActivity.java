package com.example.android.gamingapp.History;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gamingapp.R;
import com.example.android.gamingapp.Register.Register;
import com.example.android.gamingapp.Tournament.AlltournamentModel;
import com.example.android.gamingapp.Tournament.ShowBottomSheet;
import com.example.android.gamingapp.Tournament.ViewCompletedAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<AlltournamentModel> arrayList;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    AlltournamentModel alltournamentModel;
    HistoryAdapter alltournamnetAdapter;
    String doc_url="sanket agarwal";

    SimpleDraweeView contestimage;
    TextView nameoftournamnet, fees, winningprice;
    TextView startdate, enddate, starttime, endtime;
    Button participants,details;
    RelativeLayout container1,container2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed);

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Tournaments");

        //  storageReference=firebaseStorage.getReference().child("Contestimages/"+System.currentTimeMillis()+".jpg");

        arrayList=new ArrayList<>();
        final RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        alltournamnetAdapter=new HistoryAdapter(this,arrayList);


    /*    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                Uri abc=uri;
                String doc_url=abc.toString();
            }
        });*/

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                arrayList.clear();
                if(dataSnapshot.hasChildren())
                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        String startdate=ds.child("startdate").getValue().toString();
                        String enddate=ds.child("enddate").getValue().toString();
                        String starttime=ds.child("starttime").getValue().toString();
                        String endtime=ds.child("endtime").getValue().toString();
                        String nameoftournamnet=ds.child("nameoftournamnet").getValue().toString();
                        String fees=ds.child("fees").getValue().toString();
                        String winningprice=ds.child("winningprice").getValue().toString();
                        String gamename=ds.child("gamename").getValue().toString();
                        String coordinatorname=ds.child("coordinatorname").getValue().toString();
                        String contactno=ds.child("contactno").getValue().toString();


                        alltournamentModel=new AlltournamentModel(startdate,enddate,starttime,endtime,nameoftournamnet,fees,winningprice,gamename,coordinatorname,contactno,doc_url);
                        arrayList.add(alltournamentModel);
                    }
                recyclerView.setAdapter(alltournamnetAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

    private void initialiseBottomSheet() {

        startdate=findViewById(R.id.sdate);
        starttime = findViewById(R.id.stime);
        nameoftournamnet=findViewById(R.id.not);
        fees=findViewById(R.id.fe);
        winningprice=findViewById(R.id.wp);
        participants=findViewById(R.id.participants);
        details=findViewById(R.id.details);
        container1=findViewById(R.id.bottom_sheet_container1);
        container2=findViewById(R.id.bottom_sheet_container2);
        contestimage=findViewById(R.id.tour_img);
        LinearLayout bottom= findViewById(R.id.bottom_sheet);

        Button button=findViewById(R.id.button_join);


        //TODO show Toast if already registered registered
        button.setOnClickListener(v->{
            Intent i=new Intent(this, Register.class);
            this.startActivity(i);
        });



    }



}

