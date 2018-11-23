package ru.otus.generics.bounds;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class GenericsBounded<T extends Cat> {

    public static void main(String[] args) {

        //GenericsBounded<Animal> genericsBounded = new GenericsBounded<>(); //ошибка
        GenericsBounded<Cat> ok1 = new GenericsBounded<>();
        GenericsBounded<HomeCat> ok2 = new GenericsBounded<>();

    }

}
