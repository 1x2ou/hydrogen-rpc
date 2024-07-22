package com.hydrogen.hydrogenrpc.protocol;

import cn.hutool.http.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolMessage<T> {
    /**
     * 请求头
     */
    private Header header;
    /**
     * 消息体（请求或相应对象）
     */
    private T body;

    @Data
    public static class Header{
        /**
         * 魔数，保证安全性
         */
        private byte magic;
        /**
         * 版本号
         */
        private byte version;
        /**
         * 序列化器
         */
        private byte serializer;
        /**
         * 消息类型（请求 / 相应）
         */
        private byte type;
        /**
         * 状态
         */
        private byte status;
        /**
         * 请求 id
         */
        private long  requestId;
        /**
         * 消息体长度
         */
        private int bodyLength;

    }

}
