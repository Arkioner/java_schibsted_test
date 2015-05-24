package main.java.com.Arkioner.schibstedTest.model.User;

/**
 * Created by arkioner on 17/05/15.
 */
public interface UserRepositoryInterface
{
    public User findUserByUsernameAndPassword(String username,String password) throws UserNotFoundException;
}
