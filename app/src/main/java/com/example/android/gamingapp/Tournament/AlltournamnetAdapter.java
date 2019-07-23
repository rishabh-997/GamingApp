package com.example.android.gamingapp.Tournament;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.gamingapp.R;
import com.example.android.gamingapp.Register.Register;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
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
            ShowBottomSheet activity=(ShowBottomSheet)context;
            activity.bottomSheet(alltournamentModel);
        });
        viewHolder.contestimage.setImageURI(Uri.parse("https://statics.sportskeeda.com/editor/2019/07/b934a-15630410641271-800.jpg"));
        viewHolder.progressBar.setProgress(90);
        //viewHolder.name.setText(name);
        //viewHolder.author.setText(msg);
        //viewHolder.price.setText(prc);
        //Picasso.get().load(photourl).into(viewHolder.bookimage);
            //TODO show & set room id pass if registered
            viewHolder.room_id.setText("");
            viewHolder.room_pass.setText("");


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameoftournamnet, fees, winningprice, gamename, coordinatorname, contactno,room_id,room_pass;
        TextView startdate, enddate, starttime, endtime;
        SimpleDraweeView contestimage;
        Button register;
        View up_arror, down_arrow;
        RelativeLayout layout_after_reg;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_after_reg=itemView.findViewById(R.id.lay_after_reg);
            startdate=itemView.findViewById(R.id.sdate);
            //enddate=itemView.findViewById(R.id.edate);
            progressBar=itemView.findViewById(R.id.progress_bar);
            starttime = itemView.findViewById(R.id.stime);
            room_id=itemView.findViewById(R.id.room_id);
            room_pass=itemView.findViewById(R.id.room_pass);
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
