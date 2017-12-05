package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRoom {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("BuildId")
    @Expose
    private Integer buildId;
    @SerializedName("RoomtypeId")
    @Expose
    private Integer roomtypeId;
    @SerializedName("RoomCode")
    @Expose
    private String roomCode;
    @SerializedName("RoomName")
    @Expose
    private String roomName;
    @SerializedName("RoomImage")
    @Expose
    private String roomImage;
    @SerializedName("RoomFloor")
    @Expose
    private Integer roomFloor;
    @SerializedName("RoomDesc")
    @Expose
    private String roomDesc;
    @SerializedName("RoomDailyprice")
    @Expose
    private String roomDailyprice;
    @SerializedName("RoomMonthlyprice")
    @Expose
    private String roomMonthlyprice;
    @SerializedName("RoomStatus")
    @Expose
    private Integer roomStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public Integer getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Integer roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public String getRoomDailyprice() {
        return roomDailyprice;
    }

    public void setRoomDailyprice(String roomDailyprice) {
        this.roomDailyprice = roomDailyprice;
    }

    public String getRoomMonthlyprice() {
        return roomMonthlyprice;
    }

    public void setRoomMonthlyprice(String roomMonthlyprice) {
        this.roomMonthlyprice = roomMonthlyprice;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
