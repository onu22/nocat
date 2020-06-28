package com.nocat.Controllers;
import com.nocat.NocatUser;
import com.nocat.quadtree.INocatUser;
import com.nocat.quadtree.QuadTree;
import com.nocat.quadtree.QuadTreeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping(path="/api/neigh")
public class NeighboursController {

    @Autowired
    private QuadTree tree;

    @RequestMapping(method=RequestMethod.PUT, path = "/getall")
    public @ResponseBody Iterable<INocatUser> nearbys(@RequestBody NocatUser data) {

        try  {
            double latitude = data.getLatitude(); //float) 9.077587; //(yz / mScaleDrawToLatitude) - 90;
            double longitude = data.getLongitude();  //(xz / mScaleDrawToLongitude) - 180;
            Set<INocatUser> mSelectedINocatUsers = new HashSet<>();
            mSelectedINocatUsers = tree.findNeighbours(latitude, longitude, QuadTreeConstants.QUADTREE_LAST_NODE_SIZE_IN_KM);

            return mSelectedINocatUsers;
        }
        catch(Exception e) {
            String exce = e.toString();
        }
        return new HashSet<>();
    }

}
