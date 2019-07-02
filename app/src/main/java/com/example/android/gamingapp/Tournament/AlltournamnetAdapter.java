package com.example.android.gamingapp.Tournament;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.gamingapp.R;
import com.example.android.gamingapp.Register.Register;

import java.util.ArrayList;

public class AlltournamnetAdapter  extends  RecyclerView.Adapter<AlltournamnetAdapter.ViewHolder>{

    Context context;
    ArrayList<AlltournamentModel> arrayList;

    public AlltournamnetAdapter(Context context, ArrayList<AlltournamentModel> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public AlltournamnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tournamentdetails, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {
        AlltournamentModel alltournamentModel=arrayList.get(position);
        String startdate=alltournamentModel.getStartdate();
        String enddate=alltournamentModel.getEnddate();
        String starttime=alltournamentModel.getStarttime();
        String endtime=alltournamentModel.getEndtime();
        String fees=alltournamentModel.getFees();
        String winningprice=alltournamentModel.getWinningprice();
        String nameoftournament=alltournamentModel.getNameoftournamnet();
        String gamename=alltournamentModel.getGamename();
        String coordinatorname=alltournamentModel.getCoordinatorname();
        String contactno=alltournamentModel.getContactno();


        viewHolder.startdate.setText(startdate);
        viewHolder.enddate.setText(enddate);
        viewHolder.starttime.setText(starttime);
        viewHolder.endtime.setText(endtime);
        viewHolder.fees.setText(fees);
        viewHolder.winningprice.setText(winningprice);
        viewHolder.nameoftournamnet.setText(nameoftournament);
        viewHolder.gamename.setText(gamename);
        viewHolder.coordinatorname.setText(coordinatorname);
        viewHolder.contactno.setText(contactno);
     //   Picasso.get().load(doc_url).into(viewHolder.contestimage);
        viewHolder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Register.class);
                context.startActivity(i);

            }
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





    public class ViewHolder extends RecyclerView.ViewHolder
    {
TextView startdate,enddate,starttime,endtime,nameoftournamnet,fees,winningprice,gamename,coordinatorname,contactno;
        ImageView contestimage;
        Button register;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);


            startdate=itemView.findViewById(R.id.sdate);
            enddate=itemView.findViewById(R.id.edate);
            starttime=itemView.findViewById(R.id.stime);
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
