/*
 * Copyright © 2019 Marc Auberer. All rights reserved.
 */

package com.mrgames13.jimdo.particulatematterapp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull private long chipId;
    @NonNull private String firmwareVersion;
    @NotNull private long creationDate;
    @NotNull private String notes;
    @NotNull private long lastUpdate;
    @NotNull private long lastEdit;
    @NotNull private double latitude;
    @NotNull private double longitude;
    @NotNull private double altitude;
    @NotNull private String country;
    @NotNull private String city;
    @NotNull private String mapsUrl;
    @NotNull private double lastValueP1;
    @NotNull private double lastValueP2;

    public Sensor() {}

    public Sensor(long chipId, String firmwareVersion, long creationDate, String notes, long lastUpdate, long lastEdit, double latitude, double longitude, double altitude, String country, String city, String mapsUrl, double lastValueP1, double lastValueP2) {
        this.chipId = chipId;
        this.firmwareVersion = firmwareVersion;
        this.creationDate = creationDate;
        this.notes = notes;
        this.lastUpdate = lastUpdate;
        this.lastEdit = lastEdit;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.country = country;
        this.city = city;
        this.mapsUrl = mapsUrl;
        this.lastValueP1 = lastValueP1;
        this.lastValueP2 = lastValueP2;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }
    public void setFirmwareVersion(@NonNull String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public long getChipId() {
        return chipId;
    }
    public void setChipId(long chipId) {
        this.chipId = chipId;
    }

    public long getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getLastEdit() {
        return lastEdit;
    }
    public void setLastEdit(long lastEdit) {
        this.lastEdit = lastEdit;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getMapsUrl() {
        return mapsUrl;
    }
    public void setMapsUrl(String mapsUrl) {
        this.mapsUrl = mapsUrl;
    }

    public double getLastValueP1() {
        return lastValueP1;
    }
    public void setLastValueP1(double lastValueP1) {
        this.lastValueP1 = lastValueP1;
    }

    public double getLastValueP2() {
        return lastValueP2;
    }
    public void setLastValueP2(double lastValueP2) {
        this.lastValueP2 = lastValueP2;
    }
}
