package ru.otus.l012;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by tully.
 * Updated by Sergey Petrelevich
 *
 * Example for L01.2
 *
 * useful commands:
 * mvn clean
 * mvn package
 * java -jar ./target/L01.2-obfuscation.jar
 * java -jar ./target/L01.2-obfuscation-small.jar

 *
 * To check the jar:
 * unzip -l ./target/L01.2-obfuscation.jar
 * unzip -l ./target/L01.2-obfuscation-small.jar
 *
 *
 * Useful links:
 * proguard-maven-plugin documentation and examples:
 * http://wvengen.github.io/proguard-maven-plugin/
 */
public class Main {
    private static final int MEASURE_COUNT = 1;

    public static void main(String[] args) {
        Collection<Integer> example = new ArrayList<>();
        int min = 0;
        int max = 999_999;
        for (int i = min; i < max + 1; i++) {
            example.add(i);
        }

        List<Integer> result = new ArrayList<>();
        Collections.shuffle((List<Integer>)example);
        calcTime(() -> result.addAll(Lists.reverse((List<Integer>)example)));
    }

    private static void calcTime(Runnable runnable) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MEASURE_COUNT; i++)
            runnable.run();
        long finishTime = System.nanoTime();
        long timeNs = (finishTime - startTime) / MEASURE_COUNT;
        System.out.println("Time spent: " + timeNs + "ns (" + timeNs / 1_000_000 + "ms)");
    }
}
