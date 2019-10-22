package com.example.android.gamingapp.History;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.gamingapp.R;
import com.example.android.gamingapp.Tournament.AlltournamentModel;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AlltournamentModel> arrayList;

    public HistoryAdapter(Context context, ArrayList<AlltournamentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int position) {
        AlltournamentModel alltournamentModel = arrayList.get(position);
        String startdate=alltournamentModel.getStartdate();
        String starttime = alltournamentModel.getStarttime();
        String nameoftournament=alltournamentModel.getNameoftournamnet();
        viewHolder.startdate.setText(startdate);
        viewHolder.starttime.setText(starttime);
        viewHolder.nameoftournamnet.setText(nameoftournament);
        viewHolder.play.setOnClickListener(v -> {

        });
        viewHolder.contestimage.setImageURI(Uri.parse("https://statics.sportskeeda.com/editor/2019/07/b934a-15630410641271-800.jpg"));
        //TODO show & set room id pass if registered
        viewHolder.room_id.setText("");
        viewHolder.room_pass.setText("");


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameoftournamnet,room_id,room_pass;
        TextView startdate, starttime;
        SimpleDraweeView contestimage;
        FloatingActionButton play;
        RelativeLayout layout_after_reg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_after_reg=itemView.findViewById(R.id.lay_after_reg);
            startdate=itemView.findViewById(R.id.sdate);
            starttime = itemView.findViewById(R.id.stime);
            room_id=itemView.findViewById(R.id.room_id);
            room_pass=itemView.findViewById(R.id.room_pass);

            nameoftournamnet=itemView.findViewById(R.id.not);

            contestimage=itemView.findViewById(R.id.tour_img);
            play =itemView.findViewById(R.id.registerforcontest);

        }
    }




}


