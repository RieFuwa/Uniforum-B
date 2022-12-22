package com.uniforum.controller;
import com.uniforum.dto.UserDto;
import com.uniforum.model.User;
import com.uniforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/add") //USER KAYIT ETME
        public ResponseEntity<Void> createUser(@RequestBody User newUser) {
                User user = userService.createUser(newUser);
                if(user != null)
                        return new ResponseEntity<>(HttpStatus.CREATED);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @GetMapping("/getAll")// KAYITLI USERLARIN HEPSINI GETIR
        public List<UserDto> getAllUser(){
                return userService.getAllUser().stream().map(u -> new UserDto(u)).toList();
        }

        @GetMapping("/{userId}")// ID SINE GORE USER CAGIRMA
        public UserDto getUserById(@PathVariable("userId")String userId){
                User user= userService.getUserById(userId);
                return new UserDto(user);
        }

        @DeleteMapping("/{userId}") //USER ID SINE GORE SILME
        public String deleteUserById(@PathVariable("userId") String userId){
                return userService.deleteUserById(userId);
        }

        @PutMapping("/{userId}") // USER ID SINE GORE GUNCELLEME
        public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody User newUser){
                User user = userService.updateUserById(userId,newUser);
                if(user != null)
                        return new ResponseEntity<>(HttpStatus.OK);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //USER ACTIVITY
}
