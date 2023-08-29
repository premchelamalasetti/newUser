package com.userapi.service;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private  UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setDate(new Date());
        return userRepository.save(user);
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public Optional<User> userById(Long id)
    {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() ->new NullPointerException("User Not found")));
    }

    public User updateUserById(User user,Long id){
        User userDetials = userRepository.findById(id).orElseThrow(() -> new NullPointerException("User Not found" + id));
        userDetials.setFirstName(user.getFirstName());
        userDetials.setLastName(user.getLastName());
        userDetials.setMobileNumber(user.getMobileNumber());
        userDetials.setGmail(user.getGmail());
        return userRepository.save(userDetials);
    }

    public void  deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}

