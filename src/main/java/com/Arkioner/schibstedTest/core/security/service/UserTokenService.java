package main.java.com.Arkioner.schibstedTest.core.security.service;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.security.token.InMemoryUserTokenRepository;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserTokenNotFoundException;

/**
 * Created by arkioner on 17/05/15.
 */
public class UserTokenService {

    private InMemoryUserTokenRepository inMemoryUserToken;
    private static UserTokenService instance;

    public UserTokenService() {
        this.inMemoryUserToken = InMemoryUserTokenRepository.getInstance();
    }

    public static final String SECURITY_COOKIE_KEY = "userToken";

    public static UserTokenService getInstance()
    {
        if(null == instance){
            instance = new UserTokenService();
        }
        return instance;
    }

    public UserToken getUserToken(String tokenId) throws UserTokenNotFoundException
    {
        return this.inMemoryUserToken.findByUuid(tokenId);
    }
}
