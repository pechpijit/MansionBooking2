package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProvince {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("PROVINCE_CODE")
    @Expose
    private String PROVINCE_CODE;
    @SerializedName("PROVINCE_NAME")
    @Expose
    private String PROVINCE_NAME;
    @SerializedName("GEO_ID")
    @Expose
    private Integer GEO_ID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPROVINCE_CODE() {
        return PROVINCE_CODE;
    }

    public void setPROVINCE_CODE(String PROVINCE_CODE) {
        this.PROVINCE_CODE = PROVINCE_CODE;
    }

    public String getPROVINCE_NAME() {
        return PROVINCE_NAME;
    }

    public void setPROVINCE_NAME(String PROVINCE_NAME) {
        this.PROVINCE_NAME = PROVINCE_NAME;
    }

    public Integer getGEO_ID() {
        return GEO_ID;
    }

    public void setGEO_ID(Integer GEO_ID) {
        this.GEO_ID = GEO_ID;
    }
}
