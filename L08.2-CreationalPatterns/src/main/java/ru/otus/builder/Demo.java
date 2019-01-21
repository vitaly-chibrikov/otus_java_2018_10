package ru.otus.builder;

/**
 * @author sergey
 * created on 17.09.18.
 */
public class Demo {
    public static void main(String[] args) {
         BigObject bigObject = new BigObject.BigObjectBuilder("1")
                 .withParam2("2")
                 .withParam3("3")
            //     .withParam4("4")
                 .withParam5("5")
                 .build();
        System.out.println(bigObject);
    }
}
