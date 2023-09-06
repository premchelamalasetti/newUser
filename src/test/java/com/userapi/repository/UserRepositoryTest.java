package com.userapi.repository;

import com.userapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createEmployeeTest() {
        User user = new User();
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");

        // Saving user and verifying
        User newUser = userRepository.save(user);
        assertNotNull(newUser);
        assertThat(newUser.getFirstName()).isEqualTo("prem");
        //assertEquals("A", "B", "Did not match");
        assertThat(newUser.getLastName()).isEqualTo("Kumar");
        assertThat(newUser.getGmail()).isEqualTo("prem.ch@gmail.com");
        assertThat(newUser.getMobileNumber()).isEqualTo("4324242411");
    }

    @Test
    void allUser() {
        User user = new User();
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");

        List<User> list = userRepository.findAll();
        assertEquals(1, 1);
    }

    @Test
    void deleteUserById() {

        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        userRepository.deleteById(1L);
        List<User> list = userRepository.findAll();
        System.out.println("-------" + list.size());
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void updateUserById() {

        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        user.setGmail("krishna.kti@gmail.com");
        User updatingUser = userRepository.save(user);
        assertNotNull(updatingUser);
        assertEquals(user.getFirstName(), "prem");
        assertEquals(user.getLastName(), "Kumar");
        assertEquals(user.getMobileNumber(), "4324242411");
        assertEquals(user.getGmail(), "krishna.kti@gmail.com");
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        User existingUser = userRepository.findById(user.getId()).get();
        assertNotNull(existingUser);
        assertThat(existingUser.getId()).isEqualTo(1L);
        System.out.println("------" + existingUser.getFirstName());
        assertEquals(existingUser.getFirstName(), "prem");
        assertEquals(existingUser.getLastName(), "Kumar");
        assertEquals(existingUser.getMobileNumber(), "4324242411");
        assertEquals(existingUser.getGmail(), "prem.ch@gmail.com");
    }
}