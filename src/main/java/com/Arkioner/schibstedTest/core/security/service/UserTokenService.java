package main.java.com.Arkioner.schibstedTest.core.security.service;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.security.token.InMemoryUserTokenRepository;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserTokenNotFoundException;
import main.java.com.Arkioner.schibstedTest.model.User.User;

import java.util.Date;

/**
 * Created by arkioner on 17/05/15.
 */
public class UserTokenService {

    private int userTokenLive = 5*60*1000;

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

    public UserToken getUserToken(String tokenId) throws UserTokenNotFoundException, AuthenticationExpiredException {
        UserToken userToken = this.inMemoryUserToken.findByUuid(tokenId);
        if(userToken.isExpired()){
            throw new AuthenticationExpiredException("The token is expired");
        }
        userToken.setExpires(new Date(System.currentTimeMillis()+this.userTokenLive));
        return userToken;
    }

    public void expireUserToken(String tokenId) throws UserTokenNotFoundException {
        UserToken userToken = this.inMemoryUserToken.findByUuid(tokenId);
        userToken.setExpires(new Date());
    }

    public UserToken createUserToken(User user) {
        UserToken userToken = new UserToken(user);
        userToken.setExpires(new Date(System.currentTimeMillis()+this.userTokenLive));
        this.inMemoryUserToken.save(userToken);
        return userToken;
    }
}
