package main.java.com.Arkioner.schibstedTest.model.User;

import main.java.com.Arkioner.schibstedTest.model.Rol.RolEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkioner on 17/05/15.
 */
public class User
{
    private int id;
    private String username;
    private String password;
    private List<RolEnum> roles;
    private String landingPage;

    public User(int id, String username, String password, RolEnum role, String landingPage) {
        this.roles = new ArrayList();
        this.id = id;
        this.username = username;
        this.password = password;
        this.addRole(role);
        this.landingPage = landingPage;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<RolEnum> getRoles() {
        return roles;
    }

    public String getLandingPage() { return landingPage; }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<RolEnum> roles) {
        this.roles = roles;
    }

    public void addRole(RolEnum role)
    {
        this.roles.add(role);
    }

    public void setLandingPage(String landingPage) { this.landingPage = landingPage; }
}
