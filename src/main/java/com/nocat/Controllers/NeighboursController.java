package com.nocat.Controllers;
import com.nocat.quadtree.Neighbour;
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

    @GetMapping("/{id}")
    public @ResponseBody Iterable<Neighbour> nearbys(@PathVariable String id) {

        try  {
            float latitude = (float) 9.077587; //(yz / mScaleDrawToLatitude) - 90;
            float longitude = (float) 7.467276; //(xz / mScaleDrawToLongitude) - 180;
            Set<Neighbour> mSelectedNeighbours = new HashSet<>();
            return mSelectedNeighbours = tree.findNeighbours(latitude, longitude, QuadTreeConstants.QUADTREE_LAST_NODE_SIZE_IN_KM);
        }
        catch(Exception e) {
            String exce = e.toString();
        }
        return new HashSet<>();
    }

}
