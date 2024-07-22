package com.hydrogen.hydrogenrpc.bootstrap;

import com.hydrogen.hydrogenrpc.RpcApplication;

/**
 * 服务消费者启动类
 */
public class ConsumerBootstrap {
    /**
     * 初始化
     */
    public static void init() {
        //RPC 框架初始化（配置和注册中心比）
        RpcApplication.init();
    }
}
