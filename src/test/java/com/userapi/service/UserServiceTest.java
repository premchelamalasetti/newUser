package com.userapi.service;

import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Test
    void createUser(){
        User user=new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User newUser=userService.createUser(user);
        assertThat(newUser.getGmail()).isNotEqualTo(null);
        assertEquals(newUser.getLastName(),"Kumar");
    }
    @Test
    void getAllUser(){
        User user=new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        List<User> list=userRepository.findAll();
        list.add(user);
        when(userRepository.findAll()).thenReturn(list);
        List<User> users=userService.allUsers();
        assertEquals(1,1);
        assertNotNull(users);
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void userById(){
        User user=new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<User> existingUser=userService.userById(1L);
        assertEquals(existingUser.get().getId(),1L);
        assertEquals(existingUser.get().getFirstName(),"prem");
        assertEquals(existingUser.get().getLastName(),"Kumar");
        assertEquals(existingUser.get().getGmail(),"prem.ch@gmail.com");
        assertEquals(existingUser.get().getMobileNumber(),"4324242411");
    }
    @Test
    void deleteUserById(){
        User user=new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        userService.deleteUserById(1L);
        verify(userRepository,times(1)).deleteById(1L);
        assertNotNull(user);
    }

    @Test
    void updateUserById(){
        User user=new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        when(userRepository.save(any(User.class))).thenReturn(user);
        user.setGmail("prem@gmail.com");
        User newUser=userService.createUser(user);
        assertEquals("prem@gmail.com",user.getGmail());
    }
}