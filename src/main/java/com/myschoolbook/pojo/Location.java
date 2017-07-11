package com.myschoolbook.pojo;

import javax.persistence.*;

/**
 * Created by Krystian on 2017-05-31.
 */

@Embeddable
public class Location {

    private String latitude;
    @Embedded private Street street;
    private String longitude;

    public Location(){

    }

    public Location( String latitude, Street street, String longitude) {
        this.latitude = latitude;
        this.street = street;
        this.longitude = longitude;
    }



    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
