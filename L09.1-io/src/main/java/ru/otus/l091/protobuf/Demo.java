package ru.otus.l091.protobuf;


import com.google.protobuf.util.JsonFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author sergey
 * created on 27.01.19.
 */

/*
про WARNING:
https://github.com/protocolbuffers/protobuf/issues/3781
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        var messageOut = HelloWorld.HelloMessage.newBuilder().setMessage("Hello, World, from Protobuf").build();
        var fileName = "protoTest.bin";

        try (FileOutputStream output = new FileOutputStream(fileName)) {
            messageOut.writeTo(output);
        }

        try (FileInputStream input = new FileInputStream(fileName)) {
            var messageIn = HelloWorld.HelloMessage.parseFrom(input);
            System.out.println(messageIn.getMessage());
        }

        var messageJson = JsonFormat.printer().print(messageOut);
        System.out.println(messageJson);

        var builder = HelloWorld.HelloMessage.newBuilder();
        JsonFormat.parser().merge(messageJson, builder);
        var messageFromJson = builder.build();
        System.out.println(messageFromJson.getMessage());
    }
}
