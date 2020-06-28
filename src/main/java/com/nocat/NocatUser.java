package com.nocat;

import com.nocat.quadtree.INocatUser;
import javax.persistence.*;

@Entity
public class NocatUser implements INocatUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    protected NocatUser() {}

    public NocatUser(long id, double latitude, double longitude, String userName, String deviceId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userName = userName;
        this.deviceId = deviceId;
    }


    public long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public String getDeviceId() {
        return deviceId;
    }
    public String getUserName() {
        return userName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
