package main.java.com.Arkioner.schibstedTest.core.security.service;

import junit.framework.TestCase;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Yoshi on 02/06/2015.
 */
public class UserTokenServiceTest extends TestCase {

    @Test
    public void testRenewUserToken() throws Exception {
        UserToken userToken = new UserToken(null);
        Date expiration = new Date(0);
        userToken.setExpires(expiration);
        assertTrue("Test that the user token is expired before renew it", userToken.isExpired());
        UserTokenService.getInstance().renewUserToken(userToken);
        assertFalse("Test that the user token isn't expired so it is renewed", userToken.isExpired());
    }

    @Test
    public void testExpireUserToken() throws Exception {
        UserToken userToken = new UserToken(null);
        Date expiration = new Date();
        userToken.setExpires(expiration);
        UserTokenService.getInstance().expireUserToken(userToken);
        assertTrue("Test that the user token is expired", userToken.isExpired());
    }

    @Test
    public void testCreateUserToken() throws Exception {
        assertTrue("Test true",true);
    }
}