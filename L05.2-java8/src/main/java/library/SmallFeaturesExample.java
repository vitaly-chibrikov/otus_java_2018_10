package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/**
 * author: Vitaly Chibrikov
 */
public class SmallFeaturesExample {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.lines(Paths.get("lines.txt")).collect(Collectors.toList());

        oldJoin(list.toArray(new String[0]));
        newJoin(list.toArray(new String[0]));
    }

    private static void oldJoin(String[] array) {
        StringBuilder concat = new StringBuilder();
        concat.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            concat.append(";").append(array[i]);
        }
        System.out.println(concat);
    }

    private static void newJoin(String[] array) {
        System.out.println(String.join(";", array));
    }
}
