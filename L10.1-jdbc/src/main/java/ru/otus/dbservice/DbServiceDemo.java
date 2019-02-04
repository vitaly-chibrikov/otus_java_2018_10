package ru.otus.dbservice;

import ru.otus.dao.User;
import ru.otus.executor.DbExecutor;
import ru.otus.executor.DbExecutorImpl;
import ru.otus.executor.ExecutorDemo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSourceH2();
        DbServiceDemo demo = new DbServiceDemo();

        demo.createTable(dataSource);

        DBServiceUser dbServiceUser = new DbServiceUserImpl(dataSource);
        long id = dbServiceUser.saveUsers(new User(0,"dbServiceUser"));
        Optional<User> user = dbServiceUser.getUser(id);

        System.out.println(user);
        user.ifPresentOrElse(crUser -> System.out.println("created user, name:" + crUser.getName()),
                () -> System.out.println("user was not created"));

    }

    private void createTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50))")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }
}
