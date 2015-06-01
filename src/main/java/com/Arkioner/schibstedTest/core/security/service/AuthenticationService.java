package main.java.com.Arkioner.schibstedTest.core.security.service;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpSession;
import main.java.com.Arkioner.schibstedTest.core.http.session.Session;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserTokenNotFoundException;

import java.util.Date;

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
        Session session = (Session) exchange.getAttribute(HttpSession.sessionKey);
        UserToken userToken = (UserToken) session.get(HttpSession.userTokenKey);
        if (userToken == null){
            throw new UserTokenNotFoundException("You are anonymous");
        }else if(userToken.isExpired()){
            throw new AuthenticationExpiredException("The token is expired");
        }
        UserTokenService.getInstance().renewUserToken(userToken);
        return userToken;
    }
}
