package main.java.com.Arkioner.schibstedTest.core.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by arkioner on 18/05/15.
 */
public class HttpNotFound {
    private static HttpNotFound instance;

    public static HttpNotFound getInstance(){
        if(instance == null){
            instance = new HttpNotFound();
        }
        return instance;
    }

    public void sendNotFound(HttpExchange exchange) throws IOException {
        String response = "Not found";
        exchange.sendResponseHeaders(404, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
