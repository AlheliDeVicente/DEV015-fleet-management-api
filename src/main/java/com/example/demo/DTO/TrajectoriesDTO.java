package com.example.demo.DTO;
import com.example.demo.model.Taxi;

import java.io.Serializable;
import java.util.Date;

public class TrajectoriesDTO implements Serializable {
    private Integer id;
    private Taxi taxiId;
    private Date date;
    private Double latitude;
    private Double longitude;

    public TrajectoriesDTO(Integer id, Taxi taxiId, Date date, Double latitude, Double longitude) {
        this.id = id;
        this.taxiId = taxiId;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public TrajectoriesDTO(Integer id, String plate, Integer id1, Date date, Double latitude, Double longitude) {
    }

    public TrajectoriesDTO() {

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setPlate(String plate) {

    }

    public void setTaxiId(Integer id) {
    }
}
