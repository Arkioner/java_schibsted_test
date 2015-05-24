package main.java.com.Arkioner.schibstedTest.core.security.token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkioner on 17/05/15.
 */
public class InMemoryUserTokenRepository implements UserTokenRepositoryInterface{

    private List<UserToken> tokens;
    private static InMemoryUserTokenRepository instance;

    public static InMemoryUserTokenRepository getInstance()
    {
        if(instance == null){
            instance = new InMemoryUserTokenRepository();
        }
        return instance;
    }

    public InMemoryUserTokenRepository() {

        this.tokens = new ArrayList();

    }

    @Override
    public UserToken findByUuid(final String uuid) throws UserTokenNotFoundException {
        for (UserToken token : tokens) {
            if (token.getUuid().equals(uuid)) {
               return token;
            }
        }
        throw new UserTokenNotFoundException("No user found with uuid: "+uuid);

    }


    @Override
    public void save(UserToken userToken) {
        this.tokens.add(userToken);
    }

    @Override
    public void update(UserToken userToken) {
        /*
         * No hacemos nada ya que el objeto queda actualizado por referencia.
        */
    }

    @Override
    public void delete(UserToken userToken) {
        this.tokens.remove(userToken);
    }







}
