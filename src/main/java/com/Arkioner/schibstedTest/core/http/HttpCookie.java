package main.java.com.Arkioner.schibstedTest.core.http;

import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by arkioner on 18/05/15.
 */
public class HttpCookie {
    private static HttpCookie instance;

    public static HttpCookie getInstance(){
        if(instance == null){
            instance = new HttpCookie();
        }
        return instance;
    }

    public static final String SECURITY_COOKIE_KEY = "userToken";
    public static final String SESSION_COOKIE_KEY = "sessionToken";

    public void addCookie(HttpExchange exchange, String key, String value) {
        exchange.getResponseHeaders().add("Set-Cookie",key+"="+value+";Path=/;");
    }

    public String getCookie(HttpExchange exchange, String key) {
        List<String> cookiesList = exchange.getRequestHeaders().get("Cookie");
        if(cookiesList == null){
            return "";
        }
        for (String cookies : cookiesList){
            String[] cookiesArray = cookies.split(";");
            for (String cookieInfo : cookiesArray) {
                String[] cookie = cookieInfo.split("=");
                if (key.equals(cookie[0].trim()) && cookie.length == 2) {
                    return cookie[1];
                }
            }
        }
        return "";
    }

    public void expireCookie(HttpExchange exchange, String key) {
        exchange.getResponseHeaders().add("Set-Cookie",key+"=;Path=/;expires=Thu, 01 Jan 1970 00:00:00 GMT");
    }
}
