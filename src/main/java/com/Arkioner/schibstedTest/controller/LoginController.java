package main.java.com.Arkioner.schibstedTest.controller;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.http.HttpParameters;
import main.java.com.Arkioner.schibstedTest.core.http.HttpRedirect;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthenticationService;
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
public class LoginController {
    private static LoginController instance;

    public static LoginController getInstance(){
        if(instance == null){
            instance = new LoginController();
        }
        return instance;
    }

    public void loginPostAction(HttpExchange exchange) throws IOException {
        String username = HttpParameters.getInstance().getParameter(exchange,"username");
        String password = HttpParameters.getInstance().getParameter(exchange,"password");
        User user = null;
        try {
            user = InMemoryUserRepository.getInstance().findUserByUsernameAndPassword(username,password);
        } catch (UserNotFoundException e) {
            HttpRedirect.getInstance().sendRedirect(exchange,"/login?Error");
            return;
        }
        UserToken userToken = new UserToken(user);
        InMemoryUserTokenRepository.getInstance().save(userToken);
        HttpCookie.getInstance().addCookie(exchange, UserTokenService.SECURITY_COOKIE_KEY, userToken.getUuid());
        HttpRedirect.getInstance().sendRedirect(exchange, user.getLandingPage());
    }
    public void loginFormAction(HttpExchange exchange) throws IOException {
        URI fileLocation = null;
        try {
            fileLocation = this.getClass().getClassLoader().getResource("main/java/com/Arkioner/schibstedTest/view/login.html").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(fileLocation);
        String response = new String(java.nio.file.Files.readAllBytes(path));
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
