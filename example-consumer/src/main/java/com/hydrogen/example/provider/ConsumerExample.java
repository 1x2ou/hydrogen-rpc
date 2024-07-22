package com.hydrogen.example.provider;

import com.hydrogen.example.common.model.User;
import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.bootstrap.ConsumerBootstrap;
import com.hydrogen.hydrogenrpc.proxy.ServiceProxyFactory;

public class ConsumerExample {
    public static void main(String[] args) {
        //服务消费者初始化
        ConsumerBootstrap.init();
        //获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("lingxiao");
        //调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
