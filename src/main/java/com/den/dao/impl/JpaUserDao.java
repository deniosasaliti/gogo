package com.den.dao.impl;

import com.den.Entity.User;
import com.den.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Component
@Transactional
public class JpaUserDao implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(
                "select u from User u",User.class).getResultList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void save(User user) {
        entityManager.persist(user);

    }

    @Override
    public User getById(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select User from User u where u.id= :id",User.class
        );
        query.setParameter("id",id);
        return query.getResultList().stream().findAny().orElse(null);

    }


    @Override
    public void deleteById(int id) {
        entityManager.detach(id);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getByEmail(String email) {
        return null;
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
