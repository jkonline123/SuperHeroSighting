/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.dtos;

import java.util.Objects;

/**
 *
 * @author triplexlj
 */
public class Location {
    
    
    private int LocationID;
    private String LocationName;
    private String LocationAddress;
    private Float Latitude;
    private Float Longitude;

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int LocationID) {
        this.LocationID = LocationID;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String LocationAddress) {
        this.LocationAddress = LocationAddress;
    }

    public Float getLatitude() {
        return Latitude;
    }

    public void setLatitude(Float Latitude) {
        this.Latitude = Latitude;
    }

    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float Longitude) {
        this.Longitude = Longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.LocationID;
        hash = 11 * hash + Objects.hashCode(this.LocationName);
        hash = 11 * hash + Objects.hashCode(this.LocationAddress);
        hash = 11 * hash + Objects.hashCode(this.Latitude);
        hash = 11 * hash + Objects.hashCode(this.Longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.LocationID != other.LocationID) {
            return false;
        }
        if (!Objects.equals(this.LocationName, other.LocationName)) {
            return false;
        }
        if (!Objects.equals(this.LocationAddress, other.LocationAddress)) {
            return false;
        }
        if (!Objects.equals(this.Latitude, other.Latitude)) {
            return false;
        }
        if (!Objects.equals(this.Longitude, other.Longitude)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
