package com.chatterApp.controllers;

import com.chatterApp.сonfig.DbRegistry;
import com.chatterApp.dao.PostDao;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainController {
    private HttpServer server;

    public static void main(String[] args) throws IOException {
        int initialPort = 8080;
        DbRegistry.setPostSource(new PostDao());

        new MainController().startServer(initialPort); //because startServer non static
    }

    public void startServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/posts/search", new SearchHandler());
        server.start();
    }

    public void stopServer(int delay) {
        server.stop(delay);
    }
}
