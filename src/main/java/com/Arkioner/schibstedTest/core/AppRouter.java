package main.java.com.Arkioner.schibstedTest.core;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.Arkioner.schibstedTest.controller.LoginController;
import main.java.com.Arkioner.schibstedTest.controller.PageController;
import main.java.com.Arkioner.schibstedTest.core.http.HttpNotFound;

import java.io.IOException;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.http.HttpParameters;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthenticationService;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthorizationService;
import main.java.com.Arkioner.schibstedTest.core.security.service.UserTokenService;

/**
 * Created by arkioner on 17/05/15.
 */
public class AppRouter implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String hola = HttpCookie.getInstance().getCookie(exchange, UserTokenService.SECURITY_COOKIE_KEY);
        HttpCookie.getInstance().addCookie(exchange, "cookiesAccepted", "true");
        String path = exchange.getRequestURI().getPath().toLowerCase();
        switch (path){
            case "/login":
                LoginController.getInstance().loginFormAction(exchange);
                break;
            case "/login/execute":
                LoginController.getInstance().loginPostAction(exchange);
                break;
            case "/private/page1":
                PageController.getInstance().page1Action(exchange);
                break;
            case "/private/page2":
                PageController.getInstance().page2Action(exchange);
                break;
            case "/private/page3":
                PageController.getInstance().page3Action(exchange);
                break;
            default:
                HttpNotFound.getInstance().sendNotFound(exchange);
                break;
        }
    }
}
