package stream;

import java.util.ArrayList;
import java.util.Arrays;

public class ReflectionHelperExample {

    public static void main(String[] args) {
        Arrays.asList(toClasses(1, 2., "")).forEach(System.out::println);
    }

    static private Class<?>[] toClasses(Object... args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }
}
