package org.client;

import com.helper.CommandInfo;
import com.helper.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Client {

    String host;
    int ip;
    SocketChannel socketChannel;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    public Client(String host, int ip) {
        this.host = host;
        this.ip = ip;
    }

    public void connect() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(host, ip));

    }

    public void Send(CommandInfo commandInfo) throws IOException {
        if(outputStream == null){
            outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        }
        outputStream.writeObject(commandInfo);
    }

    public Response Receive() throws Exception {
        if(inputStream == null){
            inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
        }
        return (Response) inputStream.readObject();
    }
}
