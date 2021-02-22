package com.example.spring_boot.dao;

import com.example.spring_boot.model.User;

import java.util.List;

public interface UserDao {

    public void addUser(User user);
    public List<User> listUsers();
    public void updateUser(User user);
    public void deleteUser(Long id);
    public User getUserById(Long id);
    public User getUserByLogin(String login);
}
