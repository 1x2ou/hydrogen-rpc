package com.hydrogen.hydrogenrpc.proxy;


import java.lang.reflect.Proxy;
import java.util.Calendar;

/**
 * 服务代理工厂
 */
public class ServiceProxyFactory {
    /**
     * 根据服务类型获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());

    }
}
