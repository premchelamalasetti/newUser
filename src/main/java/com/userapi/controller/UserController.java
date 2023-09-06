package com.userapi.controller;

import com.userapi.model.User;
import com.userapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User user(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }
    @GetMapping("/allUser?page={page}&size={size}")
    public Page<User> allUsers(@RequestParam int page,@RequestParam int size){
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        return userService.userById(id);
        //return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
       userService.deleteUserById(id);
       return "Use deleted";
    }

    @PutMapping ("/update/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
         return userService.updateUserById(user);
    }
}
