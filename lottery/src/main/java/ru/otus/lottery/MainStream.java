package ru.otus.lottery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * https://github.com/vitaly-chibrikov/otus_java_2018_10
 * <p>
 * пишите параметром запуска файл source.csv,
 * запускайте.
 *
 * Ссылка на опрос: https://otus.pw/uZQz/
 */

public class MainStream {
    public static void main(String[] args) throws IOException {
        String salt = "Александр КрасновСилон Пластунски\t\n" +
                "Никита НазаровHello OTUS\t\n" +
                "Виталий Метлиновподготовительный - https://otus.ru/online/java/\t\n" +
                "Dmitry Yurkevich34t43otn94375yg04u9gh3049ug7jdklndsfgf\t\n" +
                "unnamed#29307TopSecret";

        Files.lines(Paths.get(args[0]))
                .map(String::trim)
                .map(line -> line.substring(0, !line.contains("@") ? line.length() : line.indexOf("@")))
                .map(line -> line + "\t" + salt)
                .sorted(Comparator.comparingLong(String::hashCode))
                .map(line -> line.hashCode() + "\t" + line.replace(salt, ""))
                .forEach(System.out::println);
    }
}
