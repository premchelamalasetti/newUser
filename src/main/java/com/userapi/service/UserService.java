package com.userapi.service;

import com.userapi.exceptions.EmpytDataFoundException;
import com.userapi.exceptions.NoDataUpdatedException;
import com.userapi.exceptions.NoSufficientDataException;
import com.userapi.exceptions.UserNotFoundException;
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
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        if (user.getFirstName() == null || user.getFirstName() == "" || user.getLastName() == null || user.getLastName() == "" || user.getGmail() == null || user.getGmail() == "" || user.getMobileNumber() == null|| user.getMobileNumber() == "" ) {
            throw new NoSufficientDataException();
        }
        return userRepository.save(user);
    }

    public List<User> allUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new EmpytDataFoundException();
        }
        return userRepository.findAll();
    }

    public Page<User> getAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return userRepository.findAll(pageable);
    }

    public User userById(Long id) {

        Optional<User> userObj = userRepository.findById(id);
        if (userObj.isEmpty()) {
            throw new UserNotFoundException();
        }
        return userObj.get();
    }

    public User updateUserById(User user) {
        User user1 = userRepository.save(user);

        if (user.getId()== user1.getId()||user.getFirstName() == user1.getFirstName() || user.getLastName() == user1.getLastName() || user.getGmail() == user1.getGmail() || user.getMobileNumber() == user1.getMobileNumber()) {
            throw new NoDataUpdatedException();
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

