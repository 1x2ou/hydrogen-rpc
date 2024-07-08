package com.hydrpgen.example.provider;

import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.HttpServer;
import com.hydrogen.hydrogenrpc.VertxHttpServer;
import com.hydrpgen.example.registry.LocalRegistry;

public class EasyProviderExample {
    public static void main(String[] args) {
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
