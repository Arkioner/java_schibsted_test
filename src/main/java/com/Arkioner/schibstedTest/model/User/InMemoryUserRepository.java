package main.java.com.Arkioner.schibstedTest.model.User;

import main.java.com.Arkioner.schibstedTest.model.Rol.RolEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkioner on 17/05/15.
 */
public class InMemoryUserRepository implements UserRepositoryInterface
{
    private static InMemoryUserRepository instance;

    public static InMemoryUserRepository getInstance(){
        if(instance == null){
            instance = new InMemoryUserRepository();
        }
        return instance;
    }

    private List<User> users;

    private InMemoryUserRepository()
    {
        this.users = new ArrayList<>();

        User user_1 = new User(1,"user_1","pag_1",RolEnum.ROL_1, "/private/page1");
        User user_2 = new User(1,"user_2","pag_2",RolEnum.ROL_2, "/private/page2");
        User user_3 = new User(1,"user_3","pag_3",RolEnum.ROL_3, "/private/page3");

        User user_4 = new User(1,"user_12","pag_12",RolEnum.ROL_1, "/private/page1");
        user_4.addRole(RolEnum.ROL_2);

        this.users.add(user_1);
        this.users.add(user_2);
        this.users.add(user_3);
        this.users.add(user_4);
    }

    public User findUserByUsernameAndPassword(String username,String password) throws UserNotFoundException
    {
        for (User user : this.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
        }
        throw new UserNotFoundException("User not found!");
    }

}
