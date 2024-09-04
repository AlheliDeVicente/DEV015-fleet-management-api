package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "trajectories")
public class Trajectories {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "taxi_id")
    private Taxi taxiId;
    @Column(name = "date")
    private Date date;
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;

    public Trajectories() {
    }

    public Trajectories(Integer id, Taxi taxiId, Date date, Float latitude, Float longitude) {
        this.id = id;
        this.taxiId = taxiId;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Taxi getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Taxi taxiId) {
        this.taxiId = taxiId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
