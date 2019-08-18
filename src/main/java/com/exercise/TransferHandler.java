package com.exercise;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * /api/transfer
 */
public enum TransferHandler implements HttpHandler {

    INSTANCE;

    private volatile boolean active = true;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        int responseStatus = 0;
        // TODO: if not operable, send 500
        if(!active){
            responseStatus = 500;
            response = "Service is not operable, please try again later";
        } else {
            // TODO: handle transfer request

            // TODO: create text response
            responseStatus = 200;
        }
        httpExchange.sendResponseHeaders(responseStatus,response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public synchronized String transfer(String fromAccId, String toAccId, double amount){
        // TODO: withdraw from acc 1, return error message if can't withdraw
        // TODO: add to acc 2
        return null;
    }

    public synchronized Transaction startTransaction() {

        return new Transaction("");
    }
}
