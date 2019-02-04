package ru.otus.dao;


/**
 * @author sergey
 * created on 03.02.19.
 */
public class User {
    private final long id;
    private final String Name;

    public User(long id, String name) {
        this.id = id;
        Name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
