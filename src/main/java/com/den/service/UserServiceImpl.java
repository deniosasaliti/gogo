package com.den.service;

import com.den.Entity.User;
import com.den.dao.UserDao;
import com.den.dao.impl.JpaUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;



    public UserServiceImpl(@Qualifier("jdbcTemplateUserDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }




    public List<User> findAll() {
        return userDao.findAll();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public void deleteById(int id) {
        userDao.deleteById(id);

    }

    public void update(User user) {
        userDao.update(user);

    }
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void deletePage(User user) {
         userDao.deletePage(user);
    }

    @Override
    public void restorePage(User user) {
        userDao.restorePage(user);
    }

    @Override
    public void deleteImage(User user) {
        userDao.deleteImage(user);
    }
}
