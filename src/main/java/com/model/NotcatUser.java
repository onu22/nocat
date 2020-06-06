package com.model;

import org.springframework.data.annotation.*;

//@Table("nocatuser")
public class NotcatUser {

    @Id
    private Integer id;
    private String deviceId;
    private String username;
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
