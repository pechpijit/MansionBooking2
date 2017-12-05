package dev.pechy.mansionbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelDetailLocation {
    @SerializedName("location")
    @Expose
    private ModelLocation location;
    @SerializedName("build")
    @Expose
    private ArrayList<ModelBuild> build;
    @SerializedName("public")
    @Expose
    private ModelPublic publicut;
    @SerializedName("price")
    @Expose
    private ModelPrice price;

    public ModelLocation getLocation() {
        return location;
    }

    public void setLocation(ModelLocation location) {
        this.location = location;
    }

    public ArrayList<ModelBuild> getBuild() {
        return build;
    }

    public void setBuild(ArrayList<ModelBuild> build) {
        this.build = build;
    }

    public ModelPublic getPublicut() {
        return publicut;
    }

    public void setPublicut(ModelPublic publicut) {
        this.publicut = publicut;
    }

    public ModelPrice getPrice() {
        return price;
    }

    public void setPrice(ModelPrice price) {
        this.price = price;
    }
}
