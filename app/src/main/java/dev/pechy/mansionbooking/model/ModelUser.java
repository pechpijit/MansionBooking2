package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("CustomersId")
    @Expose
    private Integer customersId;
    @SerializedName("UAUsername")
    @Expose
    private String uAUsername;
    @SerializedName("UAPassword")
    @Expose
    private String uAPassword;
    @SerializedName("UAName")
    @Expose
    private String uAName;
    @SerializedName("UASocialG")
    @Expose
    private String uASocialG;
    @SerializedName("UASocialF")
    @Expose
    private String uASocialF;
    @SerializedName("UAEmail")
    @Expose
    private String uAEmail;
    @SerializedName("UAImage")
    @Expose
    private String uAImage;
    @SerializedName("UAStatus")
    @Expose
    private Integer uAStatus;
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

    public Integer getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Integer customersId) {
        this.customersId = customersId;
    }

    public String getuAUsername() {
        return uAUsername;
    }

    public void setuAUsername(String uAUsername) {
        this.uAUsername = uAUsername;
    }

    public String getuAPassword() {
        return uAPassword;
    }

    public void setuAPassword(String uAPassword) {
        this.uAPassword = uAPassword;
    }

    public String getuAName() {
        return uAName;
    }

    public void setuAName(String uAName) {
        this.uAName = uAName;
    }

    public String getuASocialG() {
        return uASocialG;
    }

    public void setuASocialG(String uASocialG) {
        this.uASocialG = uASocialG;
    }

    public String getuASocialF() {
        return uASocialF;
    }

    public void setuASocialF(String uASocialF) {
        this.uASocialF = uASocialF;
    }

    public String getuAEmail() {
        return uAEmail;
    }

    public void setuAEmail(String uAEmail) {
        this.uAEmail = uAEmail;
    }

    public String getuAImage() {
        return uAImage;
    }

    public void setuAImage(String uAImage) {
        this.uAImage = uAImage;
    }

    public Integer getuAStatus() {
        return uAStatus;
    }

    public void setuAStatus(Integer uAStatus) {
        this.uAStatus = uAStatus;
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
