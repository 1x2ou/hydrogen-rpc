package com.hydrogen.hydrogenrpc.fault.tolerant;

import com.hydrogen.hydrogenrpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败策略
 */
public class FailFastTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错",e);
    }
}
