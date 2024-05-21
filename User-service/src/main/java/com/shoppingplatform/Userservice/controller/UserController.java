package com.shoppingplatform.Userservice.controller;

import com.shoppingplatform.Userservice.model.UserModel;
import com.shoppingplatform.Userservice.model.UserRequestDTO;
import com.shoppingplatform.Userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserWithId/{id}")
    public UserModel getUserById(@PathVariable String id) { return userService.getUserById(id); }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequestDTO userDTO){
        userService.createUser(userDTO);
    }

    @PostMapping("/updateUser/{id}")
    public UserModel updateUser(@PathVariable String  id, @RequestBody UserModel userDetails){
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

    @GetMapping("/getUserWithUsername/{username}")
    public UserModel getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

}
