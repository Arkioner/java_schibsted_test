package main.java.com.Arkioner.schibstedTest.core.security.service;

import main.java.com.Arkioner.schibstedTest.core.security.token.UserToken;
import main.java.com.Arkioner.schibstedTest.model.Rol.RolEnum;

/**
 * Created by arkioner on 17/05/15.
 */
public class AuthorizationService {
    private static AuthorizationService instance;

    public static AuthorizationService getInstance(){
        if(instance == null){
            instance = new AuthorizationService();
        }
        return instance;
    }

    public void getAuthorization(UserToken userToken, RolEnum rol) throws AuthorizationDeniedException {
        for (RolEnum rolEnum : userToken.getUser().getRoles()) {
            if (rolEnum == rol){
                return;
            }
        }
        throw new AuthorizationDeniedException("You are not Authorized");
    }
}
