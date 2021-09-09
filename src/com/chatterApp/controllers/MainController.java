package com.chatterApp.controllers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainController {

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080),0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/posts/search", new SearchHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
