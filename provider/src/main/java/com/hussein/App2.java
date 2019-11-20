package com.hussein;

import com.hussein.provider.HelloServiceImpl;
import com.hussein.provider.RpcServer;
import com.hussein.registry.IRegistryCenter;
import com.hussein.registry.ZkRegistryCenter;

/**
 * Hello world!
 */
public class App2 {

    public static void main(String[] args) {
        IRegistryCenter registryCenter = new ZkRegistryCenter();
        RpcServer rpcServer = new RpcServer(registryCenter, 1020);
        rpcServer.bind(new HelloServiceImpl());
        rpcServer.publish();
    }
}
