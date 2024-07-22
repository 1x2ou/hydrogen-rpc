package com.hydrogen.hydrogenrpc.fault.tolerant;

import com.hydrogen.hydrogenrpc.model.RpcResponse;

import java.util.Map;

/**
 * 故障转移
 */
public class FailOverTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        //todo：拓展
        return null;
    }
}
