package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCustomer {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("CusPassport")
    @Expose
    private String cusPassport;
    @SerializedName("CusFirstname")
    @Expose
    private String cusFirstname;
    @SerializedName("CusLastname")
    @Expose
    private String cusLastname;
    @SerializedName("CusPhone")
    @Expose
    private String cusPhone;
    @SerializedName("CusEmail")
    @Expose
    private String cusEmail;
    @SerializedName("CusNation")
    @Expose
    private String cusNation;
    @SerializedName("CusAddress")
    @Expose
    private String cusAddress;
    @SerializedName("CusDistrict")
    @Expose
    private Integer cusDistrict;
    @SerializedName("CusAmphoe")
    @Expose
    private Integer cusAmphoe;
    @SerializedName("CusProvince")
    @Expose
    private Integer cusProvince;
    @SerializedName("CusZipcode")
    @Expose
    private Integer cusZipcode;
    @SerializedName("CusDesc")
    @Expose
    private Integer cusDesc;
    @SerializedName("CusImage")
    @Expose
    private String cusImage;
    @SerializedName("CusStatus")
    @Expose
    private Integer cusStatus;
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

    public String getCusPassport() {
        return cusPassport;
    }

    public void setCusPassport(String cusPassport) {
        this.cusPassport = cusPassport;
    }

    public String getCusFirstname() {
        return cusFirstname;
    }

    public void setCusFirstname(String cusFirstname) {
        this.cusFirstname = cusFirstname;
    }

    public String getCusLastname() {
        return cusLastname;
    }

    public void setCusLastname(String cusLastname) {
        this.cusLastname = cusLastname;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusNation() {
        return cusNation;
    }

    public void setCusNation(String cusNation) {
        this.cusNation = cusNation;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public Integer getCusDistrict() {
        return cusDistrict;
    }

    public void setCusDistrict(Integer cusDistrict) {
        this.cusDistrict = cusDistrict;
    }

    public Integer getCusAmphoe() {
        return cusAmphoe;
    }

    public void setCusAmphoe(Integer cusAmphoe) {
        this.cusAmphoe = cusAmphoe;
    }

    public Integer getCusProvince() {
        return cusProvince;
    }

    public void setCusProvince(Integer cusProvince) {
        this.cusProvince = cusProvince;
    }

    public Integer getCusZipcode() {
        return cusZipcode;
    }

    public void setCusZipcode(Integer cusZipcode) {
        this.cusZipcode = cusZipcode;
    }

    public Integer getCusDesc() {
        return cusDesc;
    }

    public void setCusDesc(Integer cusDesc) {
        this.cusDesc = cusDesc;
    }

    public String getCusImage() {
        return cusImage;
    }

    public void setCusImage(String cusImage) {
        this.cusImage = cusImage;
    }

    public Integer getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(Integer cusStatus) {
        this.cusStatus = cusStatus;
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
