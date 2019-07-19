package com.example.android.gamingapp.Tournament;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.gamingapp.R;
//import com.example.android.gamingapp.RVAnimation;
import com.example.android.gamingapp.Register.Register;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AlltournamnetAdapter  extends RecyclerView.Adapter<AlltournamnetAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AlltournamentModel> arrayList;

    public AlltournamnetAdapter(Context context, ArrayList<AlltournamentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AlltournamnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tournamentdetails, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        AlltournamentModel alltournamentModel = arrayList.get(position);
        String startdate=alltournamentModel.getStartdate();
        String enddate=alltournamentModel.getEnddate();
        String starttime = alltournamentModel.getStarttime();
        String endtime=alltournamentModel.getEndtime();
        String nameoftournament=alltournamentModel.getNameoftournamnet();
        String gamename=alltournamentModel.getGamename();
        String winningprice=alltournamentModel.getWinningprice();
        String coordinatorname="Coordinator: "+alltournamentModel.getCoordinatorname();
        String fees=alltournamentModel.getFees();
        String contactno=alltournamentModel.getContactno();

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        String email=firebaseUser.getEmail();
        String ema=email.replace('.','.');


       FirebaseDatabase database= FirebaseDatabase.getInstance();
       DatabaseReference databaseReference=database.getReference().child("Check");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot db: dataSnapshot.getChildren()){
                   if(ema.equals(db.getValue())){
                                               //   FEtch information from Room
                       DatabaseReference databaseReference2=database.getReference().child("Room");
                       databaseReference2.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               String roomid=dataSnapshot.child(nameoftournament).getValue().toString();
                               String roompassword=dataSnapshot.child(nameoftournament).getValue().toString();
                               if(!roomid.equals("Empty") && !roompassword.equals("Empty")){
                                   //Todo: show visiblity of extended tournamnet ans set value of this 

                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {

                           }
                       });

                   }
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });




        viewHolder.startdate.setText(startdate);
        //viewHolder.enddate.setText(enddate);
        viewHolder.starttime.setText(starttime);
        //viewHolder.endtime.setText(endtime);
        viewHolder.nameoftournamnet.setText(nameoftournament);
        //viewHolder.gamename.setText(gamename);
        viewHolder.fees.setText(fees);
        viewHolder.winningprice.setText(winningprice);
        //viewHolder.coordinatorname.setText(coordinatorname);
        //viewHolder.contactno.setText(contactno);
//           Picasso.get().load(doc_url).into(viewHolder.contestimage);
        viewHolder.register.setOnClickListener(v -> {
            Intent i=new Intent(context,Register.class);
            context.startActivity(i);

        });
        viewHolder.contestimage.setImageURI(Uri.parse("https://statics.sportskeeda.com/editor/2019/07/b934a-15630410641271-800.jpg"));
        viewHolder.progressBar.setProgress(90);
        //viewHolder.name.setText(name);
        //viewHolder.author.setText(msg);
        //viewHolder.price.setText(prc);
        //Picasso.get().load(photourl).into(viewHolder.bookimage);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameoftournamnet, fees, winningprice, gamename, coordinatorname, contactno;
        TextView startdate, enddate, starttime, endtime;
        SimpleDraweeView contestimage;
        Button register;
        View up_arror, down_arrow;
        LinearLayout linearLayout;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //linearLayout = itemView.findViewById(R.id.linear_layout);
            startdate=itemView.findViewById(R.id.sdate);
            //enddate=itemView.findViewById(R.id.edate);
            progressBar=itemView.findViewById(R.id.progress_bar);
            starttime = itemView.findViewById(R.id.stime);
            //up_arror = itemView.findViewById(R.id.up_arrow);
            //down_arrow = itemView.findViewById(R.id.down_arrow);
            //endtime=itemView.findViewById(R.id.etime);
            nameoftournamnet=itemView.findViewById(R.id.not);
            fees=itemView.findViewById(R.id.fe);
            winningprice=itemView.findViewById(R.id.wp);
            //gamename=itemView.findViewById(R.id.gname);
            //coordinatorname=itemView.findViewById(R.id.coordi);
            //contactno=itemView.findViewById(R.id.cont);
            contestimage=itemView.findViewById(R.id.tour_img);
            register=itemView.findViewById(R.id.registerforcontest);

        }
    }




}
