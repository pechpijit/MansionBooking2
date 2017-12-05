package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLocation {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("AdminId")
    @Expose
    private Integer adminId;
    @SerializedName("LoName")
    @Expose
    private String loName;
    @SerializedName("LoAddress")
    @Expose
    private String loAddress;
    @SerializedName("LoDistrict")
    @Expose
    private Integer loDistrict;
    @SerializedName("LoAmphoe")
    @Expose
    private Integer loAmphoe;
    @SerializedName("LoProvince")
    @Expose
    private Integer loProvince;
    @SerializedName("LoZipcode")
    @Expose
    private Integer loZipcode;
    @SerializedName("LoLatitude")
    @Expose
    private String loLatitude;
    @SerializedName("LoLongitude")
    @Expose
    private String loLongitude;
    @SerializedName("LoTaxnumber")
    @Expose
    private Integer loTaxnumber;
    @SerializedName("LoEmail")
    @Expose
    private String loEmail;
    @SerializedName("LoPhone")
    @Expose
    private String loPhone;
    @SerializedName("LoImage")
    @Expose
    private String loImage;
    @SerializedName("LoStart")
    @Expose
    private Integer loStart;
    @SerializedName("LoStatus")
    @Expose
    private String loStatus;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getLoName() {
        return loName;
    }

    public void setLoName(String loName) {
        this.loName = loName;
    }

    public String getLoAddress() {
        return loAddress;
    }

    public void setLoAddress(String loAddress) {
        this.loAddress = loAddress;
    }

    public Integer getLoDistrict() {
        return loDistrict;
    }

    public void setLoDistrict(Integer loDistrict) {
        this.loDistrict = loDistrict;
    }

    public Integer getLoAmphoe() {
        return loAmphoe;
    }

    public void setLoAmphoe(Integer loAmphoe) {
        this.loAmphoe = loAmphoe;
    }

    public Integer getLoProvince() {
        return loProvince;
    }

    public void setLoProvince(Integer loProvince) {
        this.loProvince = loProvince;
    }

    public Integer getLoZipcode() {
        return loZipcode;
    }

    public void setLoZipcode(Integer loZipcode) {
        this.loZipcode = loZipcode;
    }

    public String getLoLatitude() {
        return loLatitude;
    }

    public void setLoLatitude(String loLatitude) {
        this.loLatitude = loLatitude;
    }

    public String getLoLongitude() {
        return loLongitude;
    }

    public void setLoLongitude(String loLongitude) {
        this.loLongitude = loLongitude;
    }

    public Integer getLoTaxnumber() {
        return loTaxnumber;
    }

    public void setLoTaxnumber(Integer loTaxnumber) {
        this.loTaxnumber = loTaxnumber;
    }

    public String getLoEmail() {
        return loEmail;
    }

    public void setLoEmail(String loEmail) {
        this.loEmail = loEmail;
    }

    public String getLoPhone() {
        return loPhone;
    }

    public void setLoPhone(String loPhone) {
        this.loPhone = loPhone;
    }

    public String getLoImage() {
        return loImage;
    }

    public void setLoImage(String loImage) {
        this.loImage = loImage;
    }

    public Integer getLoStart() {
        return loStart;
    }

    public void setLoStart(Integer loStart) {
        this.loStart = loStart;
    }

    public String getLoStatus() {
        return loStatus;
    }

    public void setLoStatus(String loStatus) {
        this.loStatus = loStatus;
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
