package ru.otus.messageSystem.app;

import ru.otus.messageSystem.entity.Addressee;

/**
 * Created by tully.
 */
public interface DBService extends Addressee {
    void init();

    int getUserId(String name);
}
