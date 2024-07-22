package com.hydrogen.hydrogenrpc.springboot.start.bootstrap;

import com.hydrogen.example.common.model.ServiceMetaInfo;
import com.hydrogen.hydrogenrpc.RpcApplication;
import com.hydrogen.hydrogenrpc.config.RegistryConfig;
import com.hydrogen.hydrogenrpc.config.RpcConfig;
import com.hydrogen.hydrogenrpc.registry.LocalRegistry;
import com.hydrogen.hydrogenrpc.registry.Registry;
import com.hydrogen.hydrogenrpc.registry.RegistryFactory;
import com.hydrogen.hydrogenrpc.springboot.start.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * RPC 服务提供者启动
 */
public class RpcProviderBootstrap implements BeanPostProcessor {
    /**
     * Bean 初始化后执行，注册服务
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        RpcService rpcService = beanClass.getAnnotation(RpcService.class);
        if (rpcService != null) {
            //需要注册服务
            //1.获取服务基本信息
            Class<?> interfaceClas = rpcService.interfaceClass();
            //默认值处理
            if (interfaceClas == void.class) {
                interfaceClas = beanClass.getInterfaces()[0];
            }
            String serviceName = interfaceClas.getName();
            String serviceVersion = rpcService.serviceVersion();
            //2.注册服务
            // 本地注册
            LocalRegistry.register(serviceName, beanClass);
            //全局配置
            final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            //注册服务到服务中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(serviceVersion);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            }catch (Exception e){
                throw new RuntimeException(serviceName + "服务注册失败", e);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean,beanName);
    }
}
