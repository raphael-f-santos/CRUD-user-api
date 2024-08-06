package com.crudapplication.user_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudapplication.user_api.dto.UserDTO;
import com.crudapplication.user_api.dto.mapper.UserMapper;
import com.crudapplication.user_api.model.User;
import com.crudapplication.user_api.services.UserService;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user-interface")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {

        UserDTO dto = userService.getUserById(id).orElse(null);
        return ResponseEntity.status(HttpStatus.FOUND).body(dto);
    } 

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return ResponseEntity.ok("User id{" + id + "} deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody User user) {
        
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(user));
    }
}
