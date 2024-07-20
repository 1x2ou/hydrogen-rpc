package com.hydrpgen.example.provider;

import com.hydrogen.example.common.model.ServiceMetaInfo;
import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.RpcApplication;
import com.hydrogen.hydrogenrpc.config.RegistryConfig;
import com.hydrogen.hydrogenrpc.config.RpcConfig;
import com.hydrogen.hydrogenrpc.registry.LocalRegistry;
import com.hydrogen.hydrogenrpc.registry.Registry;
import com.hydrogen.hydrogenrpc.registry.RegistryFactory;
import com.hydrogen.hydrogenrpc.server.HttpServer;
import com.hydrogen.hydrogenrpc.server.VertxHttpServer;

public class ProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
