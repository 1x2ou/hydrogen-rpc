package com.hydrogen.hydrogenrpc.config;

import lombok.Data;

/**
 * RPC 配置类
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private  String name = "hydrogen-rpc";
    /**
     * 版本号
     */
    private  String version = "1.0";
    /**
     * 服务器主机名
     */
    private  String serverHost = "localhost";
    /**
     * 服务器端口
     */
    private  Integer  serverPort = 8080;
}
