package ru.otus.l091.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DemoSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize(serialize());
    }

    private static byte[] serialize() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream os = new ObjectOutputStream(baos);
        ) {
            os.writeObject(new Person(12, "SerialPersonForArray"));
            os.flush();
            return baos.toByteArray();
        }
    }

    private static void deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);) {
            ObjectInputStream is = new ObjectInputStream(bis);
            Person person = (Person) is.readObject();
            System.out.println("deSerialized person:" + person);
        }
    }
}
