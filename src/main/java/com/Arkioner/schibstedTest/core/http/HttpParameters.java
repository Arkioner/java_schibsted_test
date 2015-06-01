package main.java.com.Arkioner.schibstedTest.core.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.ws.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arkioner on 18/05/15.
 */
public class HttpParameters {
    private static HttpParameters instance;

    public static HttpParameters getInstance(){
        if(instance == null){
            instance = new HttpParameters();
        }
        return instance;
    }

    public static String parameterKey = "parameters";

    public String getParameter(HttpExchange exchange, String key) throws IOException {
        Map<String, Object> parameters = (HashMap<String, Object>) exchange.getAttribute(parameterKey);
        Object value = parameters.get(key);
        if(value instanceof String) {
            return (String) value;
        }else{
            //StringUtils.join((List<String>) value).toArray(String.class),",");
            return "";//Si no es un string pues...
        }
    }
}
