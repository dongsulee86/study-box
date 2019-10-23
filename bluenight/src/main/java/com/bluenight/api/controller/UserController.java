package com.bluenight.api.controller;

import com.bluenight.api.model.User;
import com.bluenight.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/users")
  public ResponseEntity<?> getAllUser() {
    List<User> result = userService.findAllUser();
    if (result.isEmpty()) {
      return new ResponseEntity("No User", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<User>>(result, HttpStatus.OK);
  }

  @PostMapping("/user")
  public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
    User result = userService.createUser(user);
    if (result == null) {
      return new ResponseEntity("Can not create", HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<User>(result, HttpStatus.OK);
    }
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<?> getUser(@PathParam("userId") String userId) {
    User result = userService.findUser(userId);
    if (result == null) {
      return new ResponseEntity("No User", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<User>(result, HttpStatus.OK);
  }

  @DeleteMapping("/user/{userId}")
  public ResponseEntity<?> deleteUser(@PathParam("userId") String userId) {
    userService.deleteUser(userId);

    return ResponseEntity.noContent().build();
  }
}
