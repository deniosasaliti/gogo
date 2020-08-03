package com.den.dao.impl;

import com.den.Entity.User;
import com.den.dao.UserDao;
import com.den.mapper.UserMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class JdbcTemplateUserDao implements UserDao {

    public final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<User> findAll() {
        String sql= "SELECT * FROM new_schema.123";
        return jdbcTemplate.query(sql,new UserMapper());
    }

    public void save(User user) {
        String sql ="INSERT INTO new_schema.123 (name ,email,age,password,status) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getAge(),user.getPassword(),user.getStatus());

    }

    public User getById(int id) {

        String sql ="SELECT * FROM  new_schema.123 WHERE id=?";
        return jdbcTemplate.queryForObject(sql,new UserMapper(),id);
    }

    public User getByEmail(String email) {

        String sql ="SELECT * FROM  new_schema.123 WHERE email=?";
        return jdbcTemplate.query(sql,new Object []{email},new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }



    public void deleteById(int id) {
        String sql = "DELETE FROM new_schema.123 WHERE id=?";
        jdbcTemplate.update(sql,id);

    }

    public void update(User user) {
        user.setStatusActive();
        String sql = "UPDATE new_schema.123 SET name=?,age=?,email=?,filename=?,status=? WHERE  id=?";
        jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getEmail(),user.getFileName(),user.getStatus(),user.getId());

    }
    public void deletePage(User user){
        user.setStatusDeleted();
        String sql = "UPDATE new_schema.123 SET status=? WHERE id=?";
        jdbcTemplate.update(sql,user.getStatus(),user.getId());
    }

    @Override
    public void restorePage(User user) {
        user.setStatusActive();
        String sql = "UPDATE new_schema.123 SET  status=? WHERE id=?";
        jdbcTemplate.update(sql,user.getStatus(),user.getId());
    }

    @Override
    public void deleteImage(User user) {
        String sql = "UPDATE new_schema.123 SET  filename=? WHERE id=?";
        jdbcTemplate.update(sql,"",user.getId());
    }




}
