package ru.otus.login;

import ru.otus.user.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private static final int EXPIRE_INTERVAL = 20; // seconds
    private final UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (userService.authenticate(name, password)) {
            request.getSession();
        } else {
            PrintWriter out = response.getWriter();
            out.println("Either user name or password is wrong.");
        }

    }

}