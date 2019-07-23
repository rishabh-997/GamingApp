package com.example.android.gamingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.android.gamingapp.Authentication.login;
import com.example.android.gamingapp.Tournament.AllTournament;
import com.example.android.gamingapp.Tournament.AlltournamentModel;
import com.example.android.gamingapp.Tournament.AlltournamnetAdapter;
import com.example.android.gamingapp.Tournament.CreateContest;
import com.example.android.gamingapp.R;
import com.example.android.gamingapp.Register.Register;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.gamingapp.Authentication.ChangePassword;
import com.example.android.gamingapp.GamingApp.ContactUs;
import com.example.android.gamingapp.Payment.PaymentActivity;
import com.example.android.gamingapp.Profile.Profile;
import com.google.firebase.auth.FirebaseAuth;
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

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ShowBottomSheet {
    ArrayList<AlltournamentModel> arrayList;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    FirebaseUser firebaseUser;
    AlltournamentModel alltournamentModel;
    AlltournamnetAdapter alltournamnetAdapter;
    RecyclerView allbookrecycler;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    String doc_url="sanket agarwal";
    ShimmerFrameLayout shimmerFrameLayout;
    BottomSheetBehavior bottomSheetBehavior;
    SimpleDraweeView contestimage;
    TextView nameoftournamnet, fees, winningprice;
    TextView startdate, enddate, starttime, endtime;
    Button participants,details;
    RelativeLayout container1,container2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialiseBottomSheet();



        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);

        Log.d("MainActivity","okkkkk");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Tournaments");

        //  storageReference=firebaseStorage.getReference().child("Contestimages/"+System.currentTimeMillis()+".jpg");

        arrayList=new ArrayList<>();
        final RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        alltournamnetAdapter=new AlltournamnetAdapter(this,arrayList);


    /*    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
                Uri abc=uri;
                String doc_url=abc.toString();
            }
        });*/
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
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
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
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
        bottomSheetBehavior=BottomSheetBehavior.from(bottom);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        Button button=findViewById(R.id.button_join);


        //TODO show Toast if already registered registered
        button.setOnClickListener(v->{
            Intent i=new Intent(this, Register.class);
            this.startActivity(i);
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if(i==BottomSheetBehavior.STATE_COLLAPSED){
                    button.setVisibility(View.VISIBLE);}
                else if(i==BottomSheetBehavior.STATE_HIDDEN) {
                    button.setVisibility(View.GONE);
                }
                else if(i==BottomSheetBehavior.STATE_EXPANDED)
                {}

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
startActivity(new Intent(MainActivity.this,Profile.class));
        } else if (id == R.id.tournaments) {
startActivity(new Intent(MainActivity.this, AllTournament.class));
        } else if (id == R.id.wallet) {
   startActivity(new Intent(MainActivity.this,PaymentActivity.class));
        } else if (id == R.id.changepassword) {
  startActivity(new Intent(MainActivity.this,ChangePassword.class));
        } else if (id == R.id.referandearn) {

        } else if (id == R.id.contactus) {
startActivity(new Intent(MainActivity.this,ContactUs.class));
        }
        else if(id == R.id.complain)
        {

        }
        else if(id == R.id.createcontest)
        {
            startActivity(new Intent(MainActivity.this, CreateContest.class));

        }else if(id ==R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent i=new Intent(MainActivity.this,login.class);
            startActivity(i);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void bottomSheet(AlltournamentModel model) {

        participants.setOnClickListener(v->{
            details.setEnabled(true);
            details.setTextColor(getResources().getColor(R.color.background));
            participants.setEnabled(false);
            participants.setTextColor(getResources().getColor(android.R.color.white));
            container1.setVisibility(View.GONE);
            container2.setVisibility(View.VISIBLE);
        });

        details.setOnClickListener(v->{
            details.setEnabled(false);
            participants.setEnabled(true);
            details.setTextColor(getResources().getColor(android.R.color.white));
            participants.setTextColor(getResources().getColor(R.color.background));
            container1.setVisibility(View.VISIBLE);
            container2.setVisibility(View.GONE);
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        startdate.setText(model.getStartdate());
        starttime.setText(model.getStarttime());
        contestimage.setImageURI(Uri.parse("https://statics.sportskeeda.com/editor/2019/07/b934a-15630410641271-800.jpg"));
        nameoftournamnet.setText(model.getNameoftournamnet());
        fees.setText(model.getFees());
        winningprice.setText(model.getWinningprice());


    }
}
