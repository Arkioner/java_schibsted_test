package main.java.com.Arkioner.schibstedTest.core.security.service;

/**
 * Created by arkioner on 17/05/15.
 */
public class AuthenticationExpiredException extends Exception{
    public AuthenticationExpiredException(String string) {
        super(string);
    }
}
