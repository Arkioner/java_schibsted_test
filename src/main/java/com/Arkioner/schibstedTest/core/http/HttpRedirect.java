package main.java.com.Arkioner.schibstedTest.core.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by arkioner on 18/05/15.
 */
public class HttpRedirect {
    private static HttpRedirect instance;

    public static HttpRedirect getInstance(){
        if(instance == null){
            instance = new HttpRedirect();
        }
        return instance;
    }

    public void sendRedirect(HttpExchange exchange, String url) throws IOException {
        exchange.getResponseHeaders().add("Location",url);
        exchange.sendResponseHeaders(302, -1);
    }
}
