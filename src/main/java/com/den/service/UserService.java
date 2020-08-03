package com.den.service;

import com.den.Entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void save( User user);

    User getById(int id);

    void deleteById(int id);

    void update (User user);

    User getByEmail(String email);

    void deletePage(User user);

    void restorePage(User user);

    void  deleteImage(User user);

}
