package com.example.android.gamingapp.Tournament;

import android.os.Parcel;
import android.os.Parcelable;

public class AlltournamentModel implements Parcelable {

    private String startdate,enddate,starttime,endtime,nameoftournamnet,fees,winningprice,gamename,coordinatorname,contactno,doc_utl;

    public AlltournamentModel(String startdate, String enddate, String starttime, String endtime, String nameoftournamnet, String fees, String winningprice, String gamename, String coordinatorname, String contactno, String doc_utl) {
        this.startdate = startdate;
        this.enddate = enddate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.nameoftournamnet = nameoftournamnet;
        this.fees = fees;
        this.winningprice = winningprice;
        this.gamename = gamename;
        this.coordinatorname = coordinatorname;
        this.contactno = contactno;
        this.doc_utl = doc_utl;
    }


    protected AlltournamentModel(Parcel in) {
        startdate = in.readString();
        enddate = in.readString();
        starttime = in.readString();
        endtime = in.readString();
        nameoftournamnet = in.readString();
        fees = in.readString();
        winningprice = in.readString();
        gamename = in.readString();
        coordinatorname = in.readString();
        contactno = in.readString();
        doc_utl = in.readString();
    }

    public static final Creator<AlltournamentModel> CREATOR = new Creator<AlltournamentModel>() {
        @Override
        public AlltournamentModel createFromParcel(Parcel in) {
            return new AlltournamentModel(in);
        }

        @Override
        public AlltournamentModel[] newArray(int size) {
            return new AlltournamentModel[size];
        }
    };

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getNameoftournamnet() {
        return nameoftournamnet;
    }

    public void setNameoftournamnet(String nameoftournamnet) {
        this.nameoftournamnet = nameoftournamnet;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getWinningprice() {
        return winningprice;
    }

    public void setWinningprice(String winningprice) {
        this.winningprice = winningprice;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getCoordinatorname() {
        return coordinatorname;
    }

    public void setCoordinatorname(String coordinatorname) {
        this.coordinatorname = coordinatorname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDoc_utl() {
        return doc_utl;
    }

    public void setDoc_utl(String doc_utl) {
        this.doc_utl = doc_utl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startdate);
        dest.writeString(enddate);
        dest.writeString(starttime);
        dest.writeString(endtime);
        dest.writeString(nameoftournamnet);
        dest.writeString(fees);
        dest.writeString(winningprice);
        dest.writeString(gamename);
        dest.writeString(coordinatorname);
        dest.writeString(contactno);
        dest.writeString(doc_utl);
    }
}
