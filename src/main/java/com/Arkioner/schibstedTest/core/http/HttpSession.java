package main.java.com.Arkioner.schibstedTest.core.http;

import main.java.com.Arkioner.schibstedTest.core.http.session.Session;
import main.java.com.Arkioner.schibstedTest.core.http.session.SessionNotFoundException;
import main.java.com.Arkioner.schibstedTest.core.http.session.SessionRepositoryFactory;
import main.java.com.Arkioner.schibstedTest.core.http.session.SessionRepositoryInterface;

/**
 * Created by arkioner on 18/05/15.
 */
public class HttpSession {
    private static HttpSession instance;
    public static HttpSession getInstance(){
        if(instance == null){
            instance = new HttpSession();
        }
        return instance;
    }

    public static String sessionKey = "session";

    public static String userTokenKey = "userToken";

    private SessionRepositoryInterface sessionStore;

    private HttpSession() {
        this.sessionStore = SessionRepositoryFactory.getInstance().getSessionRepository();
    }

    public Session get(String uuid) {
        try {
            return sessionStore.findSessionByUuid(uuid);
        } catch (SessionNotFoundException e) {
            Session newSession = new Session();
            this.sessionStore.save(newSession);
            return newSession;
        }
    }

    public void remove(String uuid) {
        this.sessionStore.remove(uuid);
    }
}
