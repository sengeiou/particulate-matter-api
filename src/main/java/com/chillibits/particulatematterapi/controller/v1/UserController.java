/*
 * Copyright © Marc Auberer 2019 - 2020. All rights reserved
 */

package com.chillibits.particulatematterapi.controller.v1;

import com.chillibits.particulatematterapi.exception.ErrorCodeUtils;
import com.chillibits.particulatematterapi.exception.UserDataException;
import com.chillibits.particulatematterapi.model.db.main.User;
import com.chillibits.particulatematterapi.model.dto.UserDto;
import com.chillibits.particulatematterapi.repository.LinkRepository;
import com.chillibits.particulatematterapi.repository.SensorRepository;
import com.chillibits.particulatematterapi.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "User REST Endpoint", tags = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all users, registered in the database", hidden = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns details for one specific user")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return convertToDto(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adds an user to the database")
    public User addUser(@RequestBody User user) throws UserDataException {
        // Validity checks
        if(userRepository.existsById(user.getId())) return null;
        validateUserObject(user);
        // Add additional information to user object
        long currentTimestamp = System.currentTimeMillis();
        user.setCreationTimestamp(currentTimestamp);
        user.setLastEditTimestamp(currentTimestamp);
        user.setStatus(User.EMAIL_CONFIRMATION_PENDING);
        user.setRole(User.USER);
        return userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates an existing user")
    public Integer updateUser(@RequestBody User user) throws UserDataException {
        validateUserObject(user);
        return userRepository.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole(), user.getStatus());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/user/{id}")
    @ApiOperation(value = "Deletes an user from the database")
    public void deleteUser(@PathVariable("id") int id) {
        userRepository.deleteById(id);
    }

    // ---------------------------------------------- Utility functions ------------------------------------------------

    private void validateUserObject(User user) throws UserDataException {
        if(user.getEmail().isBlank() || user.getPassword().isBlank()) throw new UserDataException(ErrorCodeUtils.INVALID_USER_DATA);
    }

    private UserDto convertToDto(User sensor) {
        return mapper.map(sensor, UserDto.class);
    }
}