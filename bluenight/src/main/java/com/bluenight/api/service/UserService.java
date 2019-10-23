package com.bluenight.api.service;

import com.bluenight.api.model.User;
import com.bluenight.api.repository.UserRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

@Component
public class UserService {

  @Resource
  UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<User> findAllUser() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public User findUser(String userId) {
    return userRepository.findById(userId).orElse(null);
  }

  @Transactional
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public void deleteUser(String userId) {
    userRepository.deleteById(userId);
  }
}
