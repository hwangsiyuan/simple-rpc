package com.hussein.consumer;

import java.lang.reflect.Proxy;

/**
 * <p>Title: RpcClientProxy</p>
 * <p>Description: rpc 客户端代理</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/20 12:07 PM
 */
public class RpcClientProxy {

    private IServiceDiscovery serviceDiscovery;

    public RpcClientProxy(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public <T> T clientProxy(final Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RemoteInvocationHandler(serviceDiscovery));
    }
}
