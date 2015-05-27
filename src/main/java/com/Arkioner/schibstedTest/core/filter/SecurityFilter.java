package main.java.com.Arkioner.schibstedTest.core.filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import main.java.com.Arkioner.schibstedTest.core.http.HttpRedirect;
import main.java.com.Arkioner.schibstedTest.core.http.HttpUnauthorized;
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
public class SecurityFilter extends Filter{

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        String path = exchange.getRequestURI().getPath().toLowerCase();
        switch (path){
            case "/private/page1":
                if(!checkSecurity(exchange,RolEnum.ROL_1)){
                    return;
                }
                break;
            case "/private/page2":
                if(!checkSecurity(exchange,RolEnum.ROL_2)){
                    return;
                }
                break;
            case "/private/page3":
                if(!checkSecurity(exchange,RolEnum.ROL_3)){
                    return;
                }
                break;
        }
        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return null;
    }

    private boolean checkSecurity(HttpExchange exchange,RolEnum rol) throws IOException {
        UserToken userToken;
        try {
            userToken = checkAuthentication(exchange);
            checkAuthorization(userToken, rol);
        } catch (UserTokenNotFoundException e) {
            HttpRedirect.getInstance().sendRedirect(exchange, "/login?notAuthenticated");
            return false;
        } catch (AuthorizationDeniedException e) {
            HttpUnauthorized.getInstance().sendUnathorized(exchange);
            return false;
        } catch (AuthenticationExpiredException e) {
            HttpRedirect.getInstance().sendRedirect(exchange, "/login?expiredSession");
            return false;
        }
        return true;
    }

    private UserToken checkAuthentication(HttpExchange exchange) throws UserTokenNotFoundException, AuthenticationExpiredException {
        return AuthenticationService.getInstance().getAuthentication(exchange);
    }

    private void checkAuthorization(UserToken userToken, RolEnum rol) throws AuthorizationDeniedException {
        AuthorizationService.getInstance().getAuthorization(userToken, rol);
    }
}
