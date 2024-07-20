package com.hydrogen.example.common.service;

import com.hydrogen.example.common.model.User;

public interface UserService {
    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 获取数字,用于测试mock接口返回值
     * @return
     */
    default int getNumber(){
        return 1;
    }
}
