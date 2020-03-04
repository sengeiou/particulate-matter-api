/*
 * Copyright © Marc Auberer 2019 - 2020. All rights reserved.
 */

package com.chillibits.particulatematterapi.controller;

import com.chillibits.particulatematterapi.model.User;
import com.chillibits.particulatematterapi.repository.AuthUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "User REST Endpoint")
public class UserController {

    @Autowired
    AuthUserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all users, registered in the database")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns details for one specific user")
    public User getUserById(@PathVariable("id") Integer id) {
        return userRepository.getOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adds an user to the database")
    public User addUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates an existing user")
    public Integer updateUser(@RequestBody User user) {
        return userRepository.updateUser(user.getId(), user.getUsername(), user.getPassword(), user.getRoles(), user.isActive());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/user/{id}")
    @ApiOperation(value = "Deletes an user from the database")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }
}