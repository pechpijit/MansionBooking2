package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBooking {
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
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("paymentTime")
    @Expose
    private Object paymentTime;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Object paymentTime) {
        this.paymentTime = paymentTime;
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
