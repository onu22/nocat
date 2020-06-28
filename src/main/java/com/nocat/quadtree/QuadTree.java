package com.nocat.quadtree;

import com.nocat.NocatUser;
import org.springframework.stereotype.Component;

import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

@Component
//@ApplicationScope
public class QuadTree {

    public static final int TOTAL_X_DEGREES = 360; // -180 to 180 - longitude
    public static final int TOTAL_Y_DEGREES = 180; // -90 to 90   - latitude
    private static final int NORMALIZE_X = 180;
    private static final int NORMALIZE_Y = 90;

    private QuadTreeNode mRootNode;

    public QuadTree() {
        mRootNode = new QuadTreeNode(0, 0, TOTAL_Y_DEGREES, TOTAL_X_DEGREES);
    }

    public QuadTree(QuadTreeNode rootNode) {
        mRootNode = rootNode;
    }

    public synchronized void addNeighbour(long id, double latitude, double longitude,String userName, String deviceId) {
        INocatUser INocatUser =
               // new NeighbourImpl(id, normalizeLatitude(latitude), normalizeLongitude(longitude));
                new NocatUser(id, normalizeLatitude(latitude), normalizeLongitude(longitude),userName,deviceId);
        mRootNode.addNeighbour(INocatUser, QuadTreeConstants.QUADTREE_LAST_NODE_SIZE_IN_DEGREE);
    }

    public void removeNeighbour(long id) {
        mRootNode.removeNeighbour(id);
    }

    public Set<INocatUser> findNeighbours(double latitude, double longitude, double rangeInKm) {
        Set<INocatUser> INocatUserSet = new HashSet<>();
        double rangeInDegrees = QuadTreeConstants.kmToDegree(rangeInKm);

        Rectangle2D.Double areaOfInterest = getRangeAsRectangle(normalizeLatitude(latitude), normalizeLongitude(longitude), rangeInDegrees);
        mRootNode.findNeighboursWithinRectangle(INocatUserSet, areaOfInterest);
        return INocatUserSet;
    }

    public Set<Long> findNeighboursIds(double latitude, double longitude, double rangeInKm) {
        Set<INocatUser> nocatUserSet = findNeighbours(latitude, longitude, rangeInKm);
        Set<Long> neighboursIds = new HashSet<>();

        for(INocatUser nocatUser : nocatUserSet)
            neighboursIds.add(nocatUser.getId());

        return neighboursIds;
    }

    protected QuadTreeNode getRootNode() {
        return mRootNode;
    }

    private double normalizeLatitude(double latitude) {
        return latitude + NORMALIZE_Y;
    }

    private double normalizeLongitude(double longitude) {
        return longitude + NORMALIZE_X;
    }

    private Rectangle2D.Double getRangeAsRectangle(double latitude, double longitude, double range) {
        /*
           We need to centralize the point and have the range on every direction
         */
        return new Rectangle2D.Double(Math.max(longitude - range, 0),
                Math.max(latitude - range, 0),
                Math.min(range * 2, TOTAL_X_DEGREES),
                Math.min(range * 2, TOTAL_Y_DEGREES));
    }

}
