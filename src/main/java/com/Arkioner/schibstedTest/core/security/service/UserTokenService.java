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

    public static UserTokenService getInstance()
    {
        if(null == instance){
            instance = new UserTokenService();
        }
        return instance;
    }

    public void renewUserToken(UserToken userToken){
        userToken.setExpires(new Date(System.currentTimeMillis()+this.userTokenLive));
    }

    public void expireUserToken(UserToken userToken){
        userToken.setExpires(new Date(0));
    }

    public UserToken createUserToken(User user) {
        UserToken userToken = new UserToken(user);
        this.renewUserToken(userToken);
        this.inMemoryUserToken.save(userToken);
        return userToken;
    }
}
