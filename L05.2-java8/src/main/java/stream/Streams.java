package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 */
@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess", "Convert2MethodRef", "UnusedReturnValue", "SameParameterValue", "SimplifyStreamApiCallChains"})
public class Streams {

    private static List<Student> students;

    static {
        Student s1 = new Student("Alex", 22, 5, 4.5);
        Student s2 = new Student("Maria", 22, 5, 3.5);
        Student s3 = new Student("John", 12, 4, 4.7);
        Student s4 = new Student("Bob", 22, 5, 4.8);
        Student s5 = new Student("Anna", 20, 3, 4.5);
        students = Arrays.asList(s1, s2, s3, s4, s5);
    }

    public static void main(String[] args) {
        withoutChanges();
        //System.out.println(getAvgMark());
        //groupBy(students);
    }

    static void withoutChanges() {
        List<Student> students2 = students.stream().collect(Collectors.toList());
        System.out.println(students.equals(students2));
    }

    static double getAvgMark() {
        OptionalDouble avg = students.stream()
                .filter(st -> st.getCourse() == 5)
                .mapToDouble(st -> st.getAvgMark())
                .average();

        return avg.orElse(0.0);
    }

    static void groupBy(List<Student> students) {
        Map<Integer, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(s -> s.getCourse()));
        System.out.println(map);
    }

}
