package com.userapi.service;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
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

    public Page<User> getAllUsers(int pageNo, int pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return userRepository.findAll( pageable);
    }

    public Optional<User> userById(Long id)
    {
        return userRepository.findById(id);
    }

    public User updateUserById(User user){
        return userRepository.save(user);
    }

    public void  deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}

