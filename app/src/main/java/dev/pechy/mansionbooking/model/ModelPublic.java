package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPublic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("PubVat")
    @Expose
    private Integer pubVat;
    @SerializedName("PubWaterBTH")
    @Expose
    private String pubWaterBTH;
    @SerializedName("PubElectBTH")
    @Expose
    private String pubElectBTH;
    @SerializedName("PubPaymentdate")
    @Expose
    private String pubPaymentdate;
    @SerializedName("PubFine")
    @Expose
    private String pubFine;
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

    public Integer getPubVat() {
        return pubVat;
    }

    public void setPubVat(Integer pubVat) {
        this.pubVat = pubVat;
    }

    public String getPubWaterBTH() {
        return pubWaterBTH;
    }

    public void setPubWaterBTH(String pubWaterBTH) {
        this.pubWaterBTH = pubWaterBTH;
    }

    public String getPubElectBTH() {
        return pubElectBTH;
    }

    public void setPubElectBTH(String pubElectBTH) {
        this.pubElectBTH = pubElectBTH;
    }

    public String getPubPaymentdate() {
        return pubPaymentdate;
    }

    public void setPubPaymentdate(String pubPaymentdate) {
        this.pubPaymentdate = pubPaymentdate;
    }

    public String getPubFine() {
        return pubFine;
    }

    public void setPubFine(String pubFine) {
        this.pubFine = pubFine;
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
