package com.smartcat.core.remoting.transport;

import com.smartcat.core.remoting.dto.RpcRequest;

/**
 * description: ClientTransport
 * date: 2020/10/30 14:02
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public interface ClientTransport {
    /**
     * send rpc request to server and get result
     *
     * @param rpcRequest message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
