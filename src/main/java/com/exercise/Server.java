package com.exercise;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Represents a server that is a REST web service
 */
public class Server {

    private static volatile boolean operable = false;
    private static volatile boolean canBeStopped = false;

    public static void main( String[] args ) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(8080),25);
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.createContext("/api/1.0/transfer",TransferHandler.INSTANCE);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                operable = false;
                while(!canBeStopped){
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException ie){}
                }
                server.stop(5);
            }
        });
    }
}
