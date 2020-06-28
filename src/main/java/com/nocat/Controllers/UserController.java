package com.nocat.Controllers;

import com.nocat.service.NocatUserService;
import com.nocat.NocatUser;
import com.nocat.quadtree.QuadTree;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(path="/api/users")
public class UserController {

    @Autowired
    NocatUserService userService;
    @Autowired
    private QuadTree tree;

    @RequestMapping(method=RequestMethod.PUT, path = "/updatelocation")
    public ResponseEntity<NocatUser> updateLocation(@RequestBody NocatUser data) {
        try {

            userService.updateLocation(data);
            double latitude = data.getLatitude();
            double longitude = data.getLongitude();
            tree.addNeighbour(data.getId(),latitude, longitude, data.getUserName(),data.getDeviceId());
            return new ResponseEntity<NocatUser>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<NocatUser>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method=RequestMethod.GET, path = "/updatetree")
    public @ResponseBody void addUsersToTree() { //temp implementation
        try {
            Iterable<NocatUser>  users = userService.getUsers();
            for(NocatUser nocatUser : users){
                tree.removeNeighbour(nocatUser.getId());
            }
            for(NocatUser nocatUser : users){
                tree.addNeighbour(nocatUser.getId(),nocatUser.getLatitude(), nocatUser.getLongitude(),
                        nocatUser.getUserName(),nocatUser.getDeviceId());
            }
        }
        catch (Exception ex){

        }
    }

    @RequestMapping(method=RequestMethod.GET, path = "/{userId}")
    public @ResponseBody  NocatUser getuser(long userId) {
        try {
            NocatUser  user = userService.findById(userId);
            return  user;
        }
        catch (Exception ex){
            return  null;
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody  Iterable<NocatUser> getusers() {
        try {
            Iterable<NocatUser>  users = userService.getUsers();
            return  users;
        }
        catch (Exception ex){
            return  null;
        }
    }

//    @PostMapping("/pos")
//    public @ResponseBody String updatePos(@RequestBody Integer id, @PathVariable String latlong) {
//
//        tree.addNeighbour(111, 43.650715, -79.377161); //22 Adelaide St E, Toronto, ON M5C 3G6, Canada
//        tree.addNeighbour(211, 43.645250, -79.403168); //125-135 Bathurst St, Toronto, ON M5V 2R2, Canada
//        tree.addNeighbour(32, 43.817558, -79.450139); //Atkinson Ave, Thornhill, ON L4J 6Y6, Canada
//        tree.addNeighbour(44, 7.345851, 3.934485); // Ibadan
//        tree.addNeighbour(52, 6.550427, 3.271185); // Ikotun
//        tree.addNeighbour(63, 9.077587, 7.467276); // wuse 2
//        tree.addNeighbour(74, 9.087715, 7.495214); // Maitama
//        tree.addNeighbour(84, -15.830051, -47.947291); // Brasilia, Brazil
//        tree.addNeighbour(95, -5.833394, -35.217055); // Candelária, Natal - State of Rio Grande do Norte, 59065-190, Brazil
//        tree.addNeighbour(991, 39.939585, -75.179891); // 1034-1098 S 22nd St, Philadelphia, PA 19146, USA
//
//        return "updated";
//    }

}
