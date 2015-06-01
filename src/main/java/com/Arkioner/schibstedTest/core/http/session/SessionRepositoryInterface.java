package main.java.com.Arkioner.schibstedTest.core.http.session;

/**
 * Created by arkioner on 17/05/15.
 */
public interface SessionRepositoryInterface
{
    Session findSessionByUuid(String uuid) throws SessionNotFoundException;

    void save(Session newSession);

    void remove(String uuid);
}
