package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IRoleDao;
import com.jumillano.jumi.core.model.dao.IUserDao;
import com.jumillano.jumi.core.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    IRoleDao roleDao;

    @Autowired
    public RoleService(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Optional<Role> findByName(com.jumillano.jumi.core.model.enums.Role role) {
        return roleDao.findByName(role);
    }
}
