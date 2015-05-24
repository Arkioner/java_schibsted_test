package main.java.com.Arkioner.schibstedTest.controller;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.http.HttpParameters;
import main.java.com.Arkioner.schibstedTest.core.http.HttpRedirect;
import main.java.com.Arkioner.schibstedTest.core.security.service.UserTokenService;
import main.java.com.Arkioner.schibstedTest.core.security.token.InMemoryUserTokenRepository;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.model.User.InMemoryUserRepository;
import main.java.com.Arkioner.schibstedTest.model.User.User;
import main.java.com.Arkioner.schibstedTest.model.User.UserNotFoundException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by arkioner on 17/05/15.
 */
public class PageController {
    private static PageController instance;

    public static PageController getInstance(){
        if(instance == null){
            instance = new PageController();
        }
        return instance;
    }

    public void page1Action(HttpExchange exchange) throws IOException {
        String response = "Hello user with role for page 1";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void page2Action(HttpExchange exchange) throws IOException {
        String response = "Hello user with role for page 2";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void page3Action(HttpExchange exchange) throws IOException {
        String response = "Hello user with role for page 3";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
