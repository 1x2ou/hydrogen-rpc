package com.hydrpgen.example.provider;

import com.hydrogen.example.common.model.User;
import com.hydrogen.example.common.service.UserService;

public class UserServiceImpl implements UserService {
    public User getUser(User user) {
        System.out.println("用户名："+user.getName());
        return user;
    }
}
