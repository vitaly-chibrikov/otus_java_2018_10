package ru.otus.dbservice;

import ru.otus.dao.User;
import ru.otus.executor.DbExecutor;
import ru.otus.executor.DbExecutorImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceUserImpl implements DBServiceUser {

    private final DataSource dataSource;

    public DbServiceUserImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long saveUsers(User user) {
       try (Connection connection = dataSource.getConnection()) {
           DbExecutor<User> executor = new DbExecutorImpl<>(connection);
           long userId = executor.insertRecord("insert into user(name) values (?)", Collections.singletonList(user.getName()));
           connection.commit();
           System.out.println("created user:" + userId);
           return userId;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try (Connection connection = dataSource.getConnection()) {
            DbExecutor<User> executor = new DbExecutorImpl<>(connection);
            Optional<User> user = executor.selectRecord("select id, name from user where id  = ?", id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            System.out.println("user:" + user);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
