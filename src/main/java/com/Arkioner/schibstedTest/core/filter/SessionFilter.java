package main.java.com.Arkioner.schibstedTest.core.filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpCookie;
import main.java.com.Arkioner.schibstedTest.core.http.HttpRedirect;
import main.java.com.Arkioner.schibstedTest.core.http.HttpSession;
import main.java.com.Arkioner.schibstedTest.core.http.HttpUnauthorized;
import main.java.com.Arkioner.schibstedTest.core.http.session.Session;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthenticationExpiredException;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthenticationService;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthorizationDeniedException;
import main.java.com.Arkioner.schibstedTest.core.security.service.AuthorizationService;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.core.security.token.UserTokenNotFoundException;
import main.java.com.Arkioner.schibstedTest.model.Rol.RolEnum;

import java.io.IOException;

/**
 * Created by arkioner on 17/05/15.
 */
public class SessionFilter extends Filter{

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        String sessionUuid = HttpCookie.getInstance().getCookie(exchange, HttpCookie.SESSION_COOKIE_KEY);
        Session session = HttpSession.getInstance().get(sessionUuid);
        HttpCookie.getInstance().addCookie(exchange, HttpCookie.SESSION_COOKIE_KEY, session.getUuid());
        exchange.setAttribute(HttpSession.sessionKey,session);
        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return null;
    }

}
