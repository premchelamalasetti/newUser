package com.userapi.controller;

import com.userapi.model.LoginDto;
import com.userapi.model.User;
import com.userapi.repository.RoleRepository;
import com.userapi.repository.UserRepository;
import com.userapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, AuthenticationManager authenticationManager, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User user(@Valid @RequestBody User user) throws DataIntegrityViolationException {

        return  userService.createUser(user);
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getName(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User Authenticated",HttpStatus.OK);
    }
}
