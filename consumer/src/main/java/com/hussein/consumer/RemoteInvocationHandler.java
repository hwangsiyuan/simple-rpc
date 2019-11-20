package com.hussein.consumer;

import com.hussein.api.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Title: RemoteInvocationHandler</p>
 * <p>Description: 远程调用</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/20 11:55 AM
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private IServiceDiscovery serviceDiscovery;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        String address = serviceDiscovery.discovery(rpcRequest.getClassName());
        TcpTransport tcpTransport = new TcpTransport();
        return tcpTransport.send(address, rpcRequest);
    }
}
