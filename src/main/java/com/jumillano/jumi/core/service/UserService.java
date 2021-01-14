package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IRoleDao;
import com.jumillano.jumi.core.model.dao.IUserDao;
import com.jumillano.jumi.core.model.entity.Role;
import com.jumillano.jumi.core.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    IUserDao userDao;
    IRoleDao roleDao;

    @Autowired
    public UserService(IUserDao userDao, IRoleDao roleDao) {
        this.userDao = userDao;
    }

    public Boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    public User save(User user) {
        return userDao.save(user);
    }

}
