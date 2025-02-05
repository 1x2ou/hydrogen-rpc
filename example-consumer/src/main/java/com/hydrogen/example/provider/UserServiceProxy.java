package com.hydrogen.example.provider;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.hydrogen.example.common.model.User;
import com.hydrogen.example.common.service.UserService;
import com.hydrogen.hydrogenrpc.RpcApplication;
import com.hydrogen.hydrogenrpc.model.RpcRequest;
import com.hydrogen.hydrogenrpc.model.RpcResponse;
import com.hydrogen.hydrogenrpc.serializer.JdkSerializer;
import com.hydrogen.hydrogenrpc.serializer.Serializer;
import com.hydrogen.hydrogenrpc.serializer.SerializerFactory;

import java.io.IOException;
import java.util.ServiceLoader;

public class UserServiceProxy implements UserService {
    public User getUser(User user) {
        //指定序列化器
//        Serializer serializer = new JdkSerializer();

//        Serializer serializer = null;
//        ServiceLoader<Serializer> serviceLoader = ServiceLoader.load(Serializer.class);
//        for(Serializer service:serviceLoader){
//            serializer = service;
//        }
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        //发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
