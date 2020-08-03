package com.den.dao.impl;

import com.den.Entity.User;
import com.den.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;






public class HibernateUserDao implements UserDao {

    @Autowired
    public   SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.openSession();
    }





    @Override
    public List<User> findAll() {

        return getSession().createQuery("from User",User.class).list();
    }

    @Override
    public void save(User user) {
        getSession().save(user);

    }

    @Override
    public User getById(int id) {
        Query<User> query = sessionFactory.openSession().createQuery("from User where id = :id", User.class);
        query.setParameter("id",id);
        return query.list().stream().findAny().orElse(null);

    }

    @Override
    public void deleteById(int id) {
        Query<User> query = getSession().createQuery("delete from User where  id = :id", User.class);
        query.setParameter("id",id);


    }

    @Override
    public void update(User user) {

    }



    @Override
    public User getByEmail(String email) {
      Query<User> query = getSession().createQuery("from User where email = :email",User.class);
      query.setParameter("email",email);
      return query.list().stream().findAny().orElse(null);

    }

    @Override
    public void deletePage(User user) {

    }

    @Override
    public void restorePage(User user) {

    }

    @Override
    public void deleteImage(User user) {

    }


}
