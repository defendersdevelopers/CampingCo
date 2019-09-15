package com.defenders.campingco.models;

public class campsitesList {

    String title,description,imageUrl,accessibleBy,knownFor,importantToKnow,contactNo,amenities,activities,accomodation,climateTemp,note;


    public campsitesList() {
    }

    public campsitesList(String title, String description, String imageUrl, String accessibleBy, String knownFor, String importantToKnow, String contactNo, String amenities, String activities, String accomodation, String climateTemp, String note) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.accessibleBy = accessibleBy;
        this.knownFor = knownFor;
        this.importantToKnow = importantToKnow;
        this.contactNo = contactNo;
        this.amenities = amenities;
        this.activities = activities;
        this.accomodation = accomodation;
        this.climateTemp = climateTemp;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessibleBy() {
        return accessibleBy;
    }

    public void setAccessibleBy(String accessibleBy) {
        this.accessibleBy = accessibleBy;
    }

    public String getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(String knownFor) {
        this.knownFor = knownFor;
    }

    public String getImportantToKnow() {
        return importantToKnow;
    }

    public void setImportantToKnow(String importantToKnow) {
        this.importantToKnow = importantToKnow;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public String getClimateTemp() {
        return climateTemp;
    }

    public void setClimateTemp(String climateTemp) {
        this.climateTemp = climateTemp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
