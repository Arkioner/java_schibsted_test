package main.java.com.Arkioner.schibstedTest.core;

/**
 * Created by arkioner on 17/05/15.
 */

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import main.java.com.Arkioner.schibstedTest.core.filter.ParameterFilter;
import main.java.com.Arkioner.schibstedTest.core.filter.SecurityFilter;
import main.java.com.Arkioner.schibstedTest.core.filter.SessionFilter;

import java.net.InetSocketAddress;

public class AppServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext context = server.createContext("/", new AppRouter());
        context.getFilters().add(new SessionFilter());
        context.getFilters().add(new SecurityFilter());
        context.getFilters().add(new ParameterFilter());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}

