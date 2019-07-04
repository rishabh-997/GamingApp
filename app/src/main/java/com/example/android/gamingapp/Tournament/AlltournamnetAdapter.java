package com.example.android.gamingapp.Tournament;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
import android.widget.TextView;

import com.example.android.gamingapp.R;
import com.example.android.gamingapp.RVAnimation;
import com.example.android.gamingapp.Register.Register;
import com.google.android.material.chip.Chip;

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
        String startdate="From: "+alltournamentModel.getStartdate();
        String enddate="To: "+alltournamentModel.getEnddate();
        String starttime = "Starts at "+alltournamentModel.getStarttime();
        String endtime="Ends at "+alltournamentModel.getEndtime();
        String nameoftournament=alltournamentModel.getNameoftournamnet();
        String gamename=alltournamentModel.getGamename();
        String winningprice="Prize: "+alltournamentModel.getWinningprice();
        String coordinatorname="Coordinator: "+alltournamentModel.getCoordinatorname();
        String fees="Fees: "+alltournamentModel.getFees();
        String contactno=alltournamentModel.getContactno();


        viewHolder.down_arrow.setOnClickListener(v -> {
            viewHolder.down_arrow.setVisibility(View.GONE);
            viewHolder.up_arror.setVisibility(View.VISIBLE);
            RVAnimation.expand(viewHolder.linearLayout);
        });
        viewHolder.up_arror.setOnClickListener(v -> {
            viewHolder.up_arror.setVisibility(View.GONE);
            viewHolder.down_arrow.setVisibility(View.VISIBLE);
            RVAnimation.collapse(viewHolder.linearLayout);

        });
                viewHolder.startdate.setText(startdate);
        viewHolder.enddate.setText(enddate);
        viewHolder.starttime.setText(starttime);
        viewHolder.endtime.setText(endtime);
        viewHolder.nameoftournamnet.setText(nameoftournament);
        viewHolder.gamename.setText(gamename);
        viewHolder.fees.setText(fees);
        viewHolder.winningprice.setText(winningprice);
        viewHolder.coordinatorname.setText(coordinatorname);
        viewHolder.contactno.setText(contactno);
//           Picasso.get().load(doc_url).into(viewHolder.contestimage);
        viewHolder.register.setOnClickListener(v -> {
            Intent i=new Intent(context,Register.class);
            context.startActivity(i);

        });

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
        Chip startdate, enddate, starttime, endtime;
        ImageView contestimage;
        Button register;
        View up_arror, down_arrow;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            startdate=itemView.findViewById(R.id.sdate);
            enddate=itemView.findViewById(R.id.edate);
            starttime = itemView.findViewById(R.id.stime);
            up_arror = itemView.findViewById(R.id.up_arrow);
            down_arrow = itemView.findViewById(R.id.down_arrow);
            endtime=itemView.findViewById(R.id.etime);
            nameoftournamnet=itemView.findViewById(R.id.not);
            fees=itemView.findViewById(R.id.fe);
            winningprice=itemView.findViewById(R.id.wp);
            gamename=itemView.findViewById(R.id.gname);
            coordinatorname=itemView.findViewById(R.id.coordi);
            contactno=itemView.findViewById(R.id.cont);
            contestimage=itemView.findViewById(R.id.image);
            register=itemView.findViewById(R.id.registerforcontest);

        }
    }




}
