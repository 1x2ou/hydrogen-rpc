package com.hydrpgen.example.provider;

import com.hydrogen.example.common.model.ServiceMetaInfo;
import com.hydrogen.example.common.model.ServiceRegisterInfo;
import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.RpcApplication;
import com.hydrogen.hydrogenrpc.bootstrap.ProviderBootstrap;
import com.hydrogen.hydrogenrpc.config.RegistryConfig;
import com.hydrogen.hydrogenrpc.config.RpcConfig;
import com.hydrogen.hydrogenrpc.registry.LocalRegistry;
import com.hydrogen.hydrogenrpc.registry.Registry;
import com.hydrogen.hydrogenrpc.registry.RegistryFactory;
import com.hydrogen.hydrogenrpc.server.HttpServer;
import com.hydrogen.hydrogenrpc.server.VertxHttpServer;
import com.hydrogen.hydrogenrpc.server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

public class ProviderExample {
    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
