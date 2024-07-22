package com.example.examplespringbootprovider;

import com.hydrogen.example.common.model.User;
import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.springboot.start.annotation.RpcReference;
import com.hydrogen.hydrogenrpc.springboot.start.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}