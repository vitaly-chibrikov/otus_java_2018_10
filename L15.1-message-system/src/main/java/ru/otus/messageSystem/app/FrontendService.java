package ru.otus.messageSystem.app;

import ru.otus.messageSystem.entity.Addressee;

/**
 * Created by tully.
 */
public interface FrontendService extends Addressee {
    void init();

    void handleRequest(String login);

    void addUser(int id, String name);
}

