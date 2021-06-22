package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IUserDao;
import com.jumillano.jumi.core.model.dto.request.SignUpRequest;
import com.jumillano.jumi.core.model.entity.Role;
import com.jumillano.jumi.core.model.entity.User;
import com.jumillano.jumi.core.model.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    IUserDao userDao;
    PasswordEncoder encoder;
    RoleService roleService;
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserService(IUserDao userDao, PasswordEncoder encoder, RoleService roleService,
                       UserDetailsServiceImpl userDetailsService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    public List<User> findAllUser() {
        return userDao.findAll();
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

    public Optional<User> findById(String id) {
        return userDao.findById(id);
    }

    public User updateUser(String id, SignUpRequest signUpRequest) {
        Optional<User> currentUser = userDao.findById(id);

        User userUpdated;

        currentUser.get().setName(signUpRequest.getName());
        currentUser.get().setUsername(signUpRequest.getUsername());
        currentUser.get().setEmail(signUpRequest.getEmail());
        //currentUser.get().setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    case "leader":
                        Role leadRole = roleService.findByName(RoleType.ROLE_LEADER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(leadRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        currentUser.get().setRoles(roles);

        userUpdated = userDao.save(currentUser.get());

        return userUpdated;
    }

   public User updateUserPassword (String id, SignUpRequest signUpRequest) {

        Optional<User> currentUser = userDao.findById(id);

        User user = new User();

        user.setId(currentUser.get().getId());
        user.setUsername(currentUser.get().getUsername());
        user.setEmail(currentUser.get().getEmail());
        user.setName(currentUser.get().getName());
        user.setRoles(currentUser.get().getRoles());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        userDao.save(user);

        //currentUser.get().setPassword(encoder.encode(signUpRequest.getPassword()));

        return user;
    }

}
