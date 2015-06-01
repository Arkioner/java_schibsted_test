package main.java.com.Arkioner.schibstedTest.core.security.token;

import main.java.com.Arkioner.schibstedTest.model.User.User;

import java.util.Date;
import java.util.UUID;

/**
 * Created by arkioner on 17/05/15.
 */
public class UserToken
{
    private String uuid;
    private Date expires;
    private User user;

    public UserToken(User user) {
        this.uuid = UUID.randomUUID().toString();
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getExpires() {
        return expires;
    }

    public User getUser() {
        return user;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isExpired() {
        return expires.before(new Date());
    }
}
