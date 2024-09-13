package com.example.demo.DTO;
import com.example.demo.model.Taxi;

import java.io.Serializable;
import java.util.Date;

public class TrajectoriesDTO implements Serializable {
    private Integer id;
    private Integer taxiId;
    private String plate;
    private Date date;
    private Double latitude;
    private Double longitude;

    public String getPlate() {
        return plate;
    }

    public TrajectoriesDTO(Integer id, Integer taxiId, String plate, Date date, Double latitude, Double longitude) {
        this.id = id;
        this.taxiId = taxiId;
        this.plate = plate;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public TrajectoriesDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Integer taxiId) {
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
}
