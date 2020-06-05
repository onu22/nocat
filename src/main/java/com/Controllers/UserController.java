package com.Controllers;

import com.model.NotcatUser;
import com.model.NotcatUserRepository;
import com.quadtree.QuadTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private NotcatUserRepository userRepository;
    @Autowired
    private QuadTree tree;

    @GetMapping("/all")
    public @ResponseBody  Iterable<NotcatUser> getuser() {

        Iterable<NotcatUser> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/get/{deviceid}")
    public @ResponseBody
    NotcatUser getuser(@PathVariable String deviceid) {
        NotcatUser user = userRepository.findByDeviceId(deviceid);

        return  user;
    }

    @PostMapping("/pos")
    public @ResponseBody String updatePos(@RequestBody Integer id,@PathVariable String latlong) {

        tree.addNeighbour(111, 43.650715, -79.377161); //22 Adelaide St E, Toronto, ON M5C 3G6, Canada
        tree.addNeighbour(211, 43.645250, -79.403168); //125-135 Bathurst St, Toronto, ON M5V 2R2, Canada
        tree.addNeighbour(32, 43.817558, -79.450139); //Atkinson Ave, Thornhill, ON L4J 6Y6, Canada
        tree.addNeighbour(44, 7.345851, 3.934485); // Ibadan
        tree.addNeighbour(52, 6.550427, 3.271185); // Ikotun
        tree.addNeighbour(63, 9.077587, 7.467276); // wuse 2
        tree.addNeighbour(74, 9.087715, 7.495214); // Maitama
        tree.addNeighbour(84, -15.830051, -47.947291); // Brasilia, Brazil
        tree.addNeighbour(95, -5.833394, -35.217055); // Candelária, Natal - State of Rio Grande do Norte, 59065-190, Brazil
        tree.addNeighbour(991, 39.939585, -75.179891); // 1034-1098 S 22nd St, Philadelphia, PA 19146, USA

        return "updated";
    }
}
