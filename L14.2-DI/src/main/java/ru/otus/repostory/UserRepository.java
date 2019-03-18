package ru.otus.repostory;

import ru.otus.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    long create(String name);
}
