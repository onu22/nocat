package com.nocat.service;

import javax.persistence.*;

@Entity
public class NocatUser {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "latLong")
    private String latLong;

    protected NocatUser() {}

    public NocatUser(String deviceId, String userName, String latlong) {
        this.deviceId = deviceId;
        this.userName = userName;
        this.latLong = latlong;
    }

    public Long getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLatLong() {
        return latLong;
    }
    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

}
