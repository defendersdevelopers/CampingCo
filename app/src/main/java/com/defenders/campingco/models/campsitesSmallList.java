package com.defenders.campingco.models;

public class campsitesSmallList {
    String campsiteId,title,details,imageUrl;

    public campsitesSmallList(String campsiteId, String title, String details, String imageUrl) {
        this.campsiteId = campsiteId;
        this.title = title;
        this.details = details;
        this.imageUrl = imageUrl;
    }

    public campsitesSmallList() {
    }


    public String getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(String campsiteId) {
        this.campsiteId = campsiteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
