package com.exercise;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Represents a server that is a REST web service
 */
public class Server {

    public static final String CONTEXT_URL = "/api/1.0/transfer";

    private HttpServer server;
    private TransferHandler handler;

    /**
     * Creates a new instance of server
     * @param portNumber port to listen on
     * @param maxRequests maximum number of requests in queue
     * @throws IOException if cannot create a server with the specified port number
     */
    public Server(int portNumber, int maxRequests) throws IOException {
        handler = TransferHandler.INSTANCE;

        server = HttpServer.create(new InetSocketAddress(portNumber),maxRequests);
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.createContext(CONTEXT_URL,handler);
    }

    /**
     * Starts the server
     */
    public void startServer(){
        server.start();
    }

    /**
     * Stops the server after the transfer handler is properly un-registered
     */
    public void terminate(){
        unregisterHandler();
        stopServer();
    }

    private void stopServer(){
        server.stop(5);
    }

    private void unregisterHandler(){
        server.removeContext(CONTEXT_URL);
        // TODO: dispose transfer handler and wait for all operations completed
    }
}
