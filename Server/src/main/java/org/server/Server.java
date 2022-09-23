package org.server;

import com.helper.CommandInfo;
import com.helper.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


//314211
public class Server {
    private final String url;
    private final int host;
    ServerSocket serverSocket;

    public Server(String url, int host) {
        this.url = url;
        this.host = host;
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(InetAddress.getByName(url), host));
    }
    public Socket accept() throws IOException {
        return serverSocket.accept();
    }

}
