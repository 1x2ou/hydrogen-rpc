package com.example.examplespringbootconsumer;

import com.hydrogen.example.common.model.User;
import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.springboot.start.annotation.RpcReference;
import com.hydrogen.hydrogenrpc.springboot.start.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("lx");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}