package com.somnus.firebasedemoproject.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth/auth-user")
    public UserDto authenticateUser(@RequestHeader String firebaseToken, @RequestBody UserDto userDto){
        return userService.authenticateUser(firebaseToken, userDto);
    }
}
