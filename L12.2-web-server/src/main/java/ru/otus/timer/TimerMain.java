package ru.otus.timer;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import ru.otus.user.InMemoryUserDao;
import ru.otus.user.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TimerMain {
    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "/static";

    public static void main(String[] args) throws Exception {
        new TimerMain().start();
    }

    private void start() throws Exception {
        resourcesExample();

        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new TimerServlet()), "/timer");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    private UserService userService() {
        return new UserService(userDao());
    }


    private InMemoryUserDao userDao() {
        return new InMemoryUserDao();
    }

    private Gson gson() {
        return new Gson();
    }

    private void resourcesExample() {
        URL url = TimerMain.class.getResource(PUBLIC_HTML + "/index.html"); //local path starts with '/'
        System.out.println(url);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            System.out.println(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
