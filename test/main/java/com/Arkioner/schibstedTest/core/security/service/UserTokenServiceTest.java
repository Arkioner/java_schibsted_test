package main.java.com.Arkioner.schibstedTest.core.security.service;

import junit.framework.TestCase;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Yoshi on 02/06/2015.
 */
public class UserTokenServiceTest extends TestCase {

    @Test
    public void testRenewUserToken() throws Exception {
        UserToken userToken = new UserToken(null);
        Date expiration = new Date();
        userToken.setExpires(expiration);
        UserTokenService.getInstance().renewUserToken(userToken);
        assertTrue("Test that the expiration is renewed", expiration.before(userToken.getExpires()));
    }

    @Test
    public void testExpireUserToken() throws Exception {
        assertTrue("Test false",true);
    }

    @Test
    public void testCreateUserToken() throws Exception {
        assertTrue("Test true",true);
    }
}