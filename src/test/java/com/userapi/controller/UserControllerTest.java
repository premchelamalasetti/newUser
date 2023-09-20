package com.userapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userapi.model.User;
import com.userapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem@gmail.com");
        user.setMobileNumber("4234241231");
        user.setDate(new Date());
        //userService.createUser(user);

        when(userService.createUser(any(User.class))).thenReturn(user);
        ResultActions resultActions = mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
        resultActions.andDo(print()).andExpect(status().isCreated())
                //.andExpect(jsonPath("$.id",is(user.getId())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.gmail", is(user.getGmail())))
                .andExpect(jsonPath("$.mobileNumber", is(user.getMobileNumber())));
    }

    @Test
    void getAllUser() throws Exception {
        User user = new User();
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem@gmail.com");
        user.setMobileNumber("4234241231");

        List<User> list = new ArrayList<>();
        list.add(user);
        when(userService.allUsers()).thenReturn(list);
        this.mockMvc.
                perform(get("/api/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(list)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void getUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem@gmail.com");
        user.setMobileNumber("4234241231");

        when(userService.userById(anyLong())).thenReturn(user);
        ResultActions resultActions = mockMvc.perform(get("/api/users/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.gmail", is(user.getGmail())))
                .andExpect(jsonPath("$.mobileNumber", is(user.getMobileNumber())));
        System.out.println("><><><" + resultActions.toString() + "><><>");
    }

    @Test
    void deleteUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem@gmail.com");
        user.setMobileNumber("4234241231");

        doNothing().doThrow(new RuntimeException()).when(userService).deleteUserById(anyLong());
        this.mockMvc.perform(delete("/api/users/{id}", 1L)).andExpect(status().isOk());
    }

    @Test
    void updateUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("prem");
        user.setLastName("kumar");
        user.setGmail("prem@gmail.com");
        user.setMobileNumber("4234241231");
        userService.createUser(user);

        user.setMobileNumber("8125756777");
        when(userService.updateUserById(any(User.class))).thenReturn(user);
        this.mockMvc.perform(put("/api/users/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.gmail", is(user.getGmail())))
                .andExpect(jsonPath("$.mobileNumber", is(user.getMobileNumber())));
    }
}