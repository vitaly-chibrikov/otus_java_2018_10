package ru.otus.process.builder;

/*
 *
 *  1) Запустить процесс вывода переменных окружения на экран/списка файлов
 *  2) Вывести переменные окружения используя Process.getInputStream()
 *  3) Вывести переменные окружения используя ProcessBuilder.redirectOutput()
 *  4) Вывести переменные окружения используя ProcessBuilder.inheritIO()
 *  5) Вывести на экран список файлов в корневой директории "/"
 *
 * */

import java.io.IOException;

public class ProcessBuilderApplication {


    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ls");
        processBuilder.start();
    }


}
