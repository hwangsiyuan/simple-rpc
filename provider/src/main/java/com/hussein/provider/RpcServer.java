package com.hussein.provider;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hussein.registry.IRegistryCenter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: RpcServer</p>
 * <p>Description: rpc 服务</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 3:36 PM
 */
@Slf4j
public class RpcServer {

    /**
     * 注册中心
     */
    private IRegistryCenter registryCenter;

    /**
     * 服务发布的host
     */
    private String serverHost;

    /**
     * 服务发布的端口
     */
    private int serverPort;

    /**
     * 服务名和服务的对照
     */
    private Map<String, Object> handlerMap = new HashMap<>();

    private Executor executor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build());

    public RpcServer(IRegistryCenter registryCenter, int serverPort) {
        this(registryCenter, "127.0.0.1", serverPort);
    }

    public RpcServer(IRegistryCenter registryCenter, String serverHost, int serverPort) {
        this.registryCenter = registryCenter;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void bind(Object... services) {
        for (Object service : services) {
            RpcAnnotation rpcAnnotation = service.getClass().getAnnotation(RpcAnnotation.class);
            if (rpcAnnotation != null) {
                String className = rpcAnnotation.value().getName();
                handlerMap.put(className, service);
            }
        }
    }

    public void publish() {
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            handlerMap.forEach((className, rpcService) -> {
                registryCenter.register(className, serverHost + ":" + serverPort);
                log.info("发布服务成功：【serviceName：{}，address：{}】", className, serverHost + ":" + serverPort);
            });
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new ProcessHandler(socket, handlerMap));
            }
        } catch (IOException e) {
            log.error("发布服务成功失败", e);
        }
    }
}
