package com.uniforum.controller;
import com.uniforum.dto.UserTypeDto;
import com.uniforum.model.UserType;
import com.uniforum.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userType")
@CrossOrigin
public class UserTypeController {
    @Autowired
    private UserTypeService userTypeService;

    @PostMapping("/add")
    public ResponseEntity<Void> createUserType(@RequestBody UserType newUserType) {
        UserType userType = userTypeService.createUserType(newUserType);
        if(userType != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/getAll")
    public List<UserTypeDto> getAllUserType(){
        return userTypeService.getAllUserType().stream().map(u -> new UserTypeDto(u)).toList();
    }
    @DeleteMapping("/{userTypeId}")
    public String deleteUserType(@PathVariable("userTypeId") String userTypeId){
        return userTypeService.deleteUserType(userTypeId);
    }
    @GetMapping("/{userTypeId}")
    public UserTypeDto getUserTypeById(@PathVariable("userTypeId")String userTypeId){
        UserType userType = userTypeService.getUserTypeById(userTypeId);
        return new UserTypeDto(userType);
    }
    @PutMapping("/{userTypeId}")
    public ResponseEntity<Void> updateUserTypeById(@PathVariable("userTypeId")String userTypeId, @RequestBody UserType newUserType) {
        UserType userType = userTypeService.updateUserTypeById(userTypeId, newUserType);
        if (userType != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
