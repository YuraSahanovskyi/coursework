package com.example.coursework.services;

import com.example.coursework.model.User;

public interface UserService {
    User getById(Long id);
    User getByName(String username);
    void add(User user);
    void edit(Long id, String username, String password);
    void delete(Long id);
    boolean isExist(User user);
    boolean isExist(Long id);
    boolean isExist(String username);
}
