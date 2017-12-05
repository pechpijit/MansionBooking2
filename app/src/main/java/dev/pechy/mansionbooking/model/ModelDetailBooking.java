package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelDetailBooking {
    @SerializedName("room")
    @Expose
    private ModelRoom room;
    @SerializedName("roomtype")
    @Expose
    private String roomtype;
    @SerializedName("booking")
    @Expose
    private ModelBooking booking;

    public ModelRoom getRoom() {
        return room;
    }

    public void setRoom(ModelRoom room) {
        this.room = room;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public ModelBooking getBooking() {
        return booking;
    }

    public void setBooking(ModelBooking booking) {
        this.booking = booking;
    }
}
