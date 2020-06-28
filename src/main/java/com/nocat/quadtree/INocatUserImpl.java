package com.nocat.quadtree;

public class INocatUserImpl implements INocatUser {

    private final long mId;
    private final double mLatitude;
    private final double mLongitude;

    public INocatUserImpl(long id, double latitude, double longitude) {
        mId = id;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public double getLatitude() {
        return mLatitude;
    }

    @Override
    public double getLongitude() {
        return mLongitude;
    }
}
