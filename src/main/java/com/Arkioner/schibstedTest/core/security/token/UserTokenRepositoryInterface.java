package main.java.com.Arkioner.schibstedTest.core.security.token;

/**
 * Created by arkioner on 17/05/15.
 */
interface UserTokenRepositoryInterface
{
    public UserToken findByUuid(String uuid) throws UserTokenNotFoundException;
    public void save(UserToken userToken);
    public void update(UserToken userToken);
    public void delete(UserToken userToken);
}
