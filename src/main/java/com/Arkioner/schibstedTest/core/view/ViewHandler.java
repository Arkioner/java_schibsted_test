package main.java.com.Arkioner.schibstedTest.core.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yoshi on 25/05/2015.
 */
public class ViewHandler {
    private static ViewHandler instance;

    public static ViewHandler getInstance(){
        if(instance == null){
            instance = new ViewHandler();
        }
        return instance;
    }

    public String loadView(String template, Map<String,String> params) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("main/java/com/Arkioner/schibstedTest/view/"+template+".html");
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        final StringBuilder html = new StringBuilder();
        while (bufferedReader.ready())
        {
            html.append(bufferedReader.readLine());
        }
        if(params != null && params.size() > 0){
            this.replaceParams(html,params);
        }
        return html.toString();
    }

    public String loadView(String template) throws IOException {
        return this.loadView(template,null);
    }

    public void replaceParams(StringBuilder sb, Map<String,String> params){
        for (Map.Entry<String, String> param : params.entrySet()){
            int start;
            while ((start = sb.indexOf(param.getKey())) > -1) {
                int end = start + param.getKey().length();
                sb.replace(start, end, param.getValue());
            }
        }
    }
}
