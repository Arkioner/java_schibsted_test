package main.java.com.Arkioner.schibstedTest.core.http.session;

import main.java.com.Arkioner.schibstedTest.model.Rol.RolEnum;

import java.util.*;

/**
 * Created by arkioner on 17/05/15.
 */
public class Session
{
    private String uuid;
    private Map<String, Object> properties;

    public Session() {
        this.uuid = UUID.randomUUID().toString();
        properties = new HashMap<>();
    }

    public String getUuid() {
        return this.uuid;
    }

    public Object get(String key) {
        return this.properties.get(key);
    }

    public void add(String key, Object value) {
        properties.put(key, value);
    }

    public void remove(String key){
        properties.remove(key);
    }
}
