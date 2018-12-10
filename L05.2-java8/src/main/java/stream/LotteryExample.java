package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class LotteryExample {
    public static void main(String[] args) throws IOException {
        String salt = "";
        Files.lines(Paths.get("lines.txt"))
                .map(String::trim)
                .map(line -> line.substring(0, !line.contains("@") ? line.length() : line.indexOf("@")))
                .map(line -> line + "\t" + salt)
                .sorted(Comparator.comparingLong(String::hashCode))
                .map(line -> line.hashCode() + "\t" + line.replace(salt, ""))
                .forEach(System.out::println);
    }
}
