package com.example.bookpro.Classes;

import android.net.Uri;

public class DataForBooking {

    String locationName;
    Uri image;
    String description;

    public DataForBooking() {
        this.locationName = locationName;
        this.image = image;
        this.description = description;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationName() {
        return locationName;
    }

    public Uri getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

}
