package ru.otus.dbservice;

import ru.otus.dao.User;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public interface DBServiceUser {

    long saveUsers(User user);
    Optional<User> getUser(long id);

}
