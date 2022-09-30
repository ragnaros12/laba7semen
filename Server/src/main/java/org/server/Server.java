package org.server;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


//314211
public class Server {
    private final String host;
    private final int ip;
    ServerSocket serverSocket;

    public Server(String url, int ip) {
        this.host = url;
        this.ip = ip;
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(InetAddress.getByName(host), ip));
    }
    public Socket accept() throws IOException {
        return serverSocket.accept();
    }
}
