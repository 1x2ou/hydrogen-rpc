package com.hydrogen.hydrogenrpc.server.tcp;

import com.hydrogen.hydrogenrpc.model.RpcRequest;
import com.hydrogen.hydrogenrpc.model.RpcResponse;
import com.hydrogen.hydrogenrpc.protocol.ProtocolMessage;
import com.hydrogen.hydrogenrpc.protocol.ProtocolMessageDecoder;
import com.hydrogen.hydrogenrpc.protocol.ProtocolMessageEncoder;
import com.hydrogen.hydrogenrpc.protocol.ProtocolMessageTypeEnum;
import com.hydrogen.hydrogenrpc.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.Method;
import io.vertx.core.buffer.Buffer;

/**
 * 请求处理器（服务提供者）
 */
public class TcpServerHandler implements Handler<NetSocket> {
    @Override
    public void handle(NetSocket netSocket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            // 处理请求代码
            //接收请求，解码
            ProtocolMessage<RpcRequest> protocolMessage;
            try{
                protocolMessage = (ProtocolMessage<RpcRequest>) ProtocolMessageDecoder.decode(buffer);
            } catch (IOException e) {
                throw new RuntimeException("协议消息解码错误");
            }
            RpcRequest rpcRequest = protocolMessage.getBody();

            //处理请求
            //构造响应结果
            RpcResponse rpcResponse = new RpcResponse();
            try{
                //获取要调用的服务实现类，通过反射调用
                Class<?> imClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = imClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(imClass.newInstance(),rpcRequest.getArgs());
                //封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataTypes(method.getReturnType());
                rpcResponse.setMessage("ok");
            }catch (Exception e){
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            // 发送响应，解码
            ProtocolMessage.Header header = protocolMessage.getHeader();
            header.setType((byte) ProtocolMessageTypeEnum.RESPONSE.getKey());
            ProtocolMessage<RpcResponse> responseProtocolMessage = new ProtocolMessage<>(header,rpcResponse);
            try {
                Buffer encode = ProtocolMessageEncoder.encode(responseProtocolMessage);
                netSocket.write(encode);
            } catch (IOException e) {
                throw new RuntimeException("协议消息编码错误");
            }
        });
        netSocket.handler(bufferHandlerWrapper);
    }
}
