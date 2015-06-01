package main.java.com.Arkioner.schibstedTest.core.http.session;

import main.java.com.Arkioner.schibstedTest.model.Rol.RolEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkioner on 17/05/15.
 */
public class InMemorySessionRepository implements SessionRepositoryInterface
{
    private static InMemorySessionRepository instance;

    public static InMemorySessionRepository getInstance(){
        if(instance == null){
            instance = new InMemorySessionRepository();
        }
        return instance;
    }

    private List<Session> sessions;

    public InMemorySessionRepository()
    {
        this.sessions = new ArrayList<>();
    }

    public Session findSessionByUuid(String uuid) throws SessionNotFoundException
    {
        for (Session session : this.sessions) {
            if(session.getUuid().equals(uuid)) return session;
        }
        throw new SessionNotFoundException("Session not found!");
    }

    @Override
    public void save(Session session) {
        this.sessions.add(session);
    }

    @Override
    public void remove(String uuid) {
        int i = 0;
        for (Session session : this.sessions) {
            if(session.getUuid().equals(uuid)) {
                this.sessions.remove(i);
                return;
            }
            i++;
        }
    }

}
