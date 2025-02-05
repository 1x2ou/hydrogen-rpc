package com.hydrogen.hydrogenrpc.registry;

import com.hydrogen.hydrogenrpc.spi.SpiLoader;

/**
 * 注册中心工厂
 */
public class RegistryFactory {
    static{
        SpiLoader.load(Registry.class);
    }

    /**
     *  默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取示例
     * @param key
     * @return
     */
    public static Registry getInstance(String key){
        return SpiLoader.getInstance(Registry.class,key);
    }
}
