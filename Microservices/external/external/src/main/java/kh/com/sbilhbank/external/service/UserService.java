package kh.com.sbilhbank.external.service;

import kh.com.sbilhbank.external.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
}
