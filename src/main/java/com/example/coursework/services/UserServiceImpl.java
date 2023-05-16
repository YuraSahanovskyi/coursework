package com.example.coursework.services;

import com.example.coursework.model.User;
import com.example.coursework.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User getByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(Long id, String username, String password) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setPassword(password);
        this.add(user);

    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isExist(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        return userRepository.existsById(user.getId());
    }

    @Override
    public boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean isExist(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isPresent();
    }
}
