package com.example.android.gamingapp.Tournament;

public class RoomModel {

    public String roomid,roompassword;

    public RoomModel(String roomid, String roompassword) {
        this.roomid = roomid;
        this.roompassword = roompassword;
    }

    public RoomModel() {
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoompassword() {
        return roompassword;
    }

    public void setRoompassword(String roompassword) {
        this.roompassword = roompassword;
    }
}
