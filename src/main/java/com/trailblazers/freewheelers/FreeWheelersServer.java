package com.trailblazers.freewheelers;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FreeWheelersServer {

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/log4j.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load log4j.properties", e);
        }
        PropertyConfigurator.configure(properties);
    }

    private final Server server;

    public FreeWheelersServer() {
        server = new Server(8080);
        server.setHandler(handlers());
    }

    private void start() throws Exception {
        server.start();
        server.join();
    }

    private Handler handlers() {
        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{webAppHandler()});

        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.addHandler(handlerList);

        return handlerCollection;
    }

    private Handler webAppHandler() {
        WebAppContext handler = new WebAppContext();
        handler.setWar("src/main/webapp");

        return handler;
    }

    public static void main(String... args) throws Exception {
        new FreeWheelersServer().start();
    }
}
