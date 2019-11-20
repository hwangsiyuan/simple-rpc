package com.hussein.consumer;

import com.hussein.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p>Title: TcpTransport</p>
 * <p>Description: rpc客户端</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/20 11:59 AM
 */
public class TcpTransport {


    public Object send(String address, RpcRequest rpcRequest) {
        Socket socket = getSocket(address);
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            oos.writeObject(rpcRequest);
            oos.flush();
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Socket getSocket(String address) {
        Socket socket = null;
        String[] hostList = address.split(":");
        try {
            socket = new Socket(hostList[0], Integer.valueOf(hostList[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
