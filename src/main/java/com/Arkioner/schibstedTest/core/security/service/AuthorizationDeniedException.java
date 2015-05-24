package main.java.com.Arkioner.schibstedTest.core.security.service;

/**
 * Created by arkioner on 17/05/15.
 */
public class AuthorizationDeniedException extends Exception{
    public AuthorizationDeniedException(String string) {
        super(string);
    }
}
