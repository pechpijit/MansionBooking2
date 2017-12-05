package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelListBooking {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("BuildId")
    @Expose
    private Integer buildId;
    @SerializedName("RoomID")
    @Expose
    private Integer roomID;
    @SerializedName("UserAppID")
    @Expose
    private Integer userAppID;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("buildName")
    @Expose
    private String buildName;
    @SerializedName("roomName")
    @Expose
    private String roomName;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getUserAppID() {
        return userAppID;
    }

    public void setUserAppID(Integer userAppID) {
        this.userAppID = userAppID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
