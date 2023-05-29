package com.example.SoulNeo.controller;
import com.example.SoulNeo.domain.Interest;
import com.example.SoulNeo.domain.Match;
import com.example.SoulNeo.domain.User;
import com.example.SoulNeo.exception.userNotFound;
import com.example.SoulNeo.service.ISoulUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/neo/v1")
public class UserController {
//http://localhost:2222/api/neo/v1/saveUser
    private ISoulUser iSoulUser;
    @Autowired
    public UserController(ISoulUser iSoulUser) {
        this.iSoulUser = iSoulUser;
    }

    @GetMapping("/allUsers")
    public Collection<User>  getAll(){
       return iSoulUser.getAll();
    }


    @PostMapping("/saveUser")
    public ResponseEntity registerUser(@RequestBody User user){
        return new ResponseEntity(iSoulUser.saveUser(user), HttpStatus.CREATED);
}


//http://localhost:2222/api/neo/v1/city/
    @GetMapping("/city/{city}")
    public ResponseEntity<List<User>> getUsersByCity(@PathVariable String city) {
        try {
            List<User> users = iSoulUser.getUsersByCity(city);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/like/{userEmail1}/{userEmail2}")
    public ResponseEntity<String> createLikeRelationship(@PathVariable("userEmail1") String userEmail1,
                                                         @PathVariable("userEmail2") String userEmail2) {
        iSoulUser.createLikeRelationship(userEmail1, userEmail2);
        return ResponseEntity.ok("Like relationship created successfully.");
}

    //http://localhost:2222/api/neo/v1/male
    @GetMapping("/genderr/{gender}")
    public List<User> findByGenderNot(@PathVariable String gender) {
    return iSoulUser.findByGenderNot(gender);
    }


    @PostMapping("/unlike/{userFromId}/{userToId}")
    public void unlikeUser(@PathVariable String userFromId, @PathVariable String userToId) {
        User userFrom = iSoulUser.getUserById(userFromId);
        User userTo = iSoulUser.getUserById(userToId);
        iSoulUser.unlikeUser(userFrom, userTo);
    }
//    @GetMapping("/{userEmail}")
//    public ResponseEntity<User> findMatch(@PathVariable String userEmail) {
//        User match = iSoulUser.findMatch(userEmail);
//        if (match != null) {
//            return ResponseEntity.ok(match);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/{userEmail}")
    public ResponseEntity<List<User>> findMatches(@PathVariable String userEmail) {
        List<User> matches = iSoulUser.findMatches(userEmail);
        if (!matches.isEmpty()) {
            return ResponseEntity.ok(matches);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
