package com.chatterApp.controllers;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainController {
    static HttpServer server;
    static int initialPort = 8080;

    public static void main(String[] args) throws IOException {
        new MainController().startServer(initialPort); //because startServer non static
    }

    public void startServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/posts/search", new SearchHandler());
        server.start();
    }
}
