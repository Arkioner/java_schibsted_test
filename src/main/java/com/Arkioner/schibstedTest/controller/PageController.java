package main.java.com.Arkioner.schibstedTest.controller;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.http.HttpParameters;
import main.java.com.Arkioner.schibstedTest.core.http.HttpRedirect;
import main.java.com.Arkioner.schibstedTest.core.http.HttpSession;
import main.java.com.Arkioner.schibstedTest.core.http.session.Session;
import main.java.com.Arkioner.schibstedTest.core.security.service.UserTokenService;
import main.java.com.Arkioner.schibstedTest.core.security.token.InMemoryUserTokenRepository;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserTokenNotFoundException;
import main.java.com.Arkioner.schibstedTest.core.view.ViewHandler;
import main.java.com.Arkioner.schibstedTest.model.User.InMemoryUserRepository;
import main.java.com.Arkioner.schibstedTest.model.User.User;
import main.java.com.Arkioner.schibstedTest.model.User.UserNotFoundException;
import main.java.com.Arkioner.schibstedTest.model.User.UserRepositoryInterface;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
        String response = ViewHandler.getInstance().loadView("page",this.getPageParameters(exchange));
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void page2Action(HttpExchange exchange) throws IOException {
        String response = ViewHandler.getInstance().loadView("page",this.getPageParameters(exchange));
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void page3Action(HttpExchange exchange) throws IOException {
        String response = ViewHandler.getInstance().loadView("page",this.getPageParameters(exchange));
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> getPageParameters(HttpExchange exchange) {
        Session session = (Session) exchange.getAttribute(HttpSession.sessionKey);
        Map<String, String> params = new HashMap<>();
        UserToken userToken = (UserToken) session.get(HttpSession.userTokenKey);
        params.put("{{username}}",userToken.getUser().getUsername());
        return params;
    }
}
