package com.hussein.provider;

import com.hussein.api.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * <p>Title: ProcessHandler</p>
 * <p>Description: 处理器</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 4:20 PM
 */
public class ProcessHandler implements Runnable {

    private final Socket socket;

    private final Map<String, Object> handlerMap;

    public ProcessHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            Object result = invoke(rpcRequest);
            oos.writeObject(result);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws Exception {
        String className = rpcRequest.getClassName();
        String methodName = rpcRequest.getMethodName();
        Object[] parameters = rpcRequest.getParameters();
        Class[] parameterTypes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }
        Object object = handlerMap.get(className);
        Method method = object.getClass().getMethod(methodName, parameterTypes);
        return method.invoke(object, parameters);
    }
}
