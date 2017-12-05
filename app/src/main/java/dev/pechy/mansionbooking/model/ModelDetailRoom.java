package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelDetailRoom {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("BuildName")
    @Expose
    private String buildName;
    @SerializedName("TypeName")
    @Expose
    private String typeName;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Desc")
    @Expose
    private Object desc;
    @SerializedName("Monthlyprice")
    @Expose
    private String monthlyprice;
    @SerializedName("Status")
    @Expose
    private Integer status;

    @SerializedName("LocationId")
    @Expose
    private Integer LocationId;
    @SerializedName("BuildId")
    @Expose
    private Integer BuildId;
    @SerializedName("TypeId")
    @Expose
    private Integer TypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public String getMonthlyprice() {
        return monthlyprice;
    }

    public void setMonthlyprice(String monthlyprice) {
        this.monthlyprice = monthlyprice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLocationId() {
        return LocationId;
    }

    public void setLocationId(Integer locationId) {
        LocationId = locationId;
    }

    public Integer getBuildId() {
        return BuildId;
    }

    public void setBuildId(Integer buildId) {
        BuildId = buildId;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer typeId) {
        TypeId = typeId;
    }
}
