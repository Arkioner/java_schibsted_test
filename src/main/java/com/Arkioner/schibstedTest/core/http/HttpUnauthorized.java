package main.java.com.Arkioner.schibstedTest.core.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by arkioner on 18/05/15.
 */
public class HttpUnauthorized {
    private static HttpUnauthorized instance;

    public static HttpUnauthorized getInstance(){
        if(instance == null){
            instance = new HttpUnauthorized();
        }
        return instance;
    }

    public void sendUnathorized(HttpExchange exchange) throws IOException {
        String response = "Not authorized";
        exchange.sendResponseHeaders(401, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
