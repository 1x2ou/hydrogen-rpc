package com.hydrogen.example.provider;

import com.hydrogen.example.common.model.User;
import com.hydrogen.example.common.service.UserService;

public class EasyConsumerExample {
    public static void main(String[] args) {
        //静态代理
        UserService userService = new UserServiceProxy();

        User user = new User();
        user.setName("lingxiao");
        //调用
        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
    }
}
