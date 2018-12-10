package library;

import java.util.Optional;
import java.util.Random;

/**
 * author: Dmitry Arkhangelskiy
 */
public class OptionalExample {

    public static void main(String[] args) {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + get(rnd.nextBoolean()).orElse("default"));
        }
    }

    private static Optional<String> get(boolean cond) {
        if (cond) {
            return Optional.of("hello");
        } else {
            return Optional.empty();
        }
    }
}
