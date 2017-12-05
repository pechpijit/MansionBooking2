package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBuild {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("BuildName")
    @Expose
    private String buildName;
    @SerializedName("BuildDesc")
    @Expose
    private String buildDesc;
    @SerializedName("BuildImage")
    @Expose
    private String buildImage;
    @SerializedName("BuildLayersum")
    @Expose
    private Integer buildLayersum;
    @SerializedName("BuildRoomsum")
    @Expose
    private Integer buildRoomsum;
    @SerializedName("BuildStatus")
    @Expose
    private Integer buildStatus;
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

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
    }

    public String getBuildImage() {
        return buildImage;
    }

    public void setBuildImage(String buildImage) {
        this.buildImage = buildImage;
    }

    public Integer getBuildLayersum() {
        return buildLayersum;
    }

    public void setBuildLayersum(Integer buildLayersum) {
        this.buildLayersum = buildLayersum;
    }

    public Integer getBuildRoomsum() {
        return buildRoomsum;
    }

    public void setBuildRoomsum(Integer buildRoomsum) {
        this.buildRoomsum = buildRoomsum;
    }

    public Integer getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(Integer buildStatus) {
        this.buildStatus = buildStatus;
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
