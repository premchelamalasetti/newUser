package com.userapi.integration;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    private String baseUrl="http://localhost";
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;
    private static RestTemplate restTemplate;

    @BeforeAll
    public static void setUp(){
       restTemplate = new RestTemplate();
    }
    @AfterEach
    public  void delete(){
        userRepository.deleteAll();
    }
    @Test
    void createUser(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("d.c@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        User newUser=restTemplate.postForObject(baseUrl+":"+port+"/user/createUser",user,User.class);
        assertThat(user.getFirstName()).isEqualTo(newUser.getFirstName());
        assertThat(user.getLastName()).isEqualTo(newUser.getLastName());
        assertEquals(newUser.getFirstName(),"prem");
        assertThat(user.getGmail()).isEqualTo("d.c@gmail.com");
        assertThat(user.getMobileNumber()).isEqualTo(newUser.getMobileNumber());
        System.out.println("<><><"+newUser+"<><><");
    }

    @Test
    void getAllUser(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        List<User> list=restTemplate.getForObject(baseUrl+":"+port+"/user/allUsers",List.class);
        assertThat(list.size()).isEqualTo(1);
    }
    @Test
    void updateUserById(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        user.setMobileNumber("8125756777");
        restTemplate.put(baseUrl+":"+port+"/user/updateUserById/{id}",user,user.getId());
        User updatedUser=restTemplate.getForObject(baseUrl+":"+port+"/user/updateUserById/{id}"+user.getId(),User.class);
        assertEquals(updatedUser.getFirstName(),"prem");
        assertEquals(updatedUser.getLastName(),"kumar");
        assertEquals(updatedUser.getGmail(),"prem.ch@gmail.com");
        assertEquals(updatedUser.getMobileNumber(),"8125756777");
    }

    @Test
    void deleteUserById(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        restTemplate.delete(baseUrl+":"+port+"/user/deleteUserById/{id}"+user.getId(),User.class);
        int count=userRepository.findAll().size();
        assertEquals(0,count);
    }

    @Test
    void getUserById(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        User existingUser=restTemplate.getForObject(baseUrl+":"+port+"/user/userById/"+user.getId(),User.class);
        assertNotNull(existingUser);
        assertThat(user.getMobileNumber()).isEqualTo(existingUser.getMobileNumber());
    }
}
