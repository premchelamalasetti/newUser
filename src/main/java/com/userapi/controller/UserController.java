package com.userapi.controller;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import com.userapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User user(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }
    @GetMapping("/allUser?page=1&size=2")
    public Page<User> allUsers(@RequestParam int page,@RequestParam int size){
        return userService.getAllUsers(page,size);
    }

    @GetMapping("/userById/{id}")
    public Optional<User> getUserById(@PathVariable Long id) throws Exception {
        return userService.userById(id);
        //return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping ("/updateUserById/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
         return userService.updateUserById(user);
    }
}
