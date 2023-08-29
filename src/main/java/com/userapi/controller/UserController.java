package com.userapi.controller;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import com.userapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    @PostMapping("/createUser")
    public User user(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }
    @GetMapping("/allUser?page=1&size=2")
    public Page<User> allUsers(@RequestParam int page,@RequestParam int size){
        Pageable pr= PageRequest.of(1,2);
        System.out.println(pr);
        return userRepository.findAll(pr);
    }

    @GetMapping("userById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        User user = userService.userById(id).orElseThrow(() -> new NullPointerException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/deleteUserById{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PatchMapping("/updateUserById{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
         return userService.updateUserById(user,id);
    }
}
