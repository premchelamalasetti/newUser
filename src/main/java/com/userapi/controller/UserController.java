package com.userapi.controller;

import com.userapi.exceptions.UserAlreadyExistsException;
import com.userapi.model.User;
import com.userapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User user(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.allUsers();
    }

    @GetMapping("/allUser?page={page}&size={size}")
    public Page<User> allUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        System.out.println("EUEHJERW====");
        return userService.userById(id);
        //return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User deleted";
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserById(user);
    }
}
