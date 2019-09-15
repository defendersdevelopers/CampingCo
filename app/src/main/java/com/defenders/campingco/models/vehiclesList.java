package com.defenders.campingco.models;

public class vehiclesList {


    String vehicleId,vname,wheelDriven,price,imageUrl;

    public vehiclesList(String vehicleId, String vname, String wheelDriven, String price, String iageUrl) {
        this.vehicleId = vehicleId;
        this.vname = vname;
        this.wheelDriven = wheelDriven;
        this.price = price;
        this.imageUrl = iageUrl;
    }

    public vehiclesList() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getWheelDriven() {
        return wheelDriven;
    }

    public void setWheelDriven(String wheelDriven) {
        this.wheelDriven = wheelDriven;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
