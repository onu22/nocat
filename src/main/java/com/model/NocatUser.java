package com.model;

import org.springframework.data.annotation.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class NocatUser {

    @Id
    private Integer id;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "username")
    private String username;

    @Column(name = "latLong")
    private String latLong;

    public Integer getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }
    public String getUsername() {
        return username;
    }
    public String getLatLong() {
        return latLong;
    }

}
