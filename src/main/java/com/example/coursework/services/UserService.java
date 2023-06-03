package com.example.coursework.services;

import com.example.coursework.model.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    Iterable<User> getAll();

    User getById(Long id);

    User getByName(String username);

    User getByAuth(Authentication authentication);

    void save(User user);

    void edit(User user, String username, String password);

    void delete(Long id);

    boolean isExist(User user);

    boolean isExist(Long id);

    boolean isExist(String username);
}
