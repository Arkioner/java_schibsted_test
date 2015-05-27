package main.java.com.Arkioner.schibstedTest.core.security.service;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserTokenNotFoundException;

/**
 * Created by arkioner on 17/05/15.
 */
public class AuthenticationService {
    private static AuthenticationService instance;

    public static AuthenticationService getInstance(){
        if(instance == null){
            instance = new AuthenticationService();
        }
        return instance;
    }

    public UserToken getAuthentication(HttpExchange exchange) throws UserTokenNotFoundException, AuthenticationExpiredException {
        String tokenId = HttpCookie.getInstance().getCookie(exchange, UserTokenService.SECURITY_COOKIE_KEY);
        return UserTokenService.getInstance().getUserToken(tokenId);
    }
}
