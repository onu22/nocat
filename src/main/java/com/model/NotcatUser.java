package com.model;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

//@Table("nocatuser")
public class NotcatUser {

    @Id
    private Integer id;
    private String deviceId;
    private String userName;
    private String latLong;

    public Integer getId() {
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

}
