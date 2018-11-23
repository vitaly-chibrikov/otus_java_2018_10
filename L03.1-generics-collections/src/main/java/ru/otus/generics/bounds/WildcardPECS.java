package ru.otus.generics.bounds;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class WildcardPECS {

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());
        //printProducer(animalList); //ошибка
        printConsumer(animalList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        printProducer(catList);
        printConsumer(catList);

    }

    private static void printProducer(List<? extends Cat> catList) {
        //catList.add(new Animal()); //Ошибка
        //catList.add(new Cat()) //Ошибка
        //catList.add(new HomeCat()); //Ошибка
        catList.forEach(System.out::println);
    }

    private static void printConsumer(List<? super Cat> catList) {
        //catList.add(new Animal());// Ошибка
        catList.add(new Cat());
        catList.add(new HomeCat("noName"));
        catList.forEach(System.out::println);
    }

}
