package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class NewInContainers {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.lines(Paths.get("lines.txt")).collect(Collectors.toList());
        //Iterable
        list.forEach(System.out::println);

        //Removes all of the elements of this collection that satisfy the given predicate.
        list.removeIf(t -> false);

        //Map
        Map<Integer, String> map = new HashMap<>();
        map.putIfAbsent(0, "Hello");
        map.forEach( (k,v) -> System.out.println("Key: " + k + " Value: " + v));

        //AtomicInteger
        (new AtomicInteger()).updateAndGet(operand -> 42);
    }
}
