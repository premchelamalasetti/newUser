package com.userapi.service;

import com.userapi.exceptions.EmpytDataFoundException;
import com.userapi.exceptions.NoDataUpdatedException;
import com.userapi.exceptions.NoSufficientDataException;
import com.userapi.exceptions.UserNotFoundException;
import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user).thenThrow(NoSufficientDataException.class);
        User newUser = userService.createUser(user);
        assertThat(newUser.getGmail()).isNotEqualTo(null);
        assertEquals(newUser.getLastName(),"Kumar");
        assertThrows(NoSufficientDataException.class, () ->userService.createUser(user),"Different Exception thrown");
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

        List<User> list =new ArrayList<>();
        list.add(user);
        when(userRepository.findAll()).thenReturn(list);
        List<User> users=userService.allUsers();
        assertEquals(1,1);
        assertNotNull(users);
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void userById(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user)).thenThrow(UserNotFoundException.class);
        User existingUser = userService.userById(1L);
        assertEquals(existingUser.getId(),1L);
        assertEquals(existingUser.getFirstName(),"prem");
        assertEquals(existingUser.getLastName(),"Kumar");
        assertEquals(existingUser.getGmail(),"prem.ch@gmail.com");
        assertEquals(existingUser.getMobileNumber(),"4324242411");
        assertThrows(UserNotFoundException.class, () -> userService.userById(2L),"Different Exception Thrown");
    }
    @Test
    void deleteUserById(){
        User user = new User();
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
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("Kumar");
        user.setGmail("prem.ch@gmail.com");
        user.setMobileNumber("4324242411");
        userRepository.save(user);
        User user1=new User();

        when(userRepository.save(any(User.class))).thenReturn(user).thenThrow(NoDataUpdatedException.class);
        user.setGmail("prem@gmail.com");
        User newUser = userService.createUser(user);
        assertEquals("prem@gmail.com",newUser.getGmail());
        assertThrows(NoDataUpdatedException.class,() -> userService.updateUserById(newUser),"Different Exception thrown");
    }
}