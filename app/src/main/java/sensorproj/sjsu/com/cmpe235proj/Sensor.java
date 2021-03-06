package sensorproj.sjsu.com.cmpe235proj;

import java.io.Serializable;

/**
 * Created by Parnit on 4/28/2015.
 * Description: Sensor class to hold  sensor meta data
 */
public class Sensor implements Serializable
{

    double longitude, latitude;
    String name, description, id, userid;

    public Sensor(){

    }

    public Sensor(String name, String description,  double latitude , double longitude){
        this.name = name;
        this.description = description;
        this. longitude = longitude;
        this.latitude =latitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }
}
