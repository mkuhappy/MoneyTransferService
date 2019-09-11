package com.exercise;

import java.io.IOException;

public class Runner {

    public static void main( String[] args ) throws IOException {

        final Server server = new Server(8080,25);
        server.startServer();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                server.terminate();
            }
        });
    }

}
