package com.hydrpgen.example.provider;

import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.RpcApplication;
import com.hydrogen.hydrogenrpc.server.HttpServer;
import com.hydrogen.hydrogenrpc.server.VertxHttpServer;
import com.hydrogen.hydrogenrpc.registry.LocalRegistry;

public class EasyProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
