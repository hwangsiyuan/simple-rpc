package com.hussein;

import com.hussein.api.HelloService;
import com.hussein.consumer.IServiceDiscovery;
import com.hussein.consumer.RpcClientProxy;
import com.hussein.consumer.ZkServiceDiscovery;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        IServiceDiscovery serviceDiscovery = new ZkServiceDiscovery();
        RpcClientProxy proxy = new RpcClientProxy(serviceDiscovery);
        HelloService helloService = proxy.clientProxy(HelloService.class);
        String result = helloService.sayHello("张三");
        System.out.println(result);
    }
}
